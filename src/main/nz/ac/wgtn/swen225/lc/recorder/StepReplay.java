package nz.ac.wgtn.swen225.lc.recorder;

import java.nio.file.Path;

public class StepReplay extends Replay {
  public StepReplay(Path p) {
    super(p);
  }

  /**
   * Replay implementation of StepReplay.
   * 
   * Gets called whenever the player presses a defined key controlled by App.
   * Advanced tick.
   */
  @Override
  public void replay() {
    advanceTick(() -> new StepReplay(nextLevelPath));
  }
}
