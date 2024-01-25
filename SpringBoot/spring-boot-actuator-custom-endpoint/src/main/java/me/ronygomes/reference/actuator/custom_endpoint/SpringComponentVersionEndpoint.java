package me.ronygomes.reference.actuator.custom_endpoint;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.core.SpringVersion;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "version")
public class SpringComponentVersionEndpoint {
    
    @ReadOperation
    public Map<String, Map<String, String>> versions() {
        Map<String, String> versions = new HashMap<>();

        versions.put("spring-boot", SpringBootVersion.getVersion());
        versions.put("spring-core", SpringVersion.getVersion());
        versions.put("spring-security-core", SpringSecurityCoreVersion.getVersion());

        return Map.of("versions", versions);
    }

}
