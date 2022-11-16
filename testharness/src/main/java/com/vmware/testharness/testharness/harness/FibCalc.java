package com.vmware.testharness.testharness.harness;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@With
@ToString
public class FibCalc {

    private String uuid;

    private int index;

    private int value;

    private String source;
}
