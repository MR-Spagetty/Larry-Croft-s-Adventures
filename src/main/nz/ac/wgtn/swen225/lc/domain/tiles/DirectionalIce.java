package nz.ac.wgtn.swen225.lc.domain.tiles;

import static nz.ac.wgtn.swen225.lc.domain.PlayerAction.*;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.MoveableEntity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.entities.items.IceBoots;
import nz.ac.wgtn.swen225.lc.persistency.JSONLong;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;

/**
 * more advanced Ice tile that redirects the entity
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com>
 */
public class DirectionalIce extends Ice {
  /**
   * Enum for representing the type of DirectionalIce to create and the directions it redirects to
   * and from
   */
  public enum TYPE {
    /** Signifies a tile where the Northern and Eastern faces are open */
    NorthEast(Up, Right),
    /** Signifies a tile where the Southern and Eastern faces are open */
    SouthEast(Down, Right),
    /** Signifies a tile where the Southern and Western faces are open */
    SouthWest(Down, Left),
    /** Signifies a tile where the Northern and Western faces are open */
    NorthWest(Up, Left);

    /** the first side of the tile that is open */
    public final PlayerAction a;

    /** the second side of the tile that is open */
    public final PlayerAction b;

    TYPE(PlayerAction sideA, PlayerAction sideB) {
      this.a = sideA;
      this.b = sideB;
    }
  }

  /**
   * the type if directional ice this DirectionalIce tile is
   *
   * @see TYPE
   */
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
   * @param typeID the type ID to use see {@link TYPE} for number ot use for which variant
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
    final Point atSideA = this.type.a.offset.add(location());
    final Point atSideB = this.type.b.offset.add(location());
    Point ePreLoc = e.location().sub(e.lastMove());
    if (ePreLoc.equals(location())
        && (moveToEffect.limit(1l).equals(this.type.a.offset.mul(-1l))
            || moveToEffect.limit(1l).equals(this.type.b.offset.mul(-1l)))) {
      return Point.ORIGIN;
    }
    if (e instanceof Player p
        && p.getInventory().parallelStream().anyMatch(i -> i instanceof IceBoots)) {
      return moveToEffect;
    }
    if (ePreLoc.equals(atSideA)) {
      return this.type.b.offset;
    }
    if (ePreLoc.equals(atSideB)) {
      return this.type.a.offset;
    }
    return super.affectMove(e, moveToEffect);
  }

  @Override
  public boolean canEnter(Entity enteree) {
    return super.canEnter(enteree)
        && (enteree.location().equals(location().add(this.type.a.offset))
            || enteree.location().equals(location().add(this.type.b.offset)));
  }

  /**
   * Deserializes a directional ice tile from the given data
   *
   * @param json the json data to use
   * @return the deserialized tile
   * @throws IllegalArgumentException if the json data is incorrect for a DirectionalIce tile
   */
  public static DirectionalIce fromJSON(JSONObject json) {
    if (!((JSONString) json.get("tile")).get().equals("DirectionalIce")) {
      throw new IllegalArgumentException(
          "Incorrect data given expected DirectionalIce got: " + json.get("Tile"));
    }
    if (!(json.get("type") instanceof JSONLong)) {
      throw new IllegalArgumentException(
          "Expected long type got: " + json.get("type").getClass().getName());
    }
    return new DirectionalIce(
        Point.fromJSON(json.get("position")),
        Integer.parseInt("" + (((JSONLong) json.get("type")).get())));
  }
}
