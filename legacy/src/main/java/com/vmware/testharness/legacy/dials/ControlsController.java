package com.vmware.testharness.legacy.dials;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControlsController {

    private ControlsRespository controlsRespository;

    public ControlsController(ControlsRespository controlsRespository) {
        this.controlsRespository = controlsRespository;
    }

    @EventListener
    public void appReady (ApplicationReadyEvent event) {
        // initialize the config to send everything to the legacy implementation
        controlsRespository.save(new ControlsEntity().withId(1L).withSendToModernPercent(100).withUseModernImpl(true));
    }

    @PostMapping(path = "/controls",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ControlsEntity> changeControls (@RequestBody ControlsEntity controlsEntity) {

        // we want exactly one control, and will reset the ID to ensure this.
        controlsEntity.setId(1L);
        controlsRespository.save(controlsEntity);

        return new ResponseEntity<>(controlsEntity, HttpStatus.OK);
    }

    @GetMapping("/controls")
    public ControlsEntity getControls () {
        return controlsRespository.findById(1L).get();
    }

}
