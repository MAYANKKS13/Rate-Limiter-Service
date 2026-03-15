package com.rate.limiter.rateLimiter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/rateLimit")
    public String test() {
        return "API Success";
    }

}
