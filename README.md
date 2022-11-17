Test Harness Example

Often times when strangling code from a legacy implementation, the correctness of the new implementation is unknown.  We can build this modern implementation but we will not have strong confidence in our new implementation until we can validate that correctness.

This codebase is a sample of how to build a test harness, collecting runs from the legacy and the modern implementations, adding them to a repository, and giving exposure to that repository to run queries on the runs, comparing legacy to modern implementations.  There is also an example of a run-time control, allowing an operator to send traffic to (or not!) the modern imeplementaion.

This code sample uses a fibonacci calculation as its example.

To begin, start the legacy implementaion by going to the `/legacy` directory and typing

```mvn spring-boot:run```

This will start the legacy Spring Boot application, listening on localhost port 8081.  To get a Fibonacci-th value, curl the `/fib` endpoint with a `number` parameter.  For example, this url will get the seventh number in the sequence:

```localhost:8081/fib?number=7```

At this point the legacy system will only return a value and has a runtime configuration to *not* send any requests to the modern application.  Before we can do that, we must start the modern implementation by going to the `/modern` directory and typing

```mvn spring-boot:run```

This starts the modern Spring Boot application, listening on localhost port 8082.

Also, before sending traffic to the modern app, a RabbitMQ instance should be running locally.  Refer to the RabbitMQ documentation to start this process, either through a docker container using port forwarding or a native app.

The legacy app has a simple run-time configuration to decide two things:

1. what percent of requests to forward to the modern application and
2. whether or not to use the value returned by the modern application or to continue to use the legacy calculation.

The second part is important:  It allows us to send data to the new implementation and check its correctness before using the values returned by this modern app.  

To change the runtime configuraion, from the `/legacy` directory POST a new configuration:

```curl http://localhost:8081/controls -X POST -H 'Content-Type: application/json' -d @docs/sampleConfig.json```

The committed state of this `sampleConfig.json` file is to send 100% of the traffic to the modern app, and to *not* use the results of that calculation.

There is a ten second interval before a new runtime configuration is checked, so be prepared to wait at least that long.

Now if the same `localhost:8081\fib?number=7` endpoint is hit, you should be able to see a few things:

1.  logs in the legacy app indicating that the modern app was invoked
2.  logs in the modern app indicating that a request was made
3.  messages received on the fibcalc-topic (can be viewed on the RabbitMQ console (localhost:15672))

If these three things are visible, we are then ready to start the testharness applicaton.  Go to the `testharness` directory and type

```mvn:spring-boot:run```

This testharness application takes results off of the fibcalc-topic exchange and writes them to a local data store.  There should be a pair for each request:  one from the legacy system and another from the modern system.  The repository also has a few queries exposed through a controller:

```localhost:8083\all``` will return the results of every message pulled off the exchange

```localhost:8083\totalCalcCount``` will return the number of message pairs processed

```localhost:8083\goodCountRatio``` will return a number between 0 and 1 showing how many of the pairs have identical results versus the total number of pairs

```localhost:8083\badMatches``` will return the pairs where the results do not match

This codebase is meant to be used as an illustration:  there are a lot of hard-coded configurations, and none of the endpoints are secured.  Please use caution when applying these techniques in production.
