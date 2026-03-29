package com.rate.limiter.rateLimiter.service;

import com.rate.limiter.rateLimiter.constant.RLSConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateLimiterService {
    private final StringRedisTemplate redisTemplate;
    private final RLSConstants rlsConstants;

    public boolean isAllowed(String userId, String apiPath) {
        String key = "rate_limit:user:" + userId + ":api:" + apiPath;
        Long reqCount = redisTemplate.opsForValue().increment(key);
        if (reqCount == 1) {
            redisTemplate.expire(key, rlsConstants.getWindowSeconds(), java.util.concurrent.TimeUnit.SECONDS);
        }

        System.out.println("User: " + userId + ", Request Count: " + reqCount);

        return reqCount <= rlsConstants.getMaxRequests();
    }


}
