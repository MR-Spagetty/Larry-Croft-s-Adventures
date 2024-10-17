package nz.ac.wgtn.swen225.lc.domain;

import java.awt.Color;
import java.util.Objects;
import nz.ac.wgtn.swen225.lc.persistency.JSONSerializable;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;
import nz.ac.wgtn.swen225.lc.persistency.JSONType;

public class Colour implements JSONSerializable<Colour> {
  public static final char[] HEX = {
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
  };
  public final Color colour;

  /**
   * @param colour
   */
  public Colour(Color colour) {
    this.colour = colour;
  }

  @Override
  public String toString() {
    String r = HEX[this.colour.getRed() / 16] + "" + HEX[this.colour.getRed() % 16];
    String g = HEX[this.colour.getGreen() / 16] + "" + HEX[this.colour.getGreen() % 16];
    String b = HEX[this.colour.getBlue() / 16] + "" + HEX[this.colour.getBlue() % 16];
    return "#" + r + g + b;
  }

  @Override
  public JSONType toJson() {
    return new JSONString(toString());
  }

  @Override
  public Colour fromJson(JSONType json) {
    Objects.requireNonNull(json, "Expected JSONString got nothing");
    if (json instanceof JSONString data) {
      return new Colour(Color.decode(data.get()));
    }
    throw new IllegalArgumentException("Invalid JSON data");
  }

  public static Colour fromJSON(JSONType json) {
    final Colour ref = new Colour(Color.black);
    return ref.fromJson(json);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Colour && obj.toString().equals(toString());
  }
}
