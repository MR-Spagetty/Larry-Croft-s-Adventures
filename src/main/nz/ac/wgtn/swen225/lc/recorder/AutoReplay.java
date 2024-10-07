package nz.ac.wgtn.swen225.lc.recorder;

import java.nio.file.Path;
import javax.swing.Timer;

import nz.ac.wgtn.swen225.lc.domain.GameState;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.app.App;

/*
 * Autamatically replays the file using the default tick speed
 */
public class AutoReplay extends Replay {
  private int tick = 0;
  private Timer timer;

  public AutoReplay(Path p) {
    super(p);
    replay();
  }

  @Override
  public void replay() {
    // TODO: loop ticks using the default tick speed and play a different action
    // each time
    timer = new Timer(GameState.DEFAULT_TICK_RATE, a -> update());
    timer.setRepeats(true);
    timer.start();
  }

  /*
   * updates the game by a tick by sending App an action then ticking
   */
  private void update(){
    App.forwardActionToDomain(actions.get(tick));
    App.tickOverride();
    tick++;
    if (actions.size() > tick){
      timer.stop();
    }
    //TODO: go to next level
  }
}
