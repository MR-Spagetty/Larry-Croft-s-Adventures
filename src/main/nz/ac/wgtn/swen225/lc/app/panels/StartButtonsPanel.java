package nz.ac.wgtn.swen225.lc.app.panels;

import nz.ac.wgtn.swen225.lc.app.IOController;
import nz.ac.wgtn.swen225.lc.app.UserInterface;
import nz.ac.wgtn.swen225.lc.app.buttons.DefaultButton;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Another custom JPanel that holds the Buttons to be displayed at the start of the game.
 */
public class StartButtonsPanel extends JPanel {
    private static final StartButtonsPanel START_BUTTONS_PANEL = new StartButtonsPanel();
    public static StartButtonsPanel sbp = START_BUTTONS_PANEL;

    //The list of buttons that will be on the Start menu.
    private final List<DefaultButton> startButtons = new ArrayList<>();

    private StartButtonsPanel(){
        startButtons.add(new DefaultButton(unused -> UserInterface.ui.startGame(null), "Start new game!"));
        startButtons.add(new DefaultButton(unused -> IOController.ic.resumeExistingGame(), "Resume existing game!"));
        startButtons.add(new DefaultButton(unused -> {}, "Replay a game!")); //Didn't get around to this feature :(

        startButtons.forEach(this::add);
    }

    /** @return The list of buttons on the Start menu. Mainly for use by Fuzz. */
    public List<DefaultButton> getStartButtons(){ return startButtons; }
}