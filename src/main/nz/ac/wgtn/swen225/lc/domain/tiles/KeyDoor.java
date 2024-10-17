package nz.ac.wgtn.swen225.lc.domain.tiles;

import java.awt.Color;
import java.util.Optional;
import nz.ac.wgtn.swen225.lc.domain.Colour;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.entities.items.Item;
import nz.ac.wgtn.swen225.lc.domain.entities.items.Key;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONType;

public final class KeyDoor extends Door {

  private final Colour colour;

  public KeyDoor(Point location, Colour colour) {
    super(location);
    this.colour = colour;
  }

  public Color color() {
    return this.colour.colour;
  }

  @Override
  public JSONType toJson() {
    JSONObject out = (JSONObject) super.toJson();
    out.put("colour", this.colour.toJson());
    return out;
  }

  @Override
  protected boolean meetsUnlockReqs(Entity enteree) {
    if (enteree instanceof Player p) {
      return p.getInventory().stream()
          .<Key>mapMulti(
              (i, cons) -> {
                if (i instanceof Key k) {
                  cons.accept(k);
                }
              })
          .anyMatch(k -> k.colour.equals(this.colour));
    }
    return false;
  }

  @Override
  protected void onUnlock(Entity enteree) {
    assert enteree
        instanceof Player; // this is a postcondition of the unlocking that occurs in enter
    Optional<Item> consumed =
        ((Player) enteree).lose(i -> i instanceof Key k && k.colour.equals(this.colour));
    assert consumed.isPresent(); // ensure a key was consumed to unlock this door
  }
}
