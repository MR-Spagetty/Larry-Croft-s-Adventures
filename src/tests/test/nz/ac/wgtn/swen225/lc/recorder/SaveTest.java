package test.nz.ac.wgtn.swen225.lc.recorder;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.recorder.*;

public class SaveTest {
  @Test
  public void test1(){
    Path level = Paths.get("test/level");
    Path savePath = Paths.get("src/main/nz/ac/wgtn/swen225/lc/recorder/docs");
    String name = "SaveTest.json";
    List<PlayerAction> actions = List.of(PlayerAction.Up, PlayerAction.Down, PlayerAction.Left, PlayerAction.Right);
    
    Level l = new Level(name, actions, savePath, level);
    l.nextSavePath(Paths.get("next/save/path"));
    l.save();
  }
}
