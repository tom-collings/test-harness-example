package com.vmware.testharness.legacy.fib;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class FibCalc {

    private String uuid;

    private int index;

    private int value;

    private String source;

}
