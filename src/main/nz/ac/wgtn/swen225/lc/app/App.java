package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.domain.GameState;

import javax.swing.*;

/**
 * Main Class responsible for all other functions of the App Interface, that are not tied to the GUI or the Keystrokes.
 * When you initialise the constructor for this class, you also will initialise the constructor for the GUI so a start
 * menu can be created
 */
public class App{
    public App(){ SwingUtilities.invokeLater(GameGUI::new); }

    /**
     * A "tickOverride()" method that the Recorder can use to allow for replay-back.
     * TODO: Make an "InteractReplay" interface that has this method and a method that takes in a PlayerAction and does something with it.
     */
    public static void tickOverride(){ GameState.getGameState().tick(); }

    /*
     * TODO: Add in further integration with Domain, Recorder, Renderer, and Persistency.
     */
}