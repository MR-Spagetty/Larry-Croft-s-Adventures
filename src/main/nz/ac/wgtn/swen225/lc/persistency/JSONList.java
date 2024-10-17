package nz.ac.wgtn.swen225.lc.persistency;

import java.util.ArrayList;
import java.util.List;

/**
 * JSONList class to represent an array of JSONType elements.
 * This class provides overloaded methods to add various types of elements to the list.
 */
public class JSONList implements JSONType {

  // List to hold multiple JSONType elements
  private List<JSONType> elements = new ArrayList<>();

  /**
   * Constructor to initialize a JSONList with a list of JSONType elements.
   *
   * @param elements A list of JSONType elements.
   */
  public JSONList(List<JSONType> elements){
    this.elements = elements;
  }

  /**
   * Default constructor to create an empty JSONList.
   */
  public JSONList(){}

  /**
   * Add a String value to the JSONList as a JSONString.
   *
   * @param value The String value to be added.
   */
  public void add(String value) { elements.add(new JSONString(value)); }

  /**
   * Add a Long value to the JSONList as a JSONString.
   *
   * @param value The Long value to be added.
   */
  public void add(Long value) { elements.add(new JSONLong(value)); }

  /**
   * Add a Double value to the JSONList as a JSONDouble.
   *
   * @param value The Double value to be added.
   */
  public void add(Double value) { elements.add(new JSONDouble(value)); }

  /**
   * Add a Boolean value to the JSONList as a JSONBool.
   *
   * @param value The Boolean value to be added.
   */
  public void add(Boolean value) { elements.add(JSONBool.of(value)); }

  /**
   * Add a JSONObject to the JSONList.
   *
   * @param value The JSONObject to be added.
   */
  public void add(JSONObject value) { elements.add(value); }

  /**
   * Add a JSONList to this JSONList (nested list structure).
   *
   * @param value The JSONList to be added.
   */
  public void add(JSONList value) { elements.add(value); }


  /**
   * Get the list of JSONType elements in this JSONList.
   *
   * @return A list of JSONType elements.
   */
  public List<JSONType> getElements() { return elements; }

  /**
   * Add a generic JSONType value to the JSONList.
   *
   * @param value The JSONType value to be added.
   */
  public void add(JSONType value) {
    elements.add(value);
  }

  /**
   * Returns a string representation of the JSONList.
   *
   * @return String representation of the list.
   */
  @Override
  public String toString() { return "JSONList: " + elements.toString(); }
}
