package me.ronygomes.reference.rest_huc.service;

import me.ronygomes.reference.rest_huc.domain.User;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

public class UserService {

    private static final String FETCH_USER_PREFIX_URI = "https://jsonplaceholder.typicode.com/users/";
    private static final String CREATE_USER_PREFIX_URI = "https://jsonplaceholder.typicode.com/users";

    public static User fetchUserById(int id) {

        try {
            URI uri = new URI(FETCH_USER_PREFIX_URI + id);
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;

                StringBuilder output = new StringBuilder();
                while ((inputLine = reader.readLine()) != null) {
                    output.append(inputLine);
                }

                JSONObject object = new JSONObject(output.toString());
                User user = new User();
                user.setId(object.getInt("id"));
                user.setUsername(object.getString("username"));
                user.setEmail(object.getString("email"));
                user.setFullName(object.getString("name"));

                return user;
            }

            connection.disconnect();
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static User createUser(User inputUser) {
        try {
            URI uri = new URI(CREATE_USER_PREFIX_URI);
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json");

            JSONObject requestObject = new JSONObject();
            requestObject.put("name", inputUser.getFullName());
            requestObject.put("username", inputUser.getUsername());
            requestObject.put("email", inputUser.getEmail());

            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(requestObject.toString().getBytes());
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;

                StringBuilder output = new StringBuilder();
                while ((inputLine = reader.readLine()) != null) {
                    output.append(inputLine);
                }

                JSONObject object = new JSONObject(output.toString());
                User returnedUser = new User();
                returnedUser.setId(object.getInt("id"));
                returnedUser.setUsername(object.getString("username"));
                returnedUser.setEmail(object.getString("email"));
                returnedUser.setFullName(object.getString("name"));

                return returnedUser;
            }

            connection.disconnect();
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
