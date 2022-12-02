package me.ronygomes.reference.springboot;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.springframework.ui.ModelMap;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.i18n.LocaleContextHolder;

@RestController
public class GreetController {
    
    private MessageSource messageSource;

    public GreetController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/greet")
    public Map<String, String> greet() {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("greeting.text", null, null, locale);

        Map<String, String> model = new HashMap<>();
        model.put("message", message);

        return model;
    }
}
