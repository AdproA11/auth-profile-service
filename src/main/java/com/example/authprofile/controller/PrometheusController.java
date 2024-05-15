package com.example.authprofile.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/actuator/prometheus")
public class PrometheusController {

    @Timed(description = "Request Processing Time")
    @RequestMapping
    public String index() {
        return "Prometheus metrics endpoint";
    }

}
