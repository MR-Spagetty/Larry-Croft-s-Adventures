package nz.ac.wgtn.swen225.lc.persistency;

import com.google.gson.Gson;

import javax.swing.text.Position;
import java.util.List;

public record examplePlayer() implements JsonSerializable {
  private static String name;
  private static Position position;
  private static List<String> inventory;
  //private Stats stats;

  // Constructor, getters, and setters...

  @Override
  public String toJson() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }

  @Override
  public void fromJson(String json) {
    Gson gson = new Gson();
    examplePlayer player = gson.fromJson(json, examplePlayer.class);

    //this.stats = player.stats;
  }
}

