package com.vmware.testharness.legacy.fib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@Slf4j
public class ModernFibService {

    private StreamBridge streamBridge;

    RestTemplate restTemplate = new RestTemplate();

    public ModernFibService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Async
    public void sendToModernAsync (int number) {
        log.debug("In async method, thread name is " + Thread.currentThread().getName());
        sendToModern(number);
    }

    public int sendToModern (int number) {
        log.info("sending to the modern implementation for a result");
        String uuid = UUID.randomUUID().toString();
        ResponseEntity<Integer> response =
                restTemplate.getForEntity("http://localhost:8082/fib?index=" + number + "&uuid=" + uuid, Integer.class);

        int newVal = response.getBody().intValue();

        streamBridge.send("fibcalc", new FibCalc().withIndex(number).withUuid(uuid).withValue(newVal).withSource("LEGACY"));

        return newVal;
    }
}
