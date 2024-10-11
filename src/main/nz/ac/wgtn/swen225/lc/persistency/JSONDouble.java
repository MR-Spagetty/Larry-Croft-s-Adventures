package nz.ac.wgtn.swen225.lc.persistency;

public record JSONDouble(Double value) implements JSONType {
  public Double get() {
    return value;
  }
  //
}
