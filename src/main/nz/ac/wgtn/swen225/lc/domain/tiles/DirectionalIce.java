package nz.ac.wgtn.swen225.lc.domain.tiles;

import static nz.ac.wgtn.swen225.lc.domain.PlayerAction.*;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.MoveableEntity;

/** more advanced Ice tile that redirects the entity */
public class DirectionalIce extends Ice {
  /**
   * Enum for representing the type of DirectionalIce to create and the directions it redirects to
   * and from
   */
  public enum TYPE {
    NorthEast(Up, Right),
    SouthEast(Down, Right),
    SouthWest(Down, Left),
    NorthWest(Up, Left);

    final Point a;
    final Point b;

    TYPE(PlayerAction sideA, PlayerAction sideB) {
      this.a = sideA.offset;
      this.b = sideB.offset;
    }
  }

  public final TYPE type;

  /**
   * creates a new Directional ice tile of the given type
   *
   * @param location where to create the tile
   * @param type the type to create
   */
  public DirectionalIce(Point location, TYPE type) {
    super(location);
    this.type = type;
  }

  /**
   * creates a new Directional ice tile with the given type ID
   *
   * @param location where to create the tile
   * @param typeID the type ID to use
   */
  public DirectionalIce(Point location, int typeID) {
    this(location, TYPE.values()[typeID]);
  }

  /**
   * affects the given movement of the given entity to obey the properties of ice and redirection
   *
   * <p>Similar to the {@link Ice} tile this tile also enforces the properties of ice but also
   * redirects the movement of the entity to leave through the side of the tile the entity did not
   * enter through depending on the type
   */
  @Override
  public Point affectMove(MoveableEntity e, Point moveToEffect) {
    final Point atSideA = this.type.a.add(location());
    final Point atSideB = this.type.b.add(location());
    Point ePreLoc = e.location().sub(e.lastMove());
    if (ePreLoc.equals(atSideA)) {
      return this.type.b;
    }
    if (ePreLoc.equals(atSideB)) {
      return this.type.a;
    }
    return super.affectMove(e, moveToEffect);
  }

  @Override
  public boolean canEnter(Entity enteree) {
    return super.canEnter(enteree)
        && (enteree.location().equals(location().add(this.type.a))
            || enteree.location().equals(location().add(this.type.b)));
  }
}
