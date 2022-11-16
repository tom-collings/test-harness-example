package com.vmware.testharness.modern.fib;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class FibService {

    private final StreamBridge streamBridge;
    private final FibCalcService fibCalcService;

    public FibService(StreamBridge streamBridge, FibCalcService fibCalcService) {
        this.streamBridge = streamBridge;
        this.fibCalcService = fibCalcService;
    }

    public int getAndSend(int index, String uuid) throws FibException {
        int newVal = fibCalcService.get(index);

        sendResult(new FibCalc().withIndex(index).withValue(newVal).withUuid(uuid).withSource("MODERN"));

        return newVal;
    }

    private void sendResult(FibCalc fibCalc) {
        streamBridge.send("fibcalc", fibCalc);
    }
}
