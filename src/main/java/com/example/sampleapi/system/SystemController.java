package com.example.sampleapi.system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {

    @GetMapping("/sys/healthz")
    public String  healthz(){
        return "Service Healthy";
    }
}
