package test.nz.ac.wgtn.swen225.lc.domain;

import java.util.Optional;
import nz.ac.wgtn.swen225.lc.domain.Entity;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.Tile;

public interface Shorthands {
  static Point p(long x, long y) {
    return new Point(x, y);
  }

  static Tile et(long x, long y){
    return et(p(x, y));
  }

  static Tile et(Point loc) {
    return new Tile() {
      Optional<Entity> oc = Optional.empty();

      @Override
      public Point getLocation() {
        return loc;
      }

      @Override
      public boolean canEnter(Entity enteree) {
        return getOccupant().isEmpty();
      }

      @Override
      public void enter(Entity enteree) {
        enter(enteree);
      }

      @Override
      public void put(Entity enteree) {
        oc = Optional.of(enteree);
      }

      @Override
      public void leave(Entity exitee) {
        if (oc.map(e -> e.equals(exitee)).orElse(false)) {
          oc = Optional.empty();
        }
      }

      @Override
      public Optional<Entity> getOccupant() {
        return oc;
      }
    };
  }

  static Entity e(long x, long y) {
    return e(p(x, y));
  }

  static Entity e(Point p) {
    return new Entity() {
      Point l = p;
      long lastT = -1;

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
      public Point getLocation() {
        return l;
      }

      @Override
      public void setLocation(Point newLocation) {
        l = newLocation;
      }
    };
  }
}
