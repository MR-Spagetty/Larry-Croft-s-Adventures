package nz.ac.wgtn.swen225.lc.domain.tiles;

import java.util.Optional;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;

/**
 * Walls are basic tiles that may not be occupied
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com>
 */
public record Wall(Point location) implements Tile {

  @Override
  public void leave(Entity exitee) {
    return;
  }

  @Override
  public boolean canEnter(Entity enteree) {
    return false;
  }

  @Override
  public void enter(Entity enteree) {
    throw new UnsupportedOperationException("This tile may never be occupied");
  }

  @Override
  public void put(Entity enteree) {
    throw new UnsupportedOperationException("This tile may never be occupied");
  }

  @Override
  public Optional<Entity> getOccupant() {
    return Optional.empty();
  }
}
