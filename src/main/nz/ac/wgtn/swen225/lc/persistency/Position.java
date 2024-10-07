//package nz.ac.wgtn.swen225.lc.persistency;
//
//public class Position {
//  private int x;
//  private int y;
//
//  public Position(int x, int y) {
//    this.x = x;
//    this.y = y;
//  }
//
//  public JSONObject serialize() {
//    JSONObject jsonObject = new JSONObject();
//    jsonObject.add("x", (long) this.x);
//    jsonObject.add("y", (long) this.y);
//    return jsonObject;
//  }
//
//  public static Position deserialize(JSONObject jsonObject) {
//    int x = (Integer) jsonObject.get("x");
//    int y = (Integer) jsonObject.get("y");
//    return new Position(x, y);
//  }
//}
//
