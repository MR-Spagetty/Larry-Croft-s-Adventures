package nz.ac.wgtn.swen225.lc.recorder;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import java.util.List;

/**
 * This interface represents a replay system for a recorded game, 
 */
public interface Replay {
  public List<PlayerAction> actions = null;

  /**
   * Retrieves the player's action at the specified frame (tick) of the game replay.
   *
   * @param tick The frame number for which the recorded player action is needed. 
   *             This corresponds to the index in the 'actions' list.
   * @return The PlayerAction object representing the player's action in the specified frame.
   */
  public void replay();
}