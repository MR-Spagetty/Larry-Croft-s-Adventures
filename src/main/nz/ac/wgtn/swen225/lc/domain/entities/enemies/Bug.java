package nz.ac.wgtn.swen225.lc.domain.entities.enemies;

import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONSerializable;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;
import nz.ac.wgtn.swen225.lc.persistency.JSONType;

/**
 * Bug is a basic enemy that moves around the map randomly
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com>
 */
public class Bug extends Enemy implements JSONSerializable<Bug> {

  /**
   * creates a new bug at the given position with the given individual id
   *
   * @param location the position to create the bug at
   * @param individualID the individual id of the bug
   */
  public Bug(Point location, long individualID) {
    super(location, individualID);
  }

  @Override
  protected void doBehaviour(long tick) {
    try {
      move(dirs.get(behaviourDecider.nextInt(Enemy.dirs.size())));
    } catch (IllegalArgumentException iae) {
    } catch (UnsupportedOperationException uoe) {
    }
  }

  @Override
  public void touch(Entity touchee) {
    super.touch(touchee);
    if (touchee instanceof Player p) {
      p.die();
    }
  }

  @Override
  public Bug fromJson(JSONType json) {
    JSONObject data = (JSONObject) json;
    String type = ((JSONString) data.get("type")).get();
    if (!type.equals("Bug")) {
      throw new IllegalArgumentException("Incorrect data given expected Bug got: " + type);
    }
    return new Bug(Point.fromJSON(data.get("Position")), Entity.idFromJSON(data));
  }

  /** statically deserializes a Bug enemy for documentation see {@link #fromJson(JSONType)} */
  public static Bug fromJSON(JSONType json) {
    final Bug ref = new Bug(Point.ORIGIN, 0);
    return ref.fromJson(json);
  }
}
