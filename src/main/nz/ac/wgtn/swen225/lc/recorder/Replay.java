package nz.ac.wgtn.swen225.lc.recorder;

import nz.ac.wgtn.swen225.lc.app.Recorders;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.app.App;
import nz.ac.wgtn.swen225.lc.app.UserInterface;

import java.lang.Runnable;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * This interface represents a replay system for a recorded game.
 * 
 * @author Developer 4 <dev4@example.internal> 300655226
 */
abstract class Replay {
  public List<PlayerAction> actions;
  public Path nextLevelPath;
  int tick = 0;

  /**
   * Takes in a path and parse it and set it to the global variables. Then intitilizes a game.
   */
  Replay(Path p){
    Objects.requireNonNull(p);
    Level l = Parser.parse(p);
    actions = l.actions();
    nextLevelPath = l.nextSavePath();
    initLevel(l.levelPath());
  }

  /**
   * Initilizes a level with App and give it the path to the level file.
   */
  private void initLevel(Path levelPath){
    initRecorderGame(levelPath);
  }

  /**
   * Sends the input related to the current tick then advances that game by a tick and check if the level has ended.
   */
  protected void advanceTick(Runnable nextReplay){
    App.forwardActionToDomain(actions.get(tick));
    Recorders.recs.forwardActionToRecorder(actions.get(tick));
    App.tickOverride();
    tick++;
    if (checkLevelEnd() && nextLevelPath != null) { nextReplay.run(); }
  }

  /**
   * Checks if the replay for this level has finished.
   */
  private boolean checkLevelEnd(){
    return tick < actions.size();
  }
  
  /**
   * Sends inputs to App and ticks the game in different ways depending on the implementation
   */
  public abstract void replay();
}