package nz.ac.wgtn.swen225.lc.domain.entities.items;

import java.awt.Color;
import nz.ac.wgtn.swen225.lc.domain.Colour;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;
import nz.ac.wgtn.swen225.lc.persistency.JSONType;

/**
 * Key is an item for use in unlocking doors a key with a given colour can unlock any door of teh
 * same colour
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com>
 */
public class Key extends Item {

  /** the colour of this */
  public final Colour colour;

  /**
   * creates a new key at the given position with teh given individual id and colour
   *
   * @param location the position to create the key at
   * @param individualID the individual id of the key
   * @param colour the colour of the key
   */
  public Key(Point location, long individualID, Colour colour) {
    super(location, individualID);
    this.colour = colour;
  }

  /**
   * gets the awt Colour of this key for use by renderer to tint the key appropriately
   *
   * @return the awt colour of this key
   */
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
