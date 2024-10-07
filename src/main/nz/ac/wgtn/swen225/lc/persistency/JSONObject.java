package nz.ac.wgtn.swen225.lc.persistency;

import org.json.JSONArray;

import java.math.BigDecimal;
import java.util.*;

public class JSONObject implements JSONType { // Not record as JSONObject is dynamic

  // Map to hold key value pairs
  private Map<String, JSONType> data = new HashMap<>();

  // Add key-value pairs to the JSON object by wrapping them in JSONType

  public void put(String key, Object value) {
    if (value == null) {
      data.put(key, JSONNull.INSTANCE);  // Handle null values
    } else if (value instanceof Long) {
      data.put(key, new JSONLong((Long) value));
    } else if (value instanceof Double) {
      data.put(key, new JSONDouble((Double) value));
    } else if (value instanceof Integer) {
      data.put(key, new JSONLong(((Integer) value).longValue()));  // Convert Integer to JSONLong
    } else if (value instanceof BigDecimal) {
      data.put(key, new JSONDouble(((BigDecimal) value).doubleValue()));  // Convert BigDecimal to JSONDouble
    } else if (value instanceof Boolean) {
      data.put(key, JSONBool.of((Boolean) value));
    } else if (value instanceof String) {
      data.put(key, new JSONString((String) value));
    } else if (value instanceof JSONObject) {
      data.put(key, (JSONType) value);
    } else if (value instanceof List) {  // Handle List as JSONList
      data.put(key, new JSONList((List<JSONType>) value));
    } else if (value.getClass().getSimpleName().equals("JSONArray")) {  // Check for JSONArray
      data.put(key, new JSONList(convertJSONArrayToList((JSONArray) value))); // Implement this method
//    } else if (value instanceof JSONBool) {
//      data.put(key, (JSONType) value);
//    } else if (value instanceof JSONDouble) {
//      data.put(key, (JSONType) value);
//    } else if (value instanceof JSONLong) {
//      data.put(key, (JSONType) value);
//    } else if (value instanceof JSONString) {
//      data.put(key, (JSONType) value);
    } else if (value instanceof JSONType) {
      data.put(key, (JSONType) value);
    } else {
      throw new IllegalArgumentException("Unsupported value type: " + value.getClass().getSimpleName());
    }
  }


  // Method to convert JSONArray to List
  private List<JSONType> convertJSONArrayToList(JSONArray jsonArray) {
    List<JSONType> list = new ArrayList<>();
    for (Object item : jsonArray) {
      // You need to check the type of each item and wrap it as needed
      if (item instanceof String) {
        list.add(new JSONString((String) item));
      } else if (item instanceof Long) {
        list.add(new JSONLong((Long) item));
      } else if (item instanceof Double) {
        list.add(new JSONDouble((Double) item));
      } else if (item instanceof Integer) {
        list.add(new JSONLong(((Integer) item).longValue())); // Convert Integer to JSONLong
      } else if (item instanceof BigDecimal) {
        list.add(new JSONDouble(((BigDecimal) item).doubleValue())); // Convert BigDecimal to JSONDouble
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

  public JSONBool getBoolean(Boolean b) {
    JSONType value = data.get(b);
    if (value instanceof JSONBool) {
      return (JSONBool.of(b));
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