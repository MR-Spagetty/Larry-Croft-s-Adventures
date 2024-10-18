package nz.ac.wgtn.swen225.lc.recorder;

import java.nio.file.Path;

public class StepReplay extends Replay {
  public StepReplay(Path p) {
    super(p);
  }

  /**
   * StepReply implementation of Replay.
   * 
   * Gets called whenever the player presses a defined key controlled by App.
   * Advanced tick.
   */
  @Override
  public void replay() {
    advanceTick(() -> new StepReplay(nextLevelPath));
  }
}
