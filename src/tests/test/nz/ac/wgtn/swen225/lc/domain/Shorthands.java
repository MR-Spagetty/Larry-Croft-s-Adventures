package test.nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.*;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;
import nz.ac.wgtn.swen225.lc.persistency.JSONType;

public interface Shorthands {
  Class<IllegalArgumentException> IAE = IllegalArgumentException.class;
  Class<IllegalStateException> ISE = IllegalStateException.class;
  Class<UnsupportedOperationException> UOE = UnsupportedOperationException.class;
  Point North = PlayerAction.Up.offset;
  Point South = PlayerAction.Down.offset;
  Point East = PlayerAction.Right.offset;
  Point West = PlayerAction.Left.offset;

  static Point p(long x, long y) {
    return new Point(x, y);
  }

  static Tile et(long x, long y) {
    return et(p(x, y));
  }

  static Tile et(Point loc) {
    return new Empty(loc);
  }

  static Wall w(long x, long y) {
    return new Wall(p(0, 0));
  }

  static Exit ex(long x, long y) {
    return new Exit(p(0, 0));
  }

  static Entity e(long x, long y) {
    return e(p(x, y));
  }

  static long[] entityCount = {0};

  static Entity e(Point p) {
    long id = entityCount[0]++;
    return new MoveableEntity(p, id) {
      @Override
      public void tick(long tick) {
        if (tick > lastTicked()) {
          return;
        }
        this.lastTick = tick;
      }

      @Override
      public boolean canTouch(Entity touchee) {
        return false;
      }

      @Override
      public void touch(Entity touchee) {}

      @Override
      public JSONType toJson() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toJson'");
      }
    };
  }
}
