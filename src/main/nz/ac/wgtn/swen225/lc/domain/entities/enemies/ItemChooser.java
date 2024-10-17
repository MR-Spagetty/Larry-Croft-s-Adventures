package nz.ac.wgtn.swen225.lc.domain.entities.enemies;

import java.util.function.Predicate;
import nz.ac.wgtn.swen225.lc.domain.entities.items.Item;

/**
 * ItemChooser is a simple utility class to aid in the yoinking of items from the player by the
 * {@link BitFlipper} enemy
 */
class ItemChooser implements Predicate<Item> {
  private final int n;
  private int curr = 0;

  /**
   * Creates a new ItemChooser to select the nth item
   *
   * @param choice
   */
  public ItemChooser(int choice) {
    this.n = choice;
  }

  @Override
  public boolean test(Item t) {
    return curr++ == n;
  }
}