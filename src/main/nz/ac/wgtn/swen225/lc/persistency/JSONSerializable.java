package nz.ac.wgtn.swen225.lc.persistency;

public interface JsonSerializable <T>{
  String toJson();  // Serialize object to JSON
  T fromJson(String json);  // Deserialize object from JSON
}