package nz.ac.wgtn.swen225.lc.persistency;

import java.util.ArrayList;
import java.util.List;

public class JSONArray implements JSONType {

  // List to hold multiple JSONType elements
  private List<JSONType> elements = new ArrayList<>();


  //public void add(JSONType element) { elements.add(element); }

  // Overload add methods for each type
  public void add(String value) { elements.add(new JSONString(value)); }
  public void add(Long value) { elements.add(new JSONLong(value)); }
  public void add(Double value) { elements.add(new JSONDouble(value)); }
  public void add(Boolean value) { elements.add(new JSONBool(value)); }
  public void add(JSONObject value) { elements.add(value); }
  public void add(JSONArray value) { elements.add(value); }

  // Return the list of elements
  public List<JSONType> getElements() { return elements; }
}
