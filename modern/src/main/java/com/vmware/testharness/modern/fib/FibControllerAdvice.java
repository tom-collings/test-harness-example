package com.vmware.testharness.modern.fib;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FibControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FibException.class)
    public ResponseEntity<Integer> handleBadRequest(FibException exc, WebRequest req) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
