package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.*;

import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.tiles.Exit;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import org.junit.jupiter.api.Test;
public class ExitTests extends EmptyTests{
  @Override
  public Tile tile() {
    return ex(0, 0);
  }
}
