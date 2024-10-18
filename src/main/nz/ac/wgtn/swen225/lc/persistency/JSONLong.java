package nz.ac.wgtn.swen225.lc.persistency;

/**
 * Record class representing a JSON long value.
 *
 * <p>This class is used to encapsulate a long value in the JSON structure.</p>
 *
 * @param value the long value
 */
public record JSONLong(long value) implements JSONType {

  /**
   * Returns the encapsulated long value.
   *
   * @return the long value.
   */
  public Long get() {
    return value;
  }
}