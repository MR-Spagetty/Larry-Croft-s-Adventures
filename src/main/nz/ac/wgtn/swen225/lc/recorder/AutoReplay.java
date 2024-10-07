package nz.ac.wgtn.swen225.lc.recorder;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;

public class AutoReplay implements Replay {

  /*
   * Constructor of the AutoReplay class, opens a recorder json file, parse it and
   * store it in super.actions
   */
  public AutoReplay() {
    // TODO: determin how to parse and get file after persistency is completed
  }

  @Override
  public void replay() {
    // TODO: loop ticks using the default tick speed and play a different action
    // each time
  }
}
