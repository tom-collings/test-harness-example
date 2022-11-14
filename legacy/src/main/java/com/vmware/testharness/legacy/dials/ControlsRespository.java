package com.vmware.testharness.legacy.dials;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlsRespository extends CrudRepository<ControlsEntity, Long> {
}
