package com.vmware.testharness.legacy.fib;

import com.vmware.testharness.legacy.dials.ControlsEntity;
import com.vmware.testharness.legacy.dials.ControlsRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;

@Controller
@Slf4j
public class FibController {

    private ControlsRespository controlsRespository;
    private ModernFibService modernFibService;
    private long lastLookupTime = 0;
    ControlsEntity controlsEntity;
    private static final long LOOKUP_INTERVAL = 10000;

    public FibController(ControlsRespository controlsRespository, ModernFibService modernFibService) {
        this.controlsRespository = controlsRespository;
        this.modernFibService = modernFibService;
    }

    @GetMapping("/fib")
    @ResponseBody
    public int fib (@RequestParam("number") Integer number) throws FibException{

        long now = Calendar.getInstance().getTimeInMillis();
        if (now - lastLookupTime > LOOKUP_INTERVAL) {
            log.info("looking up controls config");
            lastLookupTime = now;
            controlsEntity = (ControlsEntity) controlsRespository.findById(1L).get();
        }

        log.info("Controls Entity Value = " + controlsEntity.toString());

        double rando = Math.random()*100.0;
        if ((rando < controlsEntity.getSendToModernPercent())  && controlsEntity.isUseModernImpl()){
            //send to modern inline and return the value calculated
            return modernFibService.sendToModern(number.intValue());
        }
        else if ((rando < controlsEntity.getSendToModernPercent()) && (!controlsEntity.isUseModernImpl())) {
            // send to modern async
            log.info ("sending async, controller thread is " + Thread.currentThread().getName());
            modernFibService.sendToModernAsync(number.intValue());
        }
        return fibCalc(number.intValue());
    }

    private int fibCalc (int number) throws FibException{
        if (number < 0) {
            throw new FibException ("invalid param");
        }
        if (number == 0) {
            return 0;
        }
        if (number == 1) {
            return 1;
        }
        int twoBack = 0;
        int lastSum = 1;
        int newSum = 1;
        for (int i = 2; i <= number; i++) {
            newSum = twoBack + lastSum;
            twoBack = lastSum;
            lastSum = newSum;
        }
        return newSum;
    }

}
