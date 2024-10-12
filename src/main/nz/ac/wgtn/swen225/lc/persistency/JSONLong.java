package nz.ac.wgtn.swen225.lc.persistency;

public record JSONLong(long value) implements JSONType {
  public Long get() {
    return value;
  }
  //
}
