package nz.ac.wgtn.swen225.lc.persistency;

/**
 * Record class representing a JSON string value.
 *
 * <p>This class is used to encapsulate a string value in the JSON structure.</p>
 *
 * @param value the long value
 */
public record JSONString(String value) implements JSONType {

  /**
   * Returns the encapsulated long value.
   *
   * @return the long value.
   */
  public String get() {
    return value;
  }
}
