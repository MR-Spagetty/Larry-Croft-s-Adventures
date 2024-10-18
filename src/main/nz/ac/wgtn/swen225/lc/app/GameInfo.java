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
    private int timeToFinish;
    private int treasuresNeeded;

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
        this.timeToFinish = 0;
        this.treasuresNeeded = 0;

        setTimeAndTreasuresCounts();

        countdownTimer = new Timer(1000, (unused) -> decreaseTimeRemaining());
        countdownTimer.setRepeats(true);
    }

    /** When a new level begins, all the information about it can be loaded here. */
    public void initialiseInformation(long levelID, int timeToFinish, int treasuresNeeded){
        countdownTimer.stop(); //Done in the case of a timer already running.

        this.levelID = levelID;
        this.timeToFinish = timeToFinish;
        this.treasuresNeeded = treasuresNeeded;

        /** TODO: Add any other code needed to restart a level. */

        setTimeAndTreasuresCounts();
        countdownTimer.restart();
    }

    /**
     * Sets the time remaining and the number of treasures remaining to their loaded-in values.
     * This is done when you first initialise the level information, and when you're restarting the level.
     */
    protected void setTimeAndTreasuresCounts(){
        this.timeRemaining = timeToFinish;
        this.treasuresRemaining = treasuresNeeded;
    }

    /** Decreases the amount of time remaining by one. (Which will be one second in the game. */
    public void decreaseTimeRemaining(){ timeRemaining--; }

    /** Decreases the amount of treasures remaining by one. */
    public void decreaseTreasuresRemaining(){ treasuresRemaining--; }

    /** @return The time remaining in the game. */
    public int getTimeRemaining(){ return timeRemaining; }

    /** @return The number of treasures left to collect in the game. */
    public int getTreasuresRemaining(){ return treasuresRemaining; }

    /** @return The level we are on. */
    public long getLevelID(){ return levelID; }
}