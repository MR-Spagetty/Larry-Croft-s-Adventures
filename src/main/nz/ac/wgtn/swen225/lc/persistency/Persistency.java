package nz.ac.wgtn.swen225.lc.persistency;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

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

  // Parse the raw JSON string to determine if it's an object or array
  private static JSONType parseJSONString(String json) {
    // Use JSONTokener to determine the structure of the string
    JSONTokener tokener = new JSONTokener(json);
    Object parsedJson = tokener.nextValue();

    // Creating a visitor to handle the parsing and conversion stuff
    JSONParserVisitor visitor = new JSONParserVisitor();

    // If it's a JSON object, call the object parser
    if (parsedJson instanceof JSONObject) {
      return visitor.visit(parsedJson);  // Send the org.json.JSONObject to visitor
    } else if (parsedJson instanceof JSONArray) {
      return visitor.visit(parsedJson);  // Send the org.json.JSONArray to visitor
    }

    // Throw an error if neither object nor array
    throw new IllegalArgumentException("Invalid JSON format");
  }

  // Parse a string and convert it into a custom JSONObject
  private static nz.ac.wgtn.swen225.lc.persistency.JSONObject parseJSONObject(String jsonString) {
    JSONObject jsonObject = new JSONObject(jsonString);  // Parse string into JSONObject
     nz.ac.wgtn.swen225.lc.persistency.JSONObject customObject = new nz.ac.wgtn.swen225.lc.persistency.JSONObject();  // Our custom JSONObject

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
      } else if (value == JSONObject.NULL){
        customList.add(JSONNull.INSTANCE);  // Handle null values
      }
    }

    return customList;
  }

  public static void main(String[] args) {
    try{
      // Simple test loading JSON Object
      System.out.println("Testing Object load:");
      JSONType jsonObject = Persistency.loadFromFile("testObject.json");
      System.out.println(jsonObject); //

      // Simple test loading a JSON Array
      System.out.println("\nTesting Array load:");
      JSONType jsonArray = Persistency.loadFromFile("src/main/nz/ac/wgtn/swen225/lc/persistency/testArray.json");
      System.out.println(jsonArray); //

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
