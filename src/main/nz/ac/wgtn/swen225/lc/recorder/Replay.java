package nz.ac.wgtn.swen225.lc.recorder;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import java.util.List;

/**
 * This class replays a recorded game saved by record.java.
 * 
 * It is stored in a list where each index is mapped to the corrosponding frame and direction.
 */
public class Replay {
  List<PlayerAction> actions;

  Replay() {
    // TODO: determin how to get the replay json file and how to parse it and store it in actions
    // after persistence is completed.
  }

  /**
   * Sends back the movement of the character at the specified frame
   * 
   * @param frame the frame of the game that wants to be replayed 
   */
  public PlayerAction replay(int tick) {
    if (tick < 0) {
      throw new IllegalArgumentException("tick cannot be less than 0");
    }
    return actions.get(tick);
  }
}
