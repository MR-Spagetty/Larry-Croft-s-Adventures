package nz.ac.wgtn.swen225.lc.recorder;

import java.nio.file.Path;
import java.util.Objects;
import javax.swing.Timer;

/**
 * This class Replays a recordered game and each tick is advanced when the user presses a key.
 * 
 * @author Developer 4 <dev4@example.internal> 300655226
 */
public class TickReplay extends Replay {
  private int tickSpeed;
  private Timer timer;

  public TickReplay(Path p, int tickSpeed) {
    super(p);
    Objects.requireNonNull(tickSpeed);
    if (tickSpeed < 0) {
      throw new IllegalArgumentException("Tickspeed cannot be less than 0");
    }
    this.tickSpeed = tickSpeed;
  }

  /**
   * Replay implementation of TickReplay.
   * 
   * This implementation will send an input each tick where the tick speed is provided with the constructor.
   */
  @Override
  public void replay() {
    timer = new Timer(tickSpeed, a -> advanceTick(
      () -> {
        timer.stop();
        new TickReplay(nextLevelPath, tickSpeed);
      }));
    timer.setRepeats(true);
    timer.start();
  }
}
