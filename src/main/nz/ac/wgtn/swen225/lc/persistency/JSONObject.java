package nz.ac.wgtn.swen225.lc.persistency;

import java.util.*;

public class JSONObject implements JSONType {

  // Map to hold key value pairs
  private Map<String, JSONType> data = new HashMap<>();

  // Add key-value pairs to the JSON object by wrapping them in JSONType
  public void add(String key, String value) { data.put(key, new JSONString(value)); }
  public void add(String key, Long value) { data.put(key, new JSONLong(value)); }
  public void add(String key, Double value) { data.put(key, new JSONDouble(value)); }
  public void add(String key, Boolean value) { data.put(key, new JSONBool(value)); }
  public void add(String key) { data.put(key, null); } // Only accepts null

  // Get a value by key
  public JSONType get(String key) { return data.get(key); }
}
