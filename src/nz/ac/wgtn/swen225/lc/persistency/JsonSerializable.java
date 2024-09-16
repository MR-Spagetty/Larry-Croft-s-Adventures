public interface JsonSerializable {
  String toJson();  // Serialize object to JSON
  void fromJson(String json);  // Deserialize object from JSON
}
