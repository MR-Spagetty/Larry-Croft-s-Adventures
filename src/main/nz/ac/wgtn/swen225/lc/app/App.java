package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.domain.GameState;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.recorder.*;
import nz.ac.wgtn.swen225.lc.recorder.Recorder;

import javax.swing.*;

/**
 * Main Class responsible for all other functions of the App Interface, that are not tied to the GUI or the Keystrokes.
 * When you initialise the constructor for this class, you also will initialise the constructor for the GUI so a start
 * menu can be created
 */
public class App{
    private final static Recorder rec = new Recorder("a");

    public App(){ SwingUtilities.invokeLater(UserInterface::new); }

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
     *
     * INTEGRATION NEEDED:
     * - A way to forward a player action to Domain.
     * - For AutoReplay, you just need to call it once.
     * - For TickReplay, you need to ask user for tick speed then pass it to the constructor.
     * - For StepReplay, you need to call the replay method each time the player presses a key.
     *
     * NB: The replay is a bit more complicated, I have a replay interface and three classes that extend that interface.
     */

    /**
     * Takes a given player action and forwards it to the Domain class for...
     */
    public static void forwardActionToDomain(PlayerAction action){
        /* TODO: Domain needs to create method to receive PlayerAction. */
        GameState.getGameState().getPlayer().setActionQueue(action);
    }

    /**
     * Passes a given player action to the recorder to allow for that action to be recorded.
     *
     * @param action The given player action
     */
    public static void forwardActionToRecorder(PlayerAction action){ rec.record(action); }

    /**
     * Calls the "Auto Replay" feature in the Replayer. This is only done once!
     */
    public static void callAutoReplay(){ new AutoReplay().replay(); }

    /*
     * TODO: Fill in the following methods.
     */
    public static void callTickReplay(){}
    public static void callStepReplay(){}
}