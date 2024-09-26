package nz.ac.wgtn.swen225.lc.persistency;

import com.google.gson.Gson;
import nz.ac.wgtn.swen225.lc.persistency.JSONType;

import java.util.HashMap;
import java.util.Map;

// This allows selective serialization of key-value pairs.
// For e.g. only certain fields of a Player object might need to be saved to JSON.

public class JSONLong implements JSONType {
  private static Map<String, Long> dataForObject = new HashMap<>();
  // Strings, booleans, int, longs etc.
  public void add(String key, Long value) {
    dataForObject.put(key, value);
  }

  @Override
  public String toJson() {
    // Convert the map to JSON using a library like Gson, or manually
    return new Gson().toJson(dataForObject);
  }

  public Object get(String x) {
    return x;
  }
}