package test.nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.MoveableEntity;
import nz.ac.wgtn.swen225.lc.domain.tiles.Empty;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;

public interface Shorthands {
  static Point p(long x, long y) {
    return new Point(x, y);
  }

  static Tile et(long x, long y) {
    return et(p(x, y));
  }

  static Tile et(Point loc) {
    return new Empty(loc);
  }

  static Entity e(long x, long y) {
    return e(p(x, y));
  }

  static Entity e(Point p) {
    return new MoveableEntity() {
      Point l = p;
      long lastT = -1;
      Maze maze = null;

      @Override
      public long lastTicked() {
        return lastT;
      }

      @Override
      public void tick(long tick) {
        if (tick > lastTicked()) {
          return;
        }
        lastT = tick;
      }

      @Override
      public Point location() {
        return l;
      }

      @Override
      public void location(Point newLocation) {
        l = newLocation;
      }

      @Override
      public Maze getMaze() {
        return maze;
      }

      @Override
      public void setMaze(Maze maze) {
        if (this.maze == null) {
          this.maze = maze;
        }
      }

      @Override
      public long getUID() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUID'");
      }

      @Override
      public boolean canTouch(Entity touchee) {
        return false;
      }

      @Override
      public void touch(Entity touchee) {}
    };
  }
}
