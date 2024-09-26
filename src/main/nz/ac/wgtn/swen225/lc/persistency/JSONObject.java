package nz.ac.wgtn.swen225.lc.persistency;

import java.util.*;

public class JSONObject implements JSONType {

  // Map to hold key-value pairs
  private Map<String, Object> data = new HashMap<>();

  // Add key-value pairs to the JSON object
  public void add(String key, String value) { data.put(key, value); }

  public void add(String key, Long value) { data.put(key, value); }

  public void add(String key, Double value) { data.put(key, value); }

  public void add(String key, Boolean value) { data.put(key, value); }

  public void add(String key, Object value) { data.put(key, value);
    // Supports null or other types
  }

  // Get a value by key
  public Object get(String key) { return data.get(key); }

}
