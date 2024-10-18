package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.MoveableBlock;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.entities.items.Flippers;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import nz.ac.wgtn.swen225.lc.domain.tiles.Water;
import org.junit.jupiter.api.Test;

public class WaterTests implements TileBaseTests {

  @Override
  public Tile tile() {
    return new Water(Point.ORIGIN);
  }

  @Test
  void kill() {
    Maze scene = new Maze(0, " ", 0);
    Player p = new Player(Point.ORIGIN, 0);
    p.maze(scene);
    Tile t = tile();
    scene.addTile(t);
    t.enter(p);
    assert p.isDead();
  }

  @Test
  void fill() {
    Maze scene = new Maze(0, " ", 0);
    Tile t = tile();
    scene.addTile(t);
    MoveableBlock block = new MoveableBlock(Point.ORIGIN, 0);
    assert !((Water)t).filled();
    t.enter(block);
    assert ((Water)t).filled();
  }
  @Test
  void enterFilled() {
    Maze scene = new Maze(0, " ", 0);
    Player p = new Player(Point.ORIGIN, 0);
    p.maze(scene);
    Tile t = tile();
    scene.addTile(t);
    MoveableBlock block = new MoveableBlock(Point.ORIGIN, 0);
    t.enter(block);
    t.enter(p);
    assert !p.isDead();
  }
  @Test
  void enterWithFlip() {
    Maze scene = new Maze(0, " ", 0);
    Player p = new Player(Point.ORIGIN, 0);
    p.maze(scene);
    Flippers flip = new Flippers(Point.ORIGIN, 0);
    p.pickUp(flip);
    Tile t = tile();
    scene.addTile(t);
    t.enter(p);
    assert !p.isDead();
  }
}
