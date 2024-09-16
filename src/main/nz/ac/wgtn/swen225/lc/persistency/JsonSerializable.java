package nz.ac.wgtn.swen225.lc.persistency;

public interface JsonSerializable {
  String toJson();  // Serialize object to JSON
  void fromJson(String json);  // Deserialize object from JSON
}