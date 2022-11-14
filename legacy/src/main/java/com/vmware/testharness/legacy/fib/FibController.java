package com.vmware.testharness.legacy.fib;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FibController {

    @GetMapping("/fib")
    @ResponseBody
    public int fib (@RequestParam("number") Integer number) throws FibException{

        if (number.intValue() < 0) {
            throw new FibException ("invalid param");
        }
        if (number.intValue() == 0) {
            return 0;
        }
        if (number.intValue() == 1) {
            return 1;
        }
        int twoBack = 0;
        int lastSum = 1;
        int newSum = 1;
        for (int i = 2; i <= number.intValue(); i++) {
            newSum = twoBack + lastSum;
            twoBack = lastSum;
            lastSum = newSum;
        }
        return newSum;
    }

}
