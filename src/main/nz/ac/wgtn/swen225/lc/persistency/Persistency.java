package nz.ac.wgtn.swen225.lc.persistency;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import nz.ac.wgtn.swen225.lc.persistency.*;
import org.json.JSONTokener;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class Persistency {

  // Load JSONType from file
  public static JSONType loadFromFile(String filePath) throws IOException {
    // Read the JSON string from the file
    File file = new File(filePath);

    if (!file.exists()) {
      throw new IOException("File not found: " + filePath);  // Handle cases where file doesn't exist
    }

    String json = FileUtils.readFileToString(new File(filePath), "UTF-8");
    //json = "[" + json + "]";

    return parseJSONString(json);
  }

  //Save JSONType to a file
  public static void saveToFile(JSONType jsonType, String filePath) throws IOException {
    // Convert the custom JSONType back to a JSON string

    String jsoString = convertToJSONString(jsonType);

    // Write the JSON string to the specified file
    FileUtils.writeStringToFile(new File(filePath), jsoString, "UTF-8");
  }

  private static String convertToJSONString(JSONType jsonType) {
    if (jsonType instanceof JSONObject) {

      // Convert custom JSONObject to string
      return convertCustomJSONObjectToString((JSONObject) jsonType).toString(2);

    } else if (jsonType instanceof JSONList) {

      // Convert custom JSONList to string
      return convertCustomJSONListToString((JSONList) jsonType).toString(2);

    } else {

      throw new IllegalArgumentException("Unsupported JSONType for saving");

    }

  }

  private static org.json.JSONObject convertCustomJSONObjectToString(JSONObject customObject) {
    org.json.JSONObject jsonObject = new org.json.JSONObject();

    Set<String> keys = customObject.keySet();

    // Go over customObject keys and the vales and add them to JSONObject
    for (String key : keys){ //org.json.JSONObject.getNames(customObject)) {
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

  // Convert custom JSONList to org.json.JSONArray
  private static JSONArray convertCustomJSONListToString(JSONList customList) {
    JSONArray jsonArray = new JSONArray();

    // Iterate over the custom list and convert each item
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

  // Parse the raw JSON string to determine if it's an object or array
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

    // Throw an error if neither object nor array
    throw new IllegalArgumentException("Invalid JSON format");
  }

  // Parse a string and convert it into a custom JSONObject
  private static JSONObject parseJSONObject(String jsonString) {
    org.json.JSONObject jsonObject = new org.json.JSONObject(jsonString);  // Parse string into JSONObject
    JSONObject customObject = new JSONObject();  // Our custom JSONObject

    for (String key : jsonObject.keySet()) {
      Object value = jsonObject.get(key);
      customObject.put(key, value);
    }
    return customObject;
  }

  // Parse a string and convert it into a custom JSONList

  // Parse a string and convert it into a custom JSONList
  static JSONList parseJSONArray(String jsonString) {
    JSONArray jsonArray = new JSONArray(jsonString);  // Parse string into JSONArray
    JSONList customList = new JSONList();  // Our custom JSONList

    for (int i = 0; i < jsonArray.length(); i++) {
      Object value = jsonArray.get(i);
      //System.out.println("Value at index " + i + ": " + value + " (Type: " + value.getClass().getSimpleName() + ")");  // Debug line

      // Recursively handle nested objects or arrays
      if (value instanceof JSONList) {
        customList.add(parseJSONObject(value.toString()));
      } else if (value instanceof JSONArray) {
        customList.add(parseJSONArray(value.toString()));
      } else if (value instanceof String) {
        customList.add((String) value);
      } else if (value instanceof Integer) {
        customList.add(new JSONLong(((Integer) value).longValue())); // Convertins integer to long
      } else if (value instanceof Long) {
        customList.add(new JSONLong((Long) value));  // Use JSONLong directly
      } else if (value instanceof BigDecimal) {
        customList.add(new JSONDouble(((BigDecimal) value).doubleValue()));  // Convert BigDecimal to JSONDouble
      } else if (value instanceof Double) {
        customList.add(new JSONDouble((Double) value));  // Use JSONDouble directly
      } else if (value instanceof Boolean) {
        customList.add((Boolean) value);
      } else if (value == org.json.JSONObject.NULL){
        customList.add(JSONNull.INSTANCE);  // Handle null values
      }
    }

    return customList;
  }

  public static void main(String[] args) {
    try{
      // Simple test loading JSON Object
      System.out.println("Testing Object load:");
      JSONType jsonObject = Persistency.loadFromFile("src/main/nz/ac/wgtn/swen225/lc/persistency/testObject.json");
      System.out.println(jsonObject); //

      // Save the modified object back to a new file
      Persistency.saveToFile(jsonObject, "src/main/nz/ac/wgtn/swen225/lc/persistency/outputObject.json");
      System.out.println("Modified object saved to 'outputObject.json'.");

      // Simple test loading a JSON Array
      System.out.println("\nTesting Array load:");
      JSONType jsonArray = Persistency.loadFromFile("src/main/nz/ac/wgtn/swen225/lc/persistency/testArray.json");
      System.out.println(jsonArray); //

      // Save the modified array back to a new file
      Persistency.saveToFile(jsonArray, "src/main/nz/ac/wgtn/swen225/lc/persistency/outputArray.json");
      System.out.println("Modified array saved to 'outputArray.json'.");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
