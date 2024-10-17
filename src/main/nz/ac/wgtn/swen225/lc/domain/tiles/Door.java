package nz.ac.wgtn.swen225.lc.domain.tiles;

import java.util.Optional;
import nz.ac.wgtn.swen225.lc.domain.Colour;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.persistency.JSONBool;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONType;

public abstract sealed class Door extends AbstractTile implements ModifiableTile
    permits TreasureDoor, KeyDoor {

  public Door(Point location) {
    super(location);
  }

  private boolean locked = false;

  /**
   * @return the locked state of the door
   */
  public boolean locked() {
    return this.locked;
  }

  /**
   * checks if the requirements to unlock this door are met
   *
   * @param enteree the entity whom may attempt to unlock this door
   * @return whether hte requirements are met or not
   */
  protected abstract boolean meetsUnlockReqs(Entity enteree);

  /**
   * performs any required actions associated with unlocking this door
   *
   * @param enteree the entity whom unlocked the door
   */
  protected abstract void onUnlock(Entity enteree);

  @Override
  public final boolean canEnter(Entity enteree) {
    return (meetsUnlockReqs(enteree) || !locked()) && super.canEnter(enteree);
  }

  @Override
  public final void enter(Entity enteree) {
    super.enter(enteree);
    if (locked()) {
      this.locked = false;
      onUnlock(enteree);
    }
  }

  @Override
  public JSONType toJson() {
    JSONObject out = new JSONObject();
    out.put("position", location().toJson());
    out.put("locked", locked ? JSONBool.True : JSONBool.False);
    return out;
  }

  @Override
  public ModifiableTile fromJson(JSONType json) {
    JSONObject data = (JSONObject) json;
    Point pos = Point.fromJSON(data.get("position"));
    boolean locked =
        Optional.ofNullable(data.get("locked"))
            .map(
                lck -> {
                  if (lck instanceof JSONBool) {
                    throw new IllegalArgumentException(
                        "Expected JSONBoolean at \"locked\" got: " + lck.getClass().getName());
                  }
                  return ((JSONBool) lck).get();
                })
            .orElse(true);
    return switch (this) {
      case TreasureDoor td -> {
        Door d = new TreasureDoor(pos);
        d.locked = locked;
        yield d;
      }
      case KeyDoor kd -> {
        Door d = new KeyDoor(pos, Colour.fromJSON(data.get("colour")));
        d.locked = locked;
        yield d;
      }
    };
  }
}
