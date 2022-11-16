package com.vmware.testharness.modern.fib;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FibCalcService {

    public int get(int index) throws FibException {
        if (index < 0) {
            throw new FibException("negatvie index supplied");
        }
        if (index == 0) {
            return 0;
        }
        if (index == 1) {
            return 1;
        }
        return (get(index - 1) + get(index - 2));
    }
}
