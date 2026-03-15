package com.rate.limiter.rateLimiter.constant;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ratelimiter")
@Getter
public class RLSConstants {
    private int maxRequests;
    private int windowSeconds;
}
