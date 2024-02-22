package me.ronygomes.reference.oauth2jwt.controller;

import me.ronygomes.reference.oauth2jwt.command.JwtPayloadParts;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.time.Duration;

import static me.ronygomes.reference.oauth2jwt.util.RequestHelper.fetchAccessToken;
import static me.ronygomes.reference.oauth2jwt.util.RequestHelper.parseJwtPayload;

/* Performant alternative is to do Unit Test with @WithMockUser or .with(oidcLogin())
 * But I am more interested with integration between spring-security and keycloak
 * Reference: https://docs.spring.io/spring-security/reference/servlet/test/mockmvc/oauth2.html
 */
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerTest {

    private static final File DOCKER_COMPOSE_FILE = new File("docker-compose.yml");

    private static final String KEYCLOAK_SERVICE_NAME = "keycloak";
    private static final int KEYCLOAK_PORT = 8080;

    private static final String USERNAME_ADMIN = "app-admin";
    private static final String USERNAME_USER = "user";
    private static final String USERNAME_ALL_ROLE = "all-role";
    private static final String GENERIC_PASSWORD = "12345";
    @Container
    public static ComposeContainer DOCKER_COMPOSE = new ComposeContainer(DOCKER_COMPOSE_FILE)
            .withLocalCompose(true)
            .withExposedService(KEYCLOAK_SERVICE_NAME, KEYCLOAK_PORT,
                    Wait.forListeningPort().withStartupTimeout(Duration.ofMinutes(3)));
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @AfterAll
    public static void stopContainers() {
        DOCKER_COMPOSE.stop();
    }

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void testUserAccess() throws Exception {

        String jwtToken = fetchAccessToken(USERNAME_USER, GENERIC_PASSWORD);
        JwtPayloadParts payloadParts = parseJwtPayload(jwtToken);

        Assertions.assertEquals(USERNAME_USER, payloadParts.preferredUsername());
        Assertions.assertArrayEquals(new String[]{"user"}, payloadParts.roles());

        String publicContent = mockMvc.perform(MockMvcRequestBuilders.get("/public")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals("Public Page", publicContent);

        String privateContent = mockMvc.perform(MockMvcRequestBuilders.get("/private")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals("Private Page! Username: user, Role: USER", privateContent);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-only")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().is(403)); // Forbidden


        mockMvc.perform(MockMvcRequestBuilders.get("/private")
                        .header("Authorization", "Bearer Invalid Token"))
                .andExpect(MockMvcResultMatchers.status().is(401));
    }

    @Test
    public void testAdminAccess() throws Exception {

        String jwtToken = fetchAccessToken(USERNAME_ADMIN, GENERIC_PASSWORD);
        JwtPayloadParts payloadParts = parseJwtPayload(jwtToken);

        Assertions.assertEquals(USERNAME_ADMIN, payloadParts.preferredUsername());
        Assertions.assertArrayEquals(new String[]{"admin"}, payloadParts.roles());

        String publicContent = mockMvc.perform(MockMvcRequestBuilders.get("/public")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals("Public Page", publicContent);

        String privateContent = mockMvc.perform(MockMvcRequestBuilders.get("/private")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals("Private Page! Username: app-admin, Role: ADMIN", privateContent);

        String adminOnlyContent = mockMvc.perform(MockMvcRequestBuilders.get("/admin-only")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals("Admin Only Page", adminOnlyContent);

        mockMvc.perform(MockMvcRequestBuilders.get("/private")
                        .header("Authorization", "Bearer Invalid Token"))
                .andExpect(MockMvcResultMatchers.status().is(401));
    }

    @Test
    public void testAllRoleAccess() throws Exception {

        String jwtToken = fetchAccessToken(USERNAME_ALL_ROLE, GENERIC_PASSWORD);
        JwtPayloadParts payloadParts = parseJwtPayload(jwtToken);

        Assertions.assertEquals(USERNAME_ALL_ROLE, payloadParts.preferredUsername());
        Assertions.assertArrayEquals(new String[]{"admin", "user"}, payloadParts.roles());

        String publicContent = mockMvc.perform(MockMvcRequestBuilders.get("/public")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals("Public Page", publicContent);

        String privateContent = mockMvc.perform(MockMvcRequestBuilders.get("/private")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals("Private Page! Username: all-role, Role: ADMIN,USER", privateContent);

        String adminOnlyContent = mockMvc.perform(MockMvcRequestBuilders.get("/admin-only")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals("Admin Only Page", adminOnlyContent);

        mockMvc.perform(MockMvcRequestBuilders.get("/private")
                        .header("Authorization", "Bearer Invalid Token"))
                .andExpect(MockMvcResultMatchers.status().is(401));
    }
}