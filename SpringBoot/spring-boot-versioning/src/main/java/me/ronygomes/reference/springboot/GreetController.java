package me.ronygomes.reference.springboot;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    private static final String MESSAGE_V1 = "Hello World!";
    private static final String MESSAGE_V2 = "World, Hello";

    @GetMapping("/v1/greetUrl")
    public Map<String, String> greetUrlV1() {
        return greetV1();
    }

    @GetMapping("/v2/greetUrl")
    public Map<String, String> greetUrlV2() {
        return greetV2();
    }

    @GetMapping("/greetRequestParam")
    public Map<String, String> greetUrlRequestParam(@RequestParam int version) {
        return version == 1 ? greetUrlV1() : greetUrlV2();
    }

    @GetMapping("/greetCustomHeader")
    public Map<String, String> greetCustomHeader(@RequestHeader("X-API-Version") int version) {
        return version == 1 ? greetUrlV1() : greetUrlV2();
    }

    @GetMapping(path = "/greetCustomMimeType", produces = "application/vnd.ronygomes.me-v1+json")
    public Map<String, String> greetCustomMimeTypeV1() {
        return greetUrlV1();
    }

    @GetMapping(path = "/greetCustomMimeType", produces = "application/vnd.ronygomes.me-v2+json")
    public Map<String, String> greetCustomMimeTypeV2() {
        return greetUrlV2();
    }

    private Map<String, String> greetV1() {
        Map<String, String> data = new HashMap<>();
        data.put("message", MESSAGE_V1);

        return data;
    }

    private Map<String, String> greetV2() {
        Map<String, String> data = new HashMap<>();
        data.put("greeting", MESSAGE_V2);

        return data;
    }
}
