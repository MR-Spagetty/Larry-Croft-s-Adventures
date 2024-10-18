package test.nz.ac.wgtn.swen225.lc.domain.entities;


import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import org.junit.jupiter.api.Test;

public interface EntityBaseTests {
  public Entity entity();

  @Test
  default void goodConst() {
    entity();
  }

  @Test
  default void goodAssignMaze() {
    Entity e = entity();
    e.maze(new Maze(0, " ", 0));
  }

  @Test
  default void badAssignMaze() {
    Entity e = entity();
    Maze a = new Maze(0, " ", 0);
    e.maze(a);
    try {
      e.maze(new Maze(0, " ", 0));
    } catch (Throwable t) {
    }
    assert e.maze() == a;
  }

  void badDeserializeData();

  public static JSONObject badData() {
    JSONObject badData = new JSONObject();
    badData.put("type", "nonExistant");
    return badData;
  }
}
