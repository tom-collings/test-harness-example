package com.vmware.testharness.testharness.harness;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResultRepository extends JpaRepository<FibCalcEntity, Long> {

    @Query("select count(distinct f.uuid) from FibCalcEntity f")
    long countDistinctUuids();

    @Query("select count(f1.uuid) from FibCalcEntity f1, FibCalcEntity f2" +
            " where f1.uuid = f2.uuid" +
            " and f1.source='LEGACY' and f2.source='MODERN'" +
            " and f1.calculatedValue = f2.calculatedValue")
    long countGoodMatches();

    @Query("select f2 as modernResult, f1 as legacyResult from FibCalcEntity f1, FibCalcEntity f2" +
            " where f1.uuid = f2.uuid" +
            " and f1.source='LEGACY' and f2.source='MODERN'" +
            " and not f1.calculatedValue = f2.calculatedValue")
    List<FibCalcEntityPair> getBadMatches();

}
