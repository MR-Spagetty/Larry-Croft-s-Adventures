package nz.ac.wgtn.swen225.lc.recorder;

import java.nio.file.Path;
import javax.swing.Timer;

import nz.ac.wgtn.swen225.lc.domain.GameState;

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
    timer = new Timer(GameState.DEFAULT_TICK_RATE, a -> advanceTick());
    timer.setRepeats(true);
    timer.start();
  }
}
