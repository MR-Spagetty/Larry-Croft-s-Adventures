package nz.ac.wgtn.swen225.lc.persistency;

import java.util.ArrayList;
import java.util.List;

public class JSONList implements JSONType {

  // List to hold multiple JSONType elements
  private List<JSONType> elements = new ArrayList<>();


  //public void add(JSONType element) { elements.add(element); }

  // Overload add methods for each type
  public void add(String value) { elements.add(new JSONString(value)); }
  public void add(Long value) { elements.add(new JSONLong(value)); }
  public void add(Double value) { elements.add(new JSONDouble(value)); }
  public void add(Boolean value) { elements.add(new JSONBool(value)); }
  public void add(JSONObject value) { elements.add(value); }
  public void add(JSONList value) { elements.add(value); }
  //public void add(JSONNull value) {elements.add(new JSONNull(value))}

  // Return the list of elements
  public List<JSONType> getElements() { return elements; }

  public void add(JSONType value) {
    elements.add(value);  // Add the value to the list
  }

  @Override
  public String toString() {
    return "JSONList: " + elements.toString();
  }

}
