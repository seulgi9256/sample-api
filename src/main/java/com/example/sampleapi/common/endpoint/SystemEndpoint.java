package com.example.sampleapi.common.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemEndpoint {

    @GetMapping("/sys/healthz")
    public String  healthz(){
        return "Service Healthy";
    }
}
