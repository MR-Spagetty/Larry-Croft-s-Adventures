package nz.ac.wgtn.swen225.lc.domain.entities.items;

import java.awt.Color;
import nz.ac.wgtn.swen225.lc.domain.Colour;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;
import nz.ac.wgtn.swen225.lc.persistency.JSONType;

public class Key extends Item {

  private final Colour colour;

  public Key(Point location, long individualID, Colour colour) {
    super(location, individualID);
    this.colour = colour;
  }

  public Color color() {
    return this.colour.colour;
  }

  @Override
  protected Item item(Point location, long id) {
    throw new UnsupportedOperationException("Key requires additional data");
  }

  @Override
  public JSONType toJson() {
    JSONObject out = (JSONObject) super.toJson();
    out.put("colour", colour.toJson());
    return out;
  }

  @Override
  public Item fromJson(JSONType json) {

    JSONObject data = (JSONObject) json;
    if (!((JSONString) data.get("type")).get().equals(getClass().getName())) {
      throw new IllegalArgumentException(
          "Incorrect data given expected Conveyor got: " + data.get("type"));
    }
    return new Key(
        Point.fromJSON(data.get("position")),
        Entity.idFromJSON(data),
        Colour.fromJSON(data.get("colour")));
  }
}
