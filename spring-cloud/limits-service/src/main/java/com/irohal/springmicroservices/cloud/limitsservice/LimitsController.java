package com.irohal.springmicroservices.cloud.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitsConfiguration retrieveLimits() {
        return new LimitsConfiguration(configuration.getMinimum(), configuration.getMaximum());
    }

}
