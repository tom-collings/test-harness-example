package com.vmware.testharness.testharness.harness;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@With
@Entity
public class FibCalcEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_jpa_sequence_generator")
    @SequenceGenerator(name = "posts_jpa_sequence_generator", sequenceName = "post_id_sequence")
    private long id = 0;

    private String uuid;

    private int index;

    private int calculatedValue;

    private String source;
}
