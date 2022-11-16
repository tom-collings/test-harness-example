package com.vmware.testharness.testharness.harness;

import org.springframework.data.repository.CrudRepository;

public interface ResultRepository extends CrudRepository<FibCalcEntity, Long> {
}
