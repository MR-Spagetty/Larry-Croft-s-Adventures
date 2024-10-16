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
  public Path nextLevelPath;
  int tick = 0;

  /**
   * Takes in a path and parse it and set it to actions. Then intitilizes a game.
   */
  Replay(Path p){
    Objects.requireNonNull(p);
    //TODO: call parse and set action equal to the result as well as initilize a game
  }

  /**
   * Initilizes a level with App and give it the path to the level file.
   */
  private void initLevel(){
    //TODO: initilize level
  }

  /**
   * Sends the input related to the current tick then advances that game by a tick.
   */
  protected void advanceTick(){
    App.forwardActionToDomain(actions.get(tick));
    App.tickOverride();
    tick++;
    checkLevelEnd();
  }

  /**
   * Checks if the replay for this level has finished and setup next level if there is one.
   */
  private boolean checkLevelEnd(){
    return tick < actions.size();
  }
  
  /**
   * Sends inputs to App and ticks the game in different ways depending on the implementation
   */
  public abstract void replay();
}