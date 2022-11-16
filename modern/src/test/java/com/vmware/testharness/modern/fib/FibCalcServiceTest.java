package com.vmware.testharness.modern.fib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FibCalcServiceTest {

    @Test
    public void getWithNegativeIndex() {
        FibCalcService fibCalcService = new FibCalcService();

        assertThrows(FibException.class, () -> {
            int value = fibCalcService.get(-1);
        });
    }

    @Test
    public void getZero() throws Exception{
        FibCalcService fibCalcService = new FibCalcService();

        assertEquals(0, fibCalcService.get(0));
    }

    @Test
    public void getOne() throws Exception{
        FibCalcService fibCalcService = new FibCalcService();

        assertEquals(1, fibCalcService.get(1));
    }

    @Test
    public void getTwo() throws Exception{
        FibCalcService fibCalcService = new FibCalcService();

        assertEquals(1, fibCalcService.get(2));
    }

    @Test
    public void getFive() throws Exception{
        FibCalcService fibCalcService = new FibCalcService();

        assertEquals(5, fibCalcService.get(5));
    }

}
