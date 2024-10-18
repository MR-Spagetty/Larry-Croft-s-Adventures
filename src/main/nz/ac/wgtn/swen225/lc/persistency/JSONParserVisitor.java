package nz.ac.wgtn.swen225.lc.persistency;

import java.math.BigDecimal;

/**
 * Class responsible for visiting and parsing JSON objects and arrays.
 *
 * <p>This class converts external JSON types from the {@code org.json} package into
 * the internal types defined by the persistency package. It handles objects, arrays,
 * strings, numbers, booleans, and null values, including nested JSON structures.</p>
 */
public class JSONParserVisitor {

  /**
   * Visits and converts an {@code org.json.JSONObject} into the internal {@code JSONObject}.
   *
   * @param jsonObject the external JSON object to be converted.
   * @return the converted internal {@code JSONObject}.
   */
  public JSONObject visit(org.json.JSONObject jsonObject) {
    JSONObject output = new JSONObject();

    // Parse object, check for nested lists/objects
    for (String key : org.json.JSONObject.getNames(jsonObject)) {
      Object value = jsonObject.get(key);
      output.put(key, this.visit(value));
    }
    return output;
  }

  /**
   * Visits and converts a generic object from the external JSON library into
   * the internal {@code JSONType}.
   *
   * @param value the external JSON value to be converted.
   * @return the corresponding internal {@code JSONType}.
   * @throws IllegalArgumentException if the object type is not supported.
   */
  public JSONType visit(Object value) throws IllegalArgumentException {
    return switch (value){
      case null -> JSONNull.INSTANCE;
      case org.json.JSONObject jo -> visit(jo);
      case org.json.JSONArray jl -> visit(jl);
      case Boolean b -> JSONBool.of(b);
      case Double d -> new JSONDouble(d);
      case Long l -> new JSONLong(l);
      case String s -> new JSONString(s);
      case Integer i -> new JSONLong(i.longValue()); // Convert Integer to Long
      case BigDecimal bd -> new JSONDouble(bd.doubleValue()); // Convert BigDecimal to Double

      default -> throw new IllegalArgumentException("Type \"%s\" is not valid".formatted(value.getClass()));
    };
  }

  /**
   * Visits and converts an {@code org.json.JSONArray} into the internal {@code JSONList}.
   *
   * @param jsonArray the external JSON array to be converted.
   * @return the converted internal {@code JSONList}.
   */
  public JSONList visit(org.json.JSONArray jsonArray) {
    JSONList output = new JSONList();

    // Parse array, check for nested objects
    for (Object value : jsonArray) {
      output.add(visit(value));
    }
    return output;
  }
}
