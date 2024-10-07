package nz.ac.wgtn.swen225.lc.recorder;

import java.nio.file.Path;
import java.util.Objects;
import javax.swing.Timer;

import nz.ac.wgtn.swen225.lc.app.App;

public class TickReplay extends Replay {
  private int tickSpeed;
  private Timer timer;
  

  public TickReplay(Path path, int tickSpeed) {
    super(path);
    Objects.requireNonNull(tickSpeed);
    if (tickSpeed < 0) {
      throw new IllegalArgumentException("Tickspeed cannot be less than 0");
    }
    this.tickSpeed = tickSpeed;
  }

  @Override
  public void replay() {
    // TODO: loop ticks using the default tick speed and play a different action
    // each time
    timer = new Timer(tickSpeed, a -> update());
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
