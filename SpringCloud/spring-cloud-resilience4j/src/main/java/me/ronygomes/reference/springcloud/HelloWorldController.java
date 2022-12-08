package me.ronygomes.reference.springcloud;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class HelloWorldController {
    
    private static Logger log = LoggerFactory.getLogger(HelloWorldController.class);

    private static final String NON_EXISTENT_URL = "http://localhost:9090/doesn-not-exits";

    @GetMapping("/raw")
    public Map<String, String> rawRequest() {
        return badRequest("RAW");
    }

    @Retry(name = "default", fallbackMethod = "fallbackRequest")
    @GetMapping("/retryDefault")
    public Map<String, String> retryRequestDefault() {
        return badRequest("RETRY_DEFAULT");
    }

    // resilience4j.retry.instances.[configName].*
    @Retry(name = "custom", fallbackMethod = "fallbackRequest")
    @GetMapping("/retryCustom")
    public Map<String, String> retryRequestCustom() {
        return badRequest("RETRY_CUSTOM");
    }

    @RateLimiter(name = "default", fallbackMethod = "fallbackRequest")
    @GetMapping("/rateLimit")
    public Map<String, String> rateLimitRequest() {
        return goodRequest("RATE_LIMIT");
    }

    // It will send failureRateThreshold to main MS. If it crossed it will return fallback
    // First few request will get "I/O error on GET request for "http://localhost:9090/doesn-not-exits": Connection refused"
    // Then it will get "CircuitBreaker 'default' is OPEN and does not permit further calls"
    @CircuitBreaker(name = "default")
    @GetMapping("/circuitBreaker")
    public Map<String, String> circuitBreakerRequest() {
        return badRequest("CIRCUIT_BREAKER");
    }

    @Bulkhead(name = "custom")
    @GetMapping("/bulkHead")
    public Map<String, String> bulkHeadRequest() throws InterruptedException {
        Thread.sleep(5000);
        return goodRequest("BULKHEAD");
    }

    // Lets assume URL NON_EXISTENT_URL isn't available i.e Server is Down or Not reachable temporarily
    private Map<String, String> badRequest(String type) {

        String detail = "Hello World!";

        log.info("Before calling URL: {}, as: {}", NON_EXISTENT_URL, type);

        ResponseEntity<String> response = new RestTemplate().getForEntity(NON_EXISTENT_URL, String.class);
        detail = response.getBody();

        log.info("After calling URL: {}, as: {}", NON_EXISTENT_URL, type);

        Map<String, String> data = new HashMap<>();
        data.put("message", detail);

        return data;
    }

    public  Map<String, String> goodRequest(String type) {
        log.info("Calling Good Request as: {}", type);
        Map<String, String> data = new HashMap<>();
        data.put("message", "Hello World (Good)!");

        return data;
    }

    public  Map<String, String> fallbackRequest(Exception e) {
        Map<String, String> data = new HashMap<>();
        data.put("message", "Hello World (Fallback)!");

        return data;
    }
}
