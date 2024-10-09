package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static nz.ac.wgtn.swen225.lc.domain.PlayerAction.None;
import static nz.ac.wgtn.swen225.lc.domain.Point.ORIGIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.IAE;

import java.util.List;
import java.util.stream.Stream;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.tiles.DirectionalIce;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import org.junit.jupiter.api.Test;

public sealed interface DirectionalIceTests extends IceBaseTests
    permits DirIceNETests, DirIceSETests, DirIceSWTests, DirIceNWTests {
  default Tile tile(int facing) {
    return new DirectionalIce(Point.ORIGIN, facing);
  }

  @Test
  default void fromOn() {
    DirectionalIce t = (DirectionalIce) tile();
    Stream.of(t.type.a, t.type.b)
        .forEach(
            act -> {
              Maze scenario = getScenario();
              Player p = getPlayer(ORIGIN);
              scenario.addEntity(p);
              p.queueAction(act);
              p.tick(0);
              assertEquals(act.offset, p.location());
            });
    Stream.of(PlayerAction.values())
        .filter(act -> !List.of(None, t.type.a, t.type.b).contains(act))
        .forEach(
            act -> {
              Maze scenario = getScenario();
              Player p = getPlayer(ORIGIN);
              scenario.addEntity(p);
              p.queueAction(act);
              assertThrows(IAE, () -> p.tick(0));
            });
  }

  @Override
  void alreadyOccupiedEnter();
}
