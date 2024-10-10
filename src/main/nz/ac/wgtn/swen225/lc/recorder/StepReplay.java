package nz.ac.wgtn.swen225.lc.recorder;

import java.nio.file.Path;

public class StepReplay extends Replay {
  private int tick = 0;

  public StepReplay(Path p) {
    super(p);
  }

  @Override
  public void replay() {
    // TODO: call the tick and play method in app when it is finished
  }
}
