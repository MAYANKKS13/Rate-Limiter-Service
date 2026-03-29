package com.rate.limiter.rateLimiter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/rateLimit")
    public String test(@RequestHeader("User-Id-1") String userId) {
        return "API Success for the user: " + userId;
    }

}
