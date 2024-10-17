package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.GameState;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;

public class TreasureDoor extends Door {

  public TreasureDoor(Point location) {
    super(location);
  }

  @Override
  protected boolean meetsUnlockReqs(Entity enteree) {
    return GameState.getGameState().collectedTreasures()
        >= GameState.getGameState().requiredTreasures();
  }

  @Override
  protected void onUnlock(Entity enteree) {}
}
