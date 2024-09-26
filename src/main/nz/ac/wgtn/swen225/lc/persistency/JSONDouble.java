package nz.ac.wgtn.swen225.lc.persistency;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

// This allows selective serialization of key-value pairs.
// For e.g. only certain fields of a Player object might need to be saved to JSON.

public class JSONDouble implements JsonSerializable {
  private static Map<String, Double> dataForObject = new HashMap<>();
  // Strings, booleans, int, longs etc.
  public void add(String key, Double value) {
    dataForObject.put(key, value);
  }

  @Override
  public String toJson() {
    // Convert the map to JSON using a library like Gson, or manually
    return new Gson().toJson(dataForObject);
  }

  @Override
  public void fromJson(String json) {

  }

  public Object get(String x) {
    return x;
  }
}