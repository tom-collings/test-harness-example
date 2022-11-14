package com.vmware.testharness.legacy.fib;

public class FibException extends Throwable {
    public FibException(String invalid_param) {
        super(invalid_param);
    }
}
