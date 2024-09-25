package nz.ac.wgtn.swen225.lc.domain;

import java.nio.file.Path;

public final class GameState {
  // TODO extend whatever JSONable interface is created for persistency
  private long tick = 0;

  /**
   * Returns the ID of the current level.
   *
   * @return A string representing the ID of the current level.
   */
  String getLevelID() {
    // TODO
    throw new UnsupportedOperationException("Level ID NYI");
  }

  /**
   * Returns the path to the current level.
   *
   * @return A Path object representing the path to the current level.
   */
  Path getLevelPath() {
    // TODO
    throw new UnsupportedOperationException("Level path NYI");
  }

  /**
   * Returns the current tick count of the game state.
   *
   * @return A long value representing the current tick count.
   */
  long getTick() {
    return this.tick;
  }

  /**
   * This method is responsible for updating the game state by one tick. It increments the internal
   * tick counter by one and performs necessary actions to update the game objects.
   *
   * @return void - This method does not return any value.
   */
  void tick() {
    // TODO tick game objects
    this.tick++;
  }

  /**
   * Sets the current level by its ID.
   *
   * @param levelID A string representing the ID of the level to be set.
   * @return A boolean value indicating whether the level was successfully set.
   */
  boolean setLevel(String levelID) {
    throw new UnsupportedOperationException("set level by ID NYI");
  }

  /**
   * Sets the current level by its path.
   *
   * @param levelPath A Path object representing the path to the level to be set.
   * @return A boolean value indicating whether the level was successfully set.
   */
  boolean setLevel(Path levelPath) {
    throw new UnsupportedOperationException("set level by path NYI");
  }
}
