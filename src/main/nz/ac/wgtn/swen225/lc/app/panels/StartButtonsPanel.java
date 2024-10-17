package nz.ac.wgtn.swen225.lc.app.panels;

import nz.ac.wgtn.swen225.lc.app.buttons.DefaultButton;

import javax.swing.*;

/**
 * Another custom JPanel that holds the Buttons to be displayed at the start of the game.
 */
public class StartButtonsPanel extends JPanel {

    /**
     * Constructor used when constructing the custom JPanel.
     *
     * @param startGame The action to be executed when the user starts a new game.
     * @param resumeGame The action to be executed when the user resumes an existing game.
     * @param replayGame The action to be executed when the user replays an already played game that has been recorded.
     */
    public StartButtonsPanel(Runnable startGame, Runnable resumeGame, Runnable replayGame){
        add(new DefaultButton(unused -> startGame.run(), "Start new game!"));
        add(new DefaultButton(unused -> resumeGame.run(), "Resume existing game!"));
        add(new DefaultButton(unused -> replayGame.run(), "Replay a game!"));
    }
}