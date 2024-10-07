//package nz.ac.wgtn.swen225.lc.persistency;
//
//import com.google.gson.Gson;
//
//import javax.swing.text.Position;
//import java.util.List;
//
//public class examplePlayer{
//  private static String name;
//  private static Position position;
//  private static List<String> inventory;
//  //private Stats stats;
//
//  // Constructor, getters, and setters...
//
//
//  public JSONObject serialize() {
//    JSONObject jsonObject = new JSONObject();
//    jsonObject.add("name", name);
//
//    // This is assuDeveloper 4 <dev4@example.internal> Position class also implement custom serialization
//    jsonObject.add("position", position.serialize());
//    return jsonObject;
//  }
//
//  // Custom deserialization
//  public static examplePlayer deserialize(JSONObject jsonObject) {
//    String name = (String) jsonObject.get("name");
//
//    // AssuDeveloper 4 <dev4@example.internal> Position class implements the custom deserialization
//    Position position = Position.deserialize((JSONObject) jsonObject.get("position"));
//
//
//    return new examplePlayer();
//  }
//}
//
