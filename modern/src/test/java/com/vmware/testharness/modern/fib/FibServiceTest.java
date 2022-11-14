package com.vmware.testharness.modern.fib;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FibServiceTest {

    @Test
    public void getWithNegativeIndex() {
        FibService fibService = new FibService();

        assertThrows(FibException.class, () -> {
            int value = fibService.get(-1);
        });
    }

    @Test
    public void getZero() throws Exception{
        FibService fibService = new FibService();

        assertEquals(0, fibService.get(0));
    }

    @Test
    public void getOne() throws Exception{
        FibService fibService = new FibService();

        assertEquals(1, fibService.get(1));
    }

    @Test
    public void getTwo() throws Exception{
        FibService fibService = new FibService();

        assertEquals(1, fibService.get(2));
    }

    @Test
    public void getFive() throws Exception{
        FibService fibService = new FibService();

        assertEquals(5, fibService.get(5));
    }

}
