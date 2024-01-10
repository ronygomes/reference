package me.ronygomes.reference.rest_huc.service;

import me.ronygomes.reference.rest_huc.domain.Comment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommentService {

    private static final String COMMENT_URI_PREFIX = "https://jsonplaceholder.typicode.com/comments?postId=";

    public static List<Comment> fetchAllComments(int postId) {

        try {
            URI uri = new URI(COMMENT_URI_PREFIX + postId);
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

                JSONArray result = new JSONArray(output.toString());

                List<Comment> comments = new ArrayList<>();
                for (int i = 0; i < result.length(); i++) {
                    JSONObject object = result.getJSONObject(i);
                    Comment comment = new Comment();
                    comment.setId(object.getInt("id"));
                    comment.setBody(object.getString("body"));
                    comment.setEmail(object.getString("email"));
                    comment.setName(object.getString("name"));

                    comments.add(comment);
                }

                return comments;
            }

            connection.disconnect();
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }

        return Collections.emptyList();
    }
}
