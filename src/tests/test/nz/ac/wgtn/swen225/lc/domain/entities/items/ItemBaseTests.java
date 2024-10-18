package test.nz.ac.wgtn.swen225.lc.domain.entities.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.*;

import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.entities.enemies.Bug;
import org.junit.jupiter.api.Test;
import test.nz.ac.wgtn.swen225.lc.domain.Shorthands;
import test.nz.ac.wgtn.swen225.lc.domain.entities.EntityBaseTests;

public interface ItemBaseTests extends EntityBaseTests {
  @Test
  default void serialEqual() {
    Maze scene = new Maze(0, " ", 0);
    Entity a = entity();
    a.maze(scene);
    Entity b = Entity.fromJSON(a.toJson());
    b.maze(scene);
    assertEquals(a.location(), b.location());
    assertEquals(a.getUID(), b.getUID());
  }

  @Test
  default void playerCanTouch() {
    Player p = new Player(Point.ORIGIN, 0);
    assert entity().canTouch(p);
  }

  @Test
  default void playerTouch() {
    Maze scene = new Maze(0, " ", 0);
    area3x3(scene, Shorthands::et, 0, 0);
    Entity e = entity();
    scene.addEntity(e);
    Player p = new Player(p(0, -1), 0);
    scene.addEntity(p);
    e.touch(p);
    assert scene.getTile(Point.ORIGIN).get().getOccupant().isEmpty();
    assert p.getInventory().contains(e);
  }

  @Test
  default void mayNotTouch() {
    Entity e = new Bug(Point.ORIGIN, 0);
    assert !entity().canTouch(e);
  }

  @Test
  default void mayNotAttemptTouch() {
    Entity e = new Bug(East, 0);
    assertThrows(IAE, () -> entity().touch(e));
  }
}
