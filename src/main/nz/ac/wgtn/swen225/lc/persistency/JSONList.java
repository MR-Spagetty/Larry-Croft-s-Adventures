package nz.ac.wgtn.swen225.lc.persistency;

import java.util.ArrayList;
import java.util.List;

public class JSONList implements JSONType {

  // List to hold multiple JSONType elements
  private List<JSONType> elements = new ArrayList<>();


  //public void add(JSONType element) { elements.add(element); }

  // Overload add methods for each type
  public void put(String value) { elements.add(new JSONString(value)); }
  public void put(Long value) { elements.add(new JSONLong(value)); }
  public void put(Double value) { elements.add(new JSONDouble(value)); }
  public void put(Boolean value) { elements.add(new JSONBool(value)); }
  public void put(JSONObject value) { elements.add(value); }
  public void put(JSONList value) { elements.add(value); }

  // Return the list of elements
  public List<JSONType> getElements() { return elements; }

  public void add(JSONType jsonType) {

  }

  public void put(JSONType value) {
    elements.add(value);  // Add the value to the list
  }
}
