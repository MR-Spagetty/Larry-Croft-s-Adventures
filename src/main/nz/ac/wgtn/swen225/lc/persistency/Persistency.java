package nz.ac.wgtn.swen225.lc.persistency;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.IOException;

public class Persistency {

  // Load JSONType from file
  public static JSONType loadFromFile(String filename) throws IOException {
    // Read the JSON string from the file
    String json = FileUtils.readFileToString(new File(filename), "UTF-8");

    // Check if the string represents a JSONObject or JSONArray
    return parseJSONString(json);
  }

  // Parse the raw JSON string to determine if it's an object or array
  private static JSONType parseJSONString(String json) {
    // Use JSONTokener to determine the structure of the string
    JSONTokener tokener = new JSONTokener(json);
    Object parsedJson = tokener.nextValue();

    // If it's a JSON object, call the object parser
    if (parsedJson instanceof JSONObject) {
      return (JSONType) parseJSONObject(json);  // Send raw string to parser
    } else if (parsedJson instanceof JSONArray) {
      return parseJSONArray(json);  // Send raw string to parser
    }

    // Throw an error if neither object nor array
    throw new IllegalArgumentException("Invalid JSON format");
  }

  // Parse a string and convert it into a custom JSONObject
  private static JSONObject parseJSONObject(String jsonString) {
    JSONObject jsonObject = new JSONObject(jsonString);  // Parse string into JSONObject
    JSONObject customObject = new JSONObject();  // Our custom JSONObject

    for (String key : jsonObject.keySet()) {
      Object value = jsonObject.get(key);

      // Recursively handle nested objects or arrays
      if (value instanceof JSONObject) {
        customObject.put(key, parseJSONObject(value.toString()));
      } else if (value instanceof JSONArray) {
        customObject.put(key, parseJSONArray(value.toString()));
      } else if (value instanceof String) {
        customObject.put(key, (String) value);
      } else if (value instanceof Long) {
        customObject.put(key, (Long) value);
      } else if (value instanceof Double) {
        customObject.put(key, (Double) value);
      } else if (value instanceof Boolean) {
        customObject.put(key, (Boolean) value);
      } else {
        customObject.put(key, 0);  // Handle null values
      }
    }

    return customObject;
  }

  // Parse a string and convert it into a custom JSONList
  private static JSONList parseJSONArray(String jsonString) {
    JSONArray jsonArray = new JSONArray(jsonString);  // Parse string into JSONArray
    JSONList customList = new JSONList();  // Our custom JSONList

    for (int i = 0; i < jsonArray.length(); i++) {
      Object value = jsonArray.get(i);

      // Recursively handle nested objects or arrays
      if (value instanceof JSONList) {
        customList.put(parseJSONObject(value.toString()));
      } else if (value instanceof JSONArray) {
        customList.put(parseJSONArray(value.toString()));
      } else if (value instanceof String) {
        customList.put((String) value);
      } else if (value instanceof Long) {
        customList.put((Long) value);
      } else if (value instanceof Double) {
        customList.put((Double) value);
      } else if (value instanceof Boolean) {
        customList.put((Boolean) value);
      } else {
        customList.add(null);  // Handle null values
      }
    }

    return customList;
  }
}
