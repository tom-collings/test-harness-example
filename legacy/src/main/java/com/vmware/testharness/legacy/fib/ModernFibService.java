package com.vmware.testharness.legacy.fib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ModernFibService {

    RestTemplate restTemplate = new RestTemplate();

    @Async
    public void sendToModernAsync (int number) {
        log.info("In async method, thread name is " + Thread.currentThread().getName());
        sendToModern(number);
    }

    public int sendToModern (int number) {
        log.info("sending to the modern implementation for a result");
        ResponseEntity<Integer> response =
                restTemplate.getForEntity("http://localhost:8082/fib?index=" + number, Integer.class);
        return response.getBody().intValue();
    }
}
