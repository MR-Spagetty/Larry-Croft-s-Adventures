package nz.ac.wgtn.swen225.lc.persistency;

public enum JSONBool implements JSONType {
  //
  True,
  False;

  static JSONBool of (Boolean b){
    return b ? True : False;
  }
}
