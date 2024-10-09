package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.tiles.DirectionalIce;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;

public sealed interface DirectionalIceTests extends IceBaseTests
    permits DirIceNETests, DirIceSETests, DirIceSWTests, DirIceNWTests {
  default Tile tile(int facing) {
    return new DirectionalIce(Point.ORIGIN, facing);
  }
}
