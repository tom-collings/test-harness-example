package com.vmware.testharness.testharness.harness;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class FibCalcController {

    ResultRepository resultRepository;

    public FibCalcController(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @GetMapping("/all")
    public List<FibCalcEntity> getAll() {
        return resultRepository.findAll();
    }

    @GetMapping("/totalCalcCount")
    public long getTotalCalcCount() {
        return resultRepository.countDistinctUuids();
    }

    @GetMapping("/goodCountRatio")
    public double getTotalGoodCalcCountPercent() {
        long totalCount = resultRepository.countDistinctUuids();
        long goodCount = resultRepository.countGoodMatches();
        log.info("good count, total count = " + goodCount + ", " + totalCount);
        return (double)goodCount/(double)totalCount;
    }

    @GetMapping("/badMatches")
    public List<FibCalcEntityPair> getBadMatches() {
        return resultRepository.getBadMatches();
    }
}
