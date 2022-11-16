package com.vmware.testharness.modern.fib;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class FibController {

    FibService fibService;

    public FibController(FibService fibService) {
        this.fibService = fibService;
    }

    @GetMapping("/fib")
    @ResponseBody
    public Integer getByIndex(@RequestParam("index")Integer index, @RequestParam("uuid")String uuid) throws FibException {
        log.info("retrieving for index = " + index);
        return fibService.getAndSend(index.intValue(), uuid);
    }

}
