package nz.ac.wgtn.swen225.lc.recorder;

import java.util.List;
import java.nio.file.Path;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;

/**
 * Represents the actions that the player makes in a level.
 *
 * @param Filename      The name of the file associated with the level.
 * @param actions       A list of player actions representing the player's moves in the level.
 * @param filePath      The path where the level is saved.
 * @param levelPath     The path of the current level.
 * @param nextLevelPath The path to the next level.
 */
public record Level(String Filename, List<PlayerAction> actions, Path filePath, Path levelPath, Path nextLevelPath) {

    /**
     * Saves the level to the specified json type.
     */
    public void save() {
        // TODO: saves the level
    }
}
