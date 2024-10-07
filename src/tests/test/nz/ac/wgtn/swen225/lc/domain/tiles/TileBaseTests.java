package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.*;

import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.tiles.Empty;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import org.junit.jupiter.api.Test;
public interface TileBaseTests {
  Tile tile();

  @Test
  default void goodConst(){
    tile();
  }
}
