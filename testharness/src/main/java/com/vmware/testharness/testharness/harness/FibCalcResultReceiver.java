package com.vmware.testharness.testharness.harness;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class FibCalcResultReceiver {

    @Bean
    public Consumer<FibCalc> resultRecieved () {
        return fibCalc -> {
            log.info("Received: {}", fibCalc.toString());
        };
    }
}
