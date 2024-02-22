package me.ronygomes.reference.oauth2jwt.util;

import me.ronygomes.reference.oauth2jwt.command.JwtPayloadParts;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestHelper {

    private static final String KEYCLOAK_CLIENT_ID = "webclient";
    private static final String KEYCLOAK_REALM = "ss-kc";
    private static final int KEYCLOAK_PORT = 8080;
    private static final String KEYCLOAK_TOKEN_ENDPOINT = "http://localhost:%d/realms/%s/protocol/openid-connect/token"
            .formatted(KEYCLOAK_PORT, KEYCLOAK_REALM);

    private static HttpClient client = HttpClient.newHttpClient();

    public static HttpRequest.BodyPublisher bodyPublisherOfMapFromData(Map<String, String> map) {

        String form = map.entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        return HttpRequest.BodyPublishers.ofString(form);
    }

    public static Map<String, String> createFormDataMapForUser(String username, String password) {
        Map<String, String> formData = new HashMap<>();

        formData.put("client_id", KEYCLOAK_CLIENT_ID);
        formData.put("grant_type", "password");
        formData.put("username", username);
        formData.put("password", password);

        return formData;
    }

    public static String fetchAccessToken(String username, String password) throws IOException, InterruptedException, JSONException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(KEYCLOAK_TOKEN_ENDPOINT))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(RequestHelper.bodyPublisherOfMapFromData(createFormDataMapForUser(username, password)))
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body()).getString("access_token");
    }

    public static JwtPayloadParts parseJwtPayload(String jwtToken) throws JSONException {
        var jwtTokenPayloadPart = jwtToken.split("\\.")[1];

        var jwtPayloadJson = new String(Base64.getUrlDecoder().decode(jwtTokenPayloadPart));
        var jsonObj = new JSONObject(jwtPayloadJson);

        String preferredUsername = jsonObj.getString("preferred_username");
        JSONArray roleJsonArray = jsonObj.getJSONObject("resource_access")
                .getJSONObject(KEYCLOAK_CLIENT_ID)
                .getJSONArray("roles");

        String[] roles = new String[roleJsonArray.length()];
        for (int i = 0; i < roleJsonArray.length(); i++) {
            roles[i] = roleJsonArray.getString(i);
        }

        Arrays.sort(roles);
        return new JwtPayloadParts(preferredUsername, roles);
    }
}
