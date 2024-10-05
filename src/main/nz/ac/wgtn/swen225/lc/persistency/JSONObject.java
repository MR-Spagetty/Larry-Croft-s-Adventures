package nz.ac.wgtn.swen225.lc.persistency;

import java.util.*;

public class JSONObject implements JSONType { // Not record as JSONObject is dynamic

  // Map to hold key value pairs
  private Map<String, JSONType> data = new HashMap<>();

  // Add key-value pairs to the JSON object by wrapping them in JSONType
  public void put(String key, String value) { data.put(key, new JSONString(value)); }
  public void put(String key, Long value) { data.put(key, new JSONLong(value)); }
  public void put(String key, Double value) { data.put(key, new JSONDouble(value)); }
  public void put(String key, Boolean value) { data.put(key, new JSONBool(value)); }
  public void put(String key) { data.put(key, JSONNull.INSTANCE); } // Null handling like a pro kachow
  public void put(String key, JSONObject value) { data.put(key, value); }
  public void put(String key, JSONList value) { data.put(key, value); }

  // Get a value by key
  public JSONType get(String key) { return data.get(key); }

  // Type-specific retrieval methods
  public String getString(String key) {
    JSONType value = data.get(key);
    if (value instanceof JSONString) {
      return ((JSONString) value).value();
    }
    throw new IllegalArgumentException("Value is not a String");
  }

  public Long getLong(String key) {
    JSONType value = data.get(key);
    if (value instanceof JSONLong) {
      return ((JSONLong) value).value();
    }
    throw new IllegalArgumentException("Value is not a Long");
  }

  public Double getDouble(String key) {
    JSONType value = data.get(key);
    if (value instanceof JSONDouble) {
      return ((JSONDouble) value).value();
    }
    throw new IllegalArgumentException("Value is not a Double");
  }

  public Boolean getBoolean(String key) {
    JSONType value = data.get(key);
    if (value instanceof JSONBool) {
      return ((JSONBool) value).value();
    }
    throw new IllegalArgumentException("Value is not a Boolean");
  }

  public JSONObject getJSONObject(String key) {
    JSONType value = data.get(key);
    if (value instanceof JSONObject) {
      return (JSONObject) value;
    }
    throw new IllegalArgumentException("Value is not a JSONObject");
  }

  public JSONList getJSONArray(String key) {
    JSONType value = data.get(key);
    if (value instanceof JSONList) {
      return (JSONList) value;
    }
    throw new IllegalArgumentException("Value is not a JSONList");
  }

  @Override
  public String toString() {
    return "JSONObject: " + data.toString();
  }


}