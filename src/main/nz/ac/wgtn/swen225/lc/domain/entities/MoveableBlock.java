package nz.ac.wgtn.swen225.lc.domain.entities;

import java.util.Optional;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.persistency.JSONLong;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONSerializable;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;
import nz.ac.wgtn.swen225.lc.persistency.JSONType;

public class MoveableBlock extends MoveableEntity implements JSONSerializable<MoveableBlock> {

  public MoveableBlock(Point location, long individualID) {
    super(location, individualID);
  }

  @Override
  public void tick(long tick) {}

  @Override
  public boolean canTouch(Entity touchee) {
    return touchee instanceof Player;
  }

  @Override
  public void touch(Entity touchee) {
    if (!canTouch(touchee)) {
      throw new IllegalArgumentException("Entity may not touch this block");
    }
    move(location().sub(touchee.location()).limit(1l));
  }

  @Override
  public MoveableBlock fromJson(JSONType json) {
    JSONObject data = (JSONObject) json;
    if (!((JSONString) data.get("type")).get().equals("Player")) {
      throw new IllegalArgumentException(
          "Incorrect data given expected Conveyor got: " + data.get("type"));
    }
    long id =
        Optional.ofNullable(data.get("indID"))
            .map(
                e -> {
                  if (!(e instanceof JSONLong)) {
                    throw new IllegalArgumentException(
                        "Expected JSONLong at \"indID\" got: " + e.getClass().getName());
                  }
                  return ((JSONLong) e).get();
                })
            .orElseThrow(() -> new IllegalArgumentException("Expected element with key \"indID\""));
    Point pos =
        Optional.ofNullable(data.get("position"))
            .map(Point::fromJSON)
            .orElseThrow(() -> new IllegalArgumentException("Expected element with key \"indID\""));
    return new MoveableBlock(pos, id);
  }

  /**
   * Deserialize a MoveableBlock from JSON statically
   * See {@link #fromJson(JSONType)} for further documentation
   */
  public static MoveableBlock fromJSON(JSONType json) {
    final MoveableBlock ref = new MoveableBlock(Point.ORIGIN, 0);
    return ref.fromJson(json);
  }
}
