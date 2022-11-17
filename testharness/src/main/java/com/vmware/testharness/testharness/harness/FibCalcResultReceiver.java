package com.vmware.testharness.testharness.harness;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class FibCalcResultReceiver {

    private ResultRepository resultRepository;

    public FibCalcResultReceiver(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Bean
    public Consumer<FibCalc> resultRecieved () {
        return fibCalc -> {
            log.info("Received: {}", fibCalc.toString());
            resultRepository.save(new FibCalcEntity()
                                        .withCalculatedValue(fibCalc.getValue())
                                        .withIndex(fibCalc.getIndex())
                    .withSource(fibCalc.getSource())
                    .withUuid(fibCalc.getUuid())

            );
        };
    }
}
