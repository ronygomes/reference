package me.ronygomes.reference.springboot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/* 
 * Note
 *  - This class need to be in same or sub packges of Application.java for auto scan
 *  - @RestController = @Controller + @ResponseBody. So no need to add in method level
 */
@RestController
public class HelloWorldController {
    
    // curl http://localhost:8080/hello-world-text
    // Old Style: @RequestMapping(method = RequestMethod.GET, value = "/hello-world-texts")
    // Output: Hello World!
    @GetMapping(path = "/hello-world-text")
    public String helloWorldText() {
        return "Hello World!";
    }

    // Output: {"message":"Hello World!"}
    @GetMapping(path = "/hello-world-map")
    public Map<String, String> helloWorldMap() {
        Map<String, String> m = new HashMap<>();
        m.put("message", "Hello World!");

        return m;
    }

    // Output: {"message":"Hello World!"}
    @GetMapping(path = "/hello-world-pojo")
    public Message helloWorldPojo() {
        return new Message("Hello World!");
    }
}
