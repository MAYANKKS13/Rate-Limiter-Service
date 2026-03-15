package com.rate.limiter.rateLimiter.filter;

import com.rate.limiter.rateLimiter.service.RateLimiterService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.LogRecord;

@Component
@RequiredArgsConstructor
public class RateLimitFilter implements Filter {
    private final RateLimiterService rateLimitService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String userId = httpRequest.getHeader("User-Id");
        if(userId == null) {
            userId = "UNKNOWN_USER";
        }
        if (rateLimitService.isAllowed(userId)) {
            chain.doFilter(request, response);
        }
        else {
            response.getWriter().write("Rate limit exceeded. Try again later.");
            httpResponse.setStatus(429);
            return;
        }
    }
}
