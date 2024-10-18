package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.domain.GameState;

import javax.swing.*;

/**
 * Stores all information that will be reported to the user about the current game. This includes the level
 * that the player is on, how much time they have left to complete the level, and how many chips the player
 * need to collect.
 */
public class GameInfo{
    private static final GameInfo INFO = new GameInfo();
    public static GameInfo info = INFO;

    private long levelID;
    private int timeRemaining;
    private int treasuresRemaining;

    final Timer countdownTimer; //Controls the time the player has left to complete the level.

    /**
     * Initialises the fields to "default" values and sets up the "Countdown" timer mechanism.
     * This is only done when the GameInfo class is first initialised. The constructor is
     * private as we also don't want multiple "Information" instances to be created.
     */
    private GameInfo(){
        this.levelID = 0;
        this.timeRemaining = 0;
        this.treasuresRemaining = 0;

        countdownTimer = new Timer(1000, (unused) -> decreaseTimeRemaining());
        countdownTimer.setRepeats(true);
    }

    /** When a new level begins, all the information about it can be loaded here. */
    public void initialiseInformation(long levelID, int timeRemaining, int chipsRemaining){
        countdownTimer.stop(); //Done in the case of a timer already running.

        this.levelID = levelID;
        this.timeRemaining = timeRemaining;
        this.treasuresRemaining = chipsRemaining;

        countdownTimer.restart();
    }

    /**
     * Decreases the amount of time remaining by one. (Which will be one second in the game.)
     * Once the time remaining gets to zero, the timer will stop and a pop-up window will appear
     * telling you that you have run out of time. From there, you can restart the current level.
     */
    public void decreaseTimeRemaining(){
        timeRemaining--;

        if (timeRemaining <= 0){
            countdownTimer.stop(); //Done in the case of a timer already running.
            GameState.getGameState().tickTimer.stop();

            JOptionPane.showMessageDialog(null,
                    "You have run out of time! Please close this window to restart the level!",
                    "Out of time", JOptionPane.PLAIN_MESSAGE);

            /** TODO: Add infrastructure to restart a level. */

            GameState.getGameState().tickTimer.restart();
            countdownTimer.restart();
        }
    }

    /** @return The time remaining in the game. */
    public int getTimeRemaining(){ return timeRemaining; }

    /** Decreases the amount of treasures remaining by one. */
    public void decreaseTreasuresRemaining(){ treasuresRemaining--; }

    /** @return The number of treasures left to collect in the game. */
    public int getTreasuresRemaining(){ return treasuresRemaining; }

    /** @return The level we are on. (Currently returning its ID; this may change) */
    public long getLevelID(){ return levelID; }
}