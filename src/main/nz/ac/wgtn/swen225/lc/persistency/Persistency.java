package nz.ac.wgtn.swen225.lc.persistency;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import nz.ac.wgtn.swen225.lc.persistency.*;
import org.json.JSONTokener;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.*;
import java.nio.file.Path;


/**
 * Persistency class is responsible for loading and saving JSON data.
 * Supports both JSONObjects and JSONLists
 */

public class Persistency {

  /**
   * Load JSONType from file using Path input.
   *
   * @param filePath The path to the file.
   * @return A custom JSONType object.
   * @throws IOException If an error occurs while reading the file.
   */
  public static JSONType loadFromFile(Path filePath) throws IOException {
    File file = filePath.toFile();
    if (!file.exists()) {
      throw new IOException("File not found: " + filePath.toString());  // Handle cases where file doesn't exist
    }
    String json = Files.readString(filePath);
    return parseJSONString(json);
  }


  /**
   * Load JSONType from file using String file path input.
   *
   * @param filePath The path to the file as a String.
   * @return A custom JSONType object.
   * @throws IOException If an error occurs while reading the file.
   */
  public static JSONType loadFromFile(String filePath) throws IOException {
    return loadFromFile(Path.of(filePath)); // Delegate to the Path method
  }

  /**
   * Save JSONType to file using Path input.
   *
   * @param jsonType The JSONType object to save.
   * @param filePath The path where the file will be saved.
   * @throws IOException If an error occurs while writing the file.
   */
  public static void saveToFile(JSONType jsonType, String filePath) throws IOException {
    saveToFile(jsonType, Path.of(filePath)); // Delegate to the Path method
  }

  /**
   * Save JSONType to file using String file path input.
   *
   * @param jsonType The JSONType object to save.
   * @param filePath The path where the file will be saved as a String.
   * @throws IOException If an error occurs while writing the file.
   */
  public static void saveToFile(JSONType jsonType, Path filePath) throws IOException {
    // Convert the custom JSONType back to a JSON string
    String jsonString = convertToJSONString(jsonType);
    Files.writeString(filePath, jsonString);
  }

  /**
   * Convert a custom JSONType into a JSON string.
   *
   * @param jsonType The JSONType object to convert.
   * @return A string representing the JSON data.
   */
  private static String convertToJSONString(JSONType jsonType) {
    if (jsonType instanceof JSONObject) { // Convert custom JSONObject to string
      return convertCustomJSONObjectToString((JSONObject) jsonType).toString(2);
    } else if (jsonType instanceof JSONList) { // Convert custom JSONList to string
      return convertCustomJSONListToString((JSONList) jsonType).toString(2);
    } else {
      throw new IllegalArgumentException("Unsupported JSONType for saving");
    }
  }

  /**
   * Convert a custom JSONObject to a org.json.JSONObject.
   *
   * @param customObject The custom JSONObject.
   * @return The converted org.json.JSONObject.
   */
  private static org.json.JSONObject convertCustomJSONObjectToString(JSONObject customObject) {
    org.json.JSONObject jsonObject = new org.json.JSONObject();
    Set<String> keys = customObject.keySet();

    // Go over customObject keys and the vales and add them to JSONObject
    for (String key : keys){
      Object value = customObject.get(key);

      if (value instanceof JSONObject) {
        jsonObject.put(key, convertCustomJSONObjectToString((JSONObject) value));
      } else if (value instanceof JSONList) {
        jsonObject.put(key, convertCustomJSONListToString((JSONList) value));
      } else if (value instanceof JSONString) {
        jsonObject.put(key, ((JSONString) value).get()); // Extract string value
      } else if (value instanceof JSONLong) {
        jsonObject.put(key, ((JSONLong) value).get()); // Extract long value
      } else if (value instanceof JSONDouble) {
        jsonObject.put(key, ((JSONDouble) value).get()); // Extract double value
      } else if (value instanceof JSONBool) {
        jsonObject.put(key, ((JSONBool) value).get()); // Extract boolean value
      } else if (value == JSONNull.INSTANCE) {
        jsonObject.put(key, org.json.JSONObject.NULL); // Handle null values
      }
    }
    return jsonObject;
  }

  /**
   * Convert a custom JSONList to an org.json.JSONArray.
   *
   * @param customList The custom JSONList.
   * @return The converted org.json.JSONArray.
   */
  private static JSONArray convertCustomJSONListToString(JSONList customList) {
    JSONArray jsonArray = new JSONArray();

    for (JSONType value : customList.getElements()) {
      if (value instanceof JSONObject) {
        jsonArray.put(convertCustomJSONObjectToString((JSONObject) value));
      } else if (value instanceof JSONList) {
        jsonArray.put(convertCustomJSONListToString((JSONList) value));
      } else if (value instanceof JSONString) {
        jsonArray.put(((JSONString) value).get()); // Extract string value
      } else if (value instanceof JSONLong) {
        jsonArray.put(((JSONLong) value).get()); // Extract long value
      } else if (value instanceof JSONDouble) {
        jsonArray.put(((JSONDouble) value).get()); // Extract double value
      } else if (value instanceof JSONBool) {
        jsonArray.put(((JSONBool) value).get()); // Extract boolean value
      } else if (value == JSONNull.INSTANCE) {
        jsonArray.put(org.json.JSONObject.NULL); // Handle null values
      }
    }
    return jsonArray;
  }

  /**
   * Parse the raw JSON string into a custom JSONType object.
   *
   * @param json The raw JSON string.
   * @return A custom JSONType object.
   */
  private static JSONType parseJSONString(String json) {
    // Use JSONTokener to determine the structure of the string
    JSONTokener tokener = new JSONTokener(json);
    Object parsedJson = tokener.nextValue();

    // Creating a visitor to handle the parsing and conversion stuff
    JSONParserVisitor visitor = new JSONParserVisitor();

    // If it's a JSON object, call the object parser
    if (parsedJson instanceof org.json.JSONObject) {
      return visitor.visit(parsedJson);  // Send the org.json.JSONObject to visitor
    } else if (parsedJson instanceof JSONArray) {
      return visitor.visit(parsedJson);  // Send the org.json.JSONArray to visitor
    }

    throw new IllegalArgumentException("Invalid JSON format");
  }

  // Main method for testing purposes
  public static void main(String[] args) {
    try{
      // Simple test loading JSON Object
      System.out.println("Testing Object load:");
      JSONType jsonObject = Persistency.loadFromFile("src/main/nz/ac/wgtn/swen225/lc/persistency/testObject.json");
      System.out.println(jsonObject); //

      // Save the modified object back to a new file
      Persistency.saveToFile(jsonObject, "src/main/nz/ac/wgtn/swen225/lc/persistency/putputObject.json");
      System.out.println("Modified object saved to 'outputObject.json'.");

      // Simple test loading a JSON Array
      System.out.println("\nTesting Array load:");
      JSONType jsonArray = Persistency.loadFromFile("src/main/nz/ac/wgtn/swen225/lc/persistency/testArray.json");
      System.out.println(jsonArray); //

      // Save the modified array back to a new file
      Persistency.saveToFile(jsonArray, "src/main/nz/ac/wgtn/swen225/lc/persistency/putputArray.json");
      System.out.println("Modified array saved to 'outputArray.json'.");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
