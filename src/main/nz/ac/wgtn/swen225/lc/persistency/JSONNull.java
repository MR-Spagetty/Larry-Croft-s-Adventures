package nz.ac.wgtn.swen225.lc.persistency;

/**
 * Singleton class representing the JSON null value.
 *
 * <p>There is only one instance of this class, {@link #INSTANCE},
 * because all null values in JSON are the same.</p>
 */
public final class JSONNull implements JSONType {
  // Singleton instance, since all nulls are the same
  public static final JSONNull INSTANCE = new JSONNull();
  // Private constructor to prevent instantiation
  private JSONNull() {}
}