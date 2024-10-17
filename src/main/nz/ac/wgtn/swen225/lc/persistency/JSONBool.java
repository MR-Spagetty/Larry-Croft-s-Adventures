package nz.ac.wgtn.swen225.lc.persistency;

/**
 * Enum representing JSON boolean values.
 *
 * <p>This enum has two values: {@code True} and {@code False}, representing
 * the boolean values in JSON. It also provides utility methods for converting
 * between Java's {@link Boolean} and the enum values.</p>
 */
public enum JSONBool implements JSONType {

  True,
  False;

  /**
   * Converts a Java {@link Boolean} to a {@code JSONBool}.
   *
   * @param b the boolean value
   * @return {@code True} if the boolean is true, otherwise {@code False}.
   */
  static JSONBool of (Boolean b){
    return b ? True : False;
  }

  /**
   * Returns the boolean value represented by this enum.
   *
   * @return {@code true} if this is {@code True}, otherwise {@code false}.
   */
  public Boolean get(){
    return this == True;
  }
}