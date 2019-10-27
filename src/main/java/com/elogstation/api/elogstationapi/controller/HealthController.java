package com.elogstation.api.elogstationapi.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class HealthController {

    @PostMapping("/health")
    @ResponseBody
    public ResponseEntity<?> health() {
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}