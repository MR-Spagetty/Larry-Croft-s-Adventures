package nz.ac.wgtn.swen225.lc.recorder;

import java.nio.file.Path;
import javax.swing.Timer;

import nz.ac.wgtn.swen225.lc.domain.GameState;
import nz.ac.wgtn.swen225.lc.app.App;

/*
 * Autamatically replays the file using the default tick speed
 */
public class AutoReplay extends Replay {
  private Timer timer;

  public AutoReplay(Path p) {
    super(p);
    replay();
  }

  /*
   * Replay implementation of AutoReplay.
   * 
   * This implementation will send an input each tick at the default tickrate defined in Domain/Gamestate.
   */
  @Override
  public void replay() {
    timer = new Timer(GameState.DEFAULT_TICK_RATE, a -> update());
    timer.setRepeats(true);
    timer.start();
  }

  /*
   * updates the game by a tick then check if the game has ended.
   */
  private void update(){
    advanceTick();
    if (actions.size() > tick){
      timer.stop();
      //TODO: go to next level
    }
  }
}
