package me.ronygomes.reference.activemq.util;

import java.io.*;
import java.util.Base64;

public class TextSerializableHelper {

    public static String serialize(Object target) {
        var baos = new ByteArrayOutputStream();
        try (var oos = new ObjectOutputStream(baos)) {
            oos.writeObject(target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    public static <T> T deserialize(String text) {
        Object deserializedObject;

        try (var ois = new ObjectInputStream(
                new ByteArrayInputStream(Base64.getDecoder().decode(text)))) {

            deserializedObject = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return (T) deserializedObject;
    }
}
