package test.nz.ac.wgtn.swen225.lc.domain.entities.enemies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.IAE;

import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.enemies.ItemChooser;
import nz.ac.wgtn.swen225.lc.domain.entities.items.IceBoots;
import nz.ac.wgtn.swen225.lc.domain.entities.items.Item;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

public class ItemChooserTests {
  @Test
  void goodCons() {
    new ItemChooser(5);
  }

  @Test
  void badCons() {
    assertThrows(IAE, () -> new ItemChooser(-5));
  }

  @Test
  void selection(){
    final int toSelect = 42;
    List<Item> is = IntStream.range(0, 50).mapToObj(i -> (Item)new IceBoots(Point.ORIGIN, 0)).toList();
    Item expected = is.get(toSelect);
    List<Item> selected = is.stream().filter(new ItemChooser(toSelect)).toList();
    assertEquals(1, selected.size());
    assertEquals(expected, selected.get(0));
    assert selected.get(0) == expected;
  }
}