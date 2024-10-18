package nz.ac.wgtn.swen225.lc.persistency;

public interface JSONSerializable <T>{
  JSONType toJson();  // Serialize object to JSON
  T fromJson(JSONType json);  // Deserialize object from JSON
}