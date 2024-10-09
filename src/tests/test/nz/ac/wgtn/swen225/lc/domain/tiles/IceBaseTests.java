package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import org.junit.jupiter.api.Test;

public interface IceBaseTests extends METBaseTests {

  default void testFromBase(Point start, PlayerAction dir, Point end) {
    Stream.of(PlayerAction.values())
        .forEach(
            act -> {
              Maze scenario = getScencario();
              Player player = getPlayer(start);
              scenario.addEntity(player);
              player.queueAction(dir);
              player.tick(0);
              player.queueAction(act);
              player.tick(1);
              assertEquals(end, player.location());
            });
  }

  void fromNorth();

  void fromSouth();

  void fromEast();

  void fromWest();

  void fromOn();
}
