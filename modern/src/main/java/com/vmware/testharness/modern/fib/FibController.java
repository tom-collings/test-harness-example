package com.vmware.testharness.modern.fib;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FibController {

    FibService fibService;

    public FibController(FibService fibService) {
        this.fibService = fibService;
    }

    @GetMapping("/fib")
    @ResponseBody
    public Integer getByIndex(@RequestParam("index")Integer index) throws FibException {
        return fibService.get(index.intValue());
    }

}
