package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/** A simple class that helps serialize and deserialize JSON objects in various formats and inputs */
public final class JsonUtils {
    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }
    /** Deserialize a JSON from a String to a given type reference */
    public static <T> T fromString(String jsonString, TypeReference<T> type) throws JsonProcessingException {
        return new ObjectMapper().readValue(jsonString, type);
    }
    /** Deserialize a JSON from a String to a given class type */
    public static <T> T fromString(String jsonString, Class<T> type) throws JsonProcessingException {
        return new ObjectMapper().readValue(jsonString, type);
    }
    /** Deserialize a JSON from a File to a given class type */
    public static <T> T fromFile(File file, Class<T> type) throws IOException {
        return new ObjectMapper().readValue(file, type);
    }
    /** Deserialize JSON from a file to a given type reference */
    public static <T> T fromFile(File file, TypeReference<T> type) throws IOException {
        return new ObjectMapper().readValue(file, type);
    }
    /** Deserialize JSON from a stream to a given type reference */
    public static <T> T fromFile(InputStream jsonStream, TypeReference<T> type) throws IOException {
        return new ObjectMapper().readValue(jsonStream, type);
    }
    /** Deserialize JSON from a stream to a given class type */
    public static <T> T fromFile(InputStream jsonStream, Class<T> type) throws IOException {
        return new ObjectMapper().readValue(jsonStream, type);
    }
    /** Deserialize JSON from a File to a given class type */
    public static <T> T fromFile(String filePath, Class<T> type) throws IOException {
        return new ObjectMapper().readValue(new File(filePath), type);
    }
    /** Serializes a given object instance into JSON and returns it as a String */
    public static String toString(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
}