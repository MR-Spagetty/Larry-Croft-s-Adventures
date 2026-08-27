package nz.ac.wgtn.swen225.lc.persistency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;

/**
 * A class representing a JSON object that stores key-value pairs where keys are strings, and values
 * are of type {@code JSONType}. This class dynamically handles various JSON data types such as
 * strings, numbers, booleans, nulls, objects, and arrays.
 */
public class JSONObject implements JSONType { // Not record as JSONObject is dynamic
  // Internal map to store JSON key-value pairs
  private Map<String, JSONType> data = new HashMap<>();

  public void clear() {
    data.clear(); // clear the interal map
  }

  /**
   * Adds or updates a key-value pair in the JSON object. Automatically wraps the value in the
   * appropriate {@code JSONType}.
   *
   * @param key the key under which the value is stored.
   * @param value the value to store, which may be a primitive or custom object.
   * @throws IllegalArgumentException if the value type is not supported.
   */
  public void put(String key, Object value) throws IllegalArgumentException {
    if (value == null) {
      data.put(key, JSONNull.INSTANCE); // Handle null values
    } else if (value instanceof Integer) {
      data.put(key, new JSONLong(((Integer) value).longValue())); // Convert Integer to JSONLong
    } else if (value instanceof BigDecimal) {
      data.put(
          key,
          new JSONDouble(((BigDecimal) value).doubleValue())); // Convert BigDecimal to JSONDouble
    } else if (value instanceof Boolean) {
      data.put(key, JSONBool.of((Boolean) value));
    } else if (value instanceof String) {
      data.put(key, new JSONString((String) value));
    } else if (value instanceof JSONObject) {
      data.put(key, (JSONType) value);
    } else if (value instanceof List) { // Handle List as JSONList
      data.put(key, new JSONList((List<JSONType>) value));
      // } else if (value.getClass().getSimpleName().equals("JSONArray")) {  // Check for JSONArray
      // data.put(key, new JSONList(convertJSONArrayToList((JSONArray) value))); // Implement this
      // method
    } else if (value instanceof JSONType) {
      data.put(key, (JSONType) value);
    } else {
      throw new IllegalArgumentException(
          "Unsupported value type: " + value.getClass().getSimpleName());
    }
  }

  /**
   * Converts a {@code JSONArray} into a {@code List<JSONType>}, wrapping elements appropriately
   * based on their types.
   *
   * @param jsonArray the external JSON array to convert.
   * @return the converted list of {@code JSONType} objects.
   */
  public static List<JSONType> convertJSONArrayToList(JSONArray jsonArray) {
    List<JSONType> list = new ArrayList<>();
    for (Object item : jsonArray) {
      // You need to check the type of each item and wrap it as needed
      if (item instanceof String) {
        list.add(new JSONString((String) item));
      } else if (item instanceof Integer) {
        list.add(new JSONLong(((Integer) item).longValue())); // Convert Integer to JSONLong
      } else if (item instanceof BigDecimal) {
        list.add(
            new JSONDouble(((BigDecimal) item).doubleValue())); // Convert BigDecimal to JSONDouble
      } else if (item instanceof Boolean) {
        list.add(JSONBool.of((Boolean) item));
      } else if (item instanceof JSONObject) {
        list.add((JSONObject) item);
      } else {
        //  handling for other
      }
    }
    return list;
  }

  /**
   * Returns the set of keys in this JSON object.
   *
   * @return a set of keys present in this object.
   */
  public Set<String> keySet() {
    return data.keySet();
  }

  /**
   * Retrieves the value associated with the given key.
   *
   * @param key the key for which the value is to be retrieved.
   * @return the value stored under the given key, or {@code null} if no such key exists.
   */
  public JSONType get(String key) {
    return data.get(key);
  }

  /**
   * Returns a string representation of the JSON object.
   *
   * @return a string in the format {@code JSONObject: {key=value, ...}}.
   */
  @Override
  public String toString() {
    return "JSONObject: " + data.toString();
  }
}
