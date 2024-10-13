package test.nz.ac.wgtn.swen225.lc.recorder;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.recorder.*;

public class LoadTest {

  @Test
  public void test1(){
    Level l = Parser.parse(Paths.get("src/main/nz/ac/wgtn/swen225/lc/recorder/docs/RecorderExample.json"));
    assertEquals(l.actions(), List.of(PlayerAction.Up, PlayerAction.Down, PlayerAction.Left, PlayerAction.Right));
    assertEquals(l.levelPath(), Paths.get("this/is/path"));
    assertEquals(l.nextSavePath(), Paths.get("path/to/nextLevel"));
  }
}