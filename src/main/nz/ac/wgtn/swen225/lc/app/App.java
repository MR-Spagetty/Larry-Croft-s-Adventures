package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.domain.GameState;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.recorder.*;

import javax.swing.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Collections;
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

    private final static Recorder rec = new Recorder(recorderPath);
    private final static StepReplay sReplay = new StepReplay(recorderPath);

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
     * Passes a given player action to the recorder to allow for that action to be recorded.
     *
     * @param action The given player action
     */
    public static void forwardActionToRecorder(PlayerAction action){ rec.record(action); }

    /** Calls the "Auto Replay" feature in the Replayer. This is only done once! */
    public static void callAutoReplay(){ new AutoReplay(recorderPath).replay(); }

    /**
     * Creates a new "Tick Replay" instance, which involves passing in the current tick (??), and then calls
     * the "replay()".
     * TODO: Check to see if the implementation is correct.
     */
    public static void callTickReplay(){
        TickReplay tReplay = new TickReplay(recorderPath, (int)GameState.getGameState().getTick());
        tReplay.replay();
    }

    /** Simply triggers a "replay" in the Step Replay. This occurs every time a hidden key is pressed. */
    public static void callStepReplay(){ sReplay.replay(); }

    /**
     * This method returns the list of buttons that have been created in the game. This method is specifically
     * for the purpose of allowing the "Fuzz" module to access the buttons.
     */
    public static List<DefaultButton> getButtons(){ return Collections.unmodifiableList(UserInterface.ui.mainUIButtons); }

    /** Returns the list of keystrokes associated with an action. */
    public static Set<Integer> getKeyStrokes(){ return UserInterface.ui.keyController.getKeyStrokes(); }

    /** Returns an unmodifiable map of the keystrokes mapped to their player actions. */
    public static Map<Integer, PlayerAction> strokesToPlayerAction(){
        return UserInterface.ui.keyController.strokesToPlayerAction();
    }

    /** Returns an unmodifiable map of the keystrokes mapped to UI actions. */
    public static Map<Integer, Runnable> strokesToUIAction(){
        return UserInterface.ui.keyController.strokesToUIAction();
    }


    /**
     * ????
     */
    public static void startGameFromFilePath(Path gameFilePath){
        /** TODO not sure what's meant to go here, as I need to know how to start a game from a given file path. */
    }
}