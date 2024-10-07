package nz.ac.wgtn.swen225.lc.recorder;

import java.nio.file.Path;
import java.util.Objects;

public class TickReplay extends Replay {
  private Long tickSpeed;

  public TickReplay(Path path, Long tickSpeed) {
    super(path);
    Objects.requireNonNull(tickSpeed);
    if (tickSpeed < 0) {
      throw new IllegalArgumentException("Tickspeed cannot be less than 0");
    }
    this.tickSpeed = tickSpeed;
  }

  @Override
  public void replay() {
    // TODO: plays an action with the given tick speed
  }
}
