package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.app.buttons.DefaultButton;
import nz.ac.wgtn.swen225.lc.domain.GameState;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;

import javax.swing.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Main Class responsible for all other functions of the App Interface, that are not tied to the GUI or the Keystrokes.
 * When you initialise the constructor for this class, you also will initialise the constructor for the GUI so a start
 * menu can be created.
 */
public class App{
    /** TODO Is the path correct? Ideally it would be kept out of the source code folder. */
    private final static Path recorderPath = FileSystems.getDefault().getPath("files/recorded_levels");

    public App(){ SwingUtilities.invokeLater(() -> UserInterface.ui.createMenu()); }

    /**
     * A "tickOverride()" method that the Recorder can use to allow for replay-back.
     * All this method will do is advance a tick in the current game!
     */
    public static void tickOverride(){ GameState.getGameState().tick(); }

    /*
     * TODO: Add in further integration with Domain, Recorder, Renderer, and Persistency.
     *
     * INTEGRATION DONE:
     * - A way to advance a tick.
     * - Pass PlayerAction to Recorder each tick.
     * - A way to forward a player action to Domain.
     * - For AutoReplay, you just need to call it once.
     * - For TickReplay, you need to ask user for tick speed then pass it to the constructor.
     * - For StepReplay, you need to call the replay method each time the player presses a key.
     *
     * NB: The replay is a bit more complicated, I have a replay interface and three classes that extend that interface.
     *
     * INTEGRATION NEEDED:
     * - A method that can end a game file, while skipping any "confirmation".
     */

    /**
     * Takes a given player action and forwards it to the Domain class.
     *
     * @param action The given player action
     */
    public static void forwardActionToDomain(PlayerAction action){
        GameState.getGameState().getPlayer().queueAction(action);
    }

    /**
     * This method returns the list of buttons that have been created in the game. This method is specifically
     * for the purpose of allowing the "Fuzz" module to access the buttons.
     *
     * @return The list of buttons that will be displayed in the UI during gameplay.
     */
    public static List<DefaultButton> getButtons(){ return IOController.ic.getMainUIButtons(); }

    /** @return The list of keystrokes associated with an action. */
    public static Set<Integer> getKeyStrokes(){ return IOController.ic.getKeyController().getKeyStrokes(); }

    /** @return An unmodifiable map of the keystrokes mapped to their player actions. */
    public static Map<Integer, PlayerAction> strokesToPlayerAction(){
        return IOController.ic.getKeyController().strokesToPlayerAction();
    }

    /** @return An unmodifiable map of the keystrokes mapped to UI actions. */
    public static Map<Integer, Runnable> strokesToUIAction(){
        return IOController.ic.getKeyController().strokesToUIAction();
    }
}