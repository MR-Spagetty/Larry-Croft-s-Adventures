package nz.ac.wgtn.swen225.lc.persistency;

import java.math.BigDecimal;

public class JSONParserVisitor {

  public JSONObject visit(org.json.JSONObject jsonObject) {

    JSONObject output = new JSONObject();

    // Parse object, check for nested lists/objects
    for (String key : org.json.JSONObject.getNames(jsonObject)) {
      Object value = jsonObject.get(key);
      output.put(key, this.visit(value));
    }
    return output;
  }

  public JSONType visit(Object value) {
    return switch (value){
      case null -> JSONNull.INSTANCE;
      case org.json.JSONObject jo -> visit(jo);
      case org.json.JSONArray jl -> visit(jl);
      case Boolean b -> JSONBool.of(b);
      case Double d -> new JSONDouble(d);
      case Long l -> new JSONLong(l);
      case String s -> new JSONString(s);
      case Integer i -> (new JSONLong(((Integer) value).longValue()));
      case BigDecimal bd -> (new JSONDouble(((BigDecimal) value).doubleValue()));
      //case JSONBool jb -> jb ;// (new JSONBool(((JSONBool) value). )) // how to implement?
      default -> throw new IllegalArgumentException("Type \"%s\" is not valid".formatted(value.getClass()));
    };
  }

  public JSONList visit(org.json.JSONArray jsonArray) {
    JSONList output = new JSONList();
    for (Object value : jsonArray) {
      output.add(visit(value));
    }
    return output;
  }
}
