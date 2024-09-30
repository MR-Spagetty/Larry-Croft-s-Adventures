package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.domain.GameState;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;

import javax.swing.*;

/**
 * Main Class responsible for all other functions of the App Interface, that are not tied to the GUI or the Keystrokes.
 * When you initialise the constructor for this class, you also will initialise the constructor for the GUI so a start
 * menu can be created
 */
public class App{
    public App(){ SwingUtilities.invokeLater(UserInterface::new); }

    /**
     * A "tickOverride()" method that the Recorder can use to allow for replay-back.
     * All this method will do is advance a tick in the current game!
     */
    public static void tickOverride(){ GameState.getGameState().tick(); }

    /*
     * TODO: Add in further integration with Domain, Recorder, Renderer, and Persistency.
     *
     * INTEGRATION NEEDED:
     * - Make a method that takes in a PlayerAction and does something with it.
     * - Somehow forward a player action to Domain.
     * - Add an additional "keystroke" that will call "step.replay()"
     * - SEE THE DISCORD CHAT!
     */
    public void doSomething(PlayerAction action){

    }
}