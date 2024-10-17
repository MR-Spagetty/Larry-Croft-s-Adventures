package nz.ac.wgtn.swen225.lc.domain.entities.enemies;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONSerializable;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;
import nz.ac.wgtn.swen225.lc.persistency.JSONType;

public class Bug extends Enemy implements JSONSerializable<Bug> {

  public Bug(Point location, long individualID) {
    super(location, individualID);
  }

  @Override
  protected void doBehaviour(long tick) {
    try {
      move(PlayerAction.values()[behaviourDecider.nextInt(5)].offset);
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
}
