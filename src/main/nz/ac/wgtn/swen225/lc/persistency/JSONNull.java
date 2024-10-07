package nz.ac.wgtn.swen225.lc.persistency;

// JSONNull class to represent null values in JSON
public final class JSONNull implements JSONType {

  // Singleton instance, since all nulls are the same
  public static final JSONNull INSTANCE = new JSONNull();

  // Private constructor to prevent instantiation
  private JSONNull() {}

  @Override
  public String toString() {
    return "null";
  }
}