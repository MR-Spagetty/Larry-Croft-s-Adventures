package nz.ac.wgtn.swen225.lc.persistency;

/**
 * Record class representing a JSON double value.
 *
 * <p>This class is used to encapsulate a double value in the JSON structure.</p>
 *
 * @param value the double value
 */
public record JSONDouble(Double value) implements JSONType {

  /**
   * Returns the encapsulated double value.
   *
   * @return the double value.
   */
  public Double get() {
    return value;
  }
}