package nz.ac.wgtn.swen225.lc.persistency;

import org.json.JSONArray;
import org.json.JSONTokener;
import java.io.File;
import java.io.IOException;
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
    return switch (jsonType) {
      case JSONObject customObject -> convertCustomJSONObjectToString((JSONObject) jsonType).toString(2);
      case JSONList customList -> convertCustomJSONListToString((JSONList) jsonType).toString(2);
      default -> throw new IllegalArgumentException("Unsupported JSONType for saving");
    };
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

    keys.forEach(key -> {
      Object value = customObject.get(key);

      switch (value) {
        case JSONObject nestedObject -> jsonObject.put(key, convertCustomJSONObjectToString((JSONObject) value));
        case JSONList nestedList -> jsonObject.put(key, convertCustomJSONListToString((JSONList) value));
        case JSONString jsonString -> jsonObject.put(key, jsonString.get());
        case JSONLong jsonLong -> jsonObject.put(key, jsonLong.get());
        case JSONDouble jsonDouble -> jsonObject.put(key, jsonDouble.get());
        case JSONBool jsonBool -> jsonObject.put(key, jsonBool.get());
        case JSONNull jsonNull -> jsonObject.put(key, org.json.JSONObject.NULL);
        default -> throw new IllegalArgumentException("Unsupported value type for key: " + key);
      }
    });
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

    customList.getElements().forEach(value -> {
      switch (value) {
        case JSONObject nestedObject -> jsonArray.put(convertCustomJSONObjectToString((JSONObject) value));
        case JSONList nestedList -> jsonArray.put(convertCustomJSONListToString((JSONList) value));
        case JSONString jsonString -> jsonArray.put(jsonString.get());
        case JSONLong jsonLong -> jsonArray.put(jsonLong.get());
        case JSONDouble jsonDouble -> jsonArray.put(jsonDouble.get());
        case JSONBool jsonBool -> jsonArray.put(jsonBool.get());
        case JSONNull jsonNull -> jsonArray.put(org.json.JSONObject.NULL);
        default -> throw new IllegalArgumentException("Unsupported value type in list");
      }
    });
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
    return switch (parsedJson) {
      case org.json.JSONObject jsonObject -> visitor.visit(jsonObject);
      case JSONArray jsonArray -> visitor.visit(jsonArray);
      default -> throw new IllegalArgumentException("Invalid JSON format");
    };
  }

  // Main method for testing purposes
  public static void main(String[] args) {
    try {
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
