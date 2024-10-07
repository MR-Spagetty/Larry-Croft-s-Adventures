package nz.ac.wgtn.swen225.lc.recorder;

import java.nio.file.Path;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;

/*
 * Autamatically replays the file using the default tick speed
 */
public class AutoReplay extends Replay {
  public AutoReplay(Path p) {
    super(p);
    replay();
  }

  @Override
  public void replay() {
    // TODO: loop ticks using the default tick speed and play a different action
    // each time
  }
}
