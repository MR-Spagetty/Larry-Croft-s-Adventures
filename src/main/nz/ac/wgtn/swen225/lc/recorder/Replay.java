package nz.ac.wgtn.swen225.lc.recorder;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;

import java.nio.file.Path;
import java.util.List;

/**
 * This interface represents a replay system for a recorded game,
 */
abstract class Replay {
  public List<PlayerAction> actions = null;

  Replay(Path p){

  }
  /**
   * Sends inputs to App and ticks the game in different ways depending on the implementation
   */
  public void replay();
}