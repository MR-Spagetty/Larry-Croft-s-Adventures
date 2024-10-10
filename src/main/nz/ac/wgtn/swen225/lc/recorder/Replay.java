package nz.ac.wgtn.swen225.lc.recorder;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.app.App;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * This interface represents a replay system for a recorded game.
 */
abstract class Replay {
  public List<PlayerAction> actions;
  int tick = 0;

  /*
   * Takes in a path and parse it and set it to actions. Then intitilizes a game.
   */
  Replay(Path p){
    Objects.requireNonNull(p);
    //TODO: call parse and set action equal to the result as well as initilize a game
  }

  protected void advanceTick(){
    App.forwardActionToDomain(actions.get(tick));
    App.tickOverride();
    tick++;
  }
  
  /**
   * Sends inputs to App and ticks the game in different ways depending on the implementation
   */
  public abstract void replay();
}