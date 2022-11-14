package com.vmware.testharness.legacy.dials;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class ControlsEntity {

    @Id
    private long id;

    // if this is set, we use the result of the modern implementation if the request is forwarded there.
    private boolean useModernImpl;

    //what percent of requests to send to the modern implementation
    private int sendToModernPercent;

}
