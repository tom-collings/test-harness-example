package com.vmware.testharness.modern.fib;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@With
public class FibCalc {

    private String uuid;

    private int index;

    private int value;

    private String source;

}
