package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.app.buttons.DefaultButton;
import nz.ac.wgtn.swen225.lc.app.panels.StartButtonsPanel;
import nz.ac.wgtn.swen225.lc.domain.GameState;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * Main Class responsible for all other functions of the App Interface, that are not tied to the GUI, the Keystrokes
 * or the Recorders. When you initialise the constructor for this class, you also will initialise the constructor for
 * the User Interface so a start menu can be created.
 *
 * @author Developer 1 <dev1@example.internal> - 300652265
 */
public class App{
    public App(){ SwingUtilities.invokeLater(() -> UserInterface.ui.createMenu()); }

    /**
     * A "tickOverride()" method that the Recorder can use to allow for replay-back.
     * All this method will do is advance a tick in the current game!
     */
    public static void tickOverride(){ GameState.getGameState().tick(); }

    /**
     * Takes a given player action and forwards it to Domain.
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

    /** @return An unmodifiable map of the keystrokes mapped to their player actions. */
    public static Map<String, PlayerAction> strokesToPlayerAction(){
        return IOController.ic.getKeyController().strokesToPlayerAction();
    }

    /** @return An unmodifiable map of the keystrokes mapped to UI actions. */
    public static Map<String, Runnable> strokesToUIAction(){
        return IOController.ic.getKeyController().strokesToUIAction();
    }

    /** @return The buttons used in the Start Menu. */
    public static List<DefaultButton> startMenuButtons(){ return StartButtonsPanel.sbp.getStartButtons(); }
}