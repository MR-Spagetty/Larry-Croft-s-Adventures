package nz.ac.wgtn.swen225.lc.app;

import javax.swing.Timer;

/**
 * Stores all information that will be reported to the user about the current game. This includes the level
 * that the player is on, how much time they have left to complete the level, and how many chips the player
 * need to collect.
 */
public class GameInfo{
    private static final GameInfo INFO = new GameInfo();
    public static GameInfo info = INFO;

    private String levelID;
    private int timeRemaining;
    private int chipsRemaining;

    final Timer countdownTimer; //Controls the time the player has left to complete the level.

    /**
     * Initialises the fields to "default" values and sets up the "Countdown" timer mechanism.
     * This is only done when the GameInfo class is first initialised. The constructor is
     * private as we also don't want multiple "Information" instances to be created.
     */
    private GameInfo(){
        this.levelID = "null";
        this.timeRemaining = 0;
        this.chipsRemaining = 0;

        countdownTimer = new Timer(1000, (unused) -> decreaseTimeRemaining());
        countdownTimer.setRepeats(true);
    }

    /** When a new level begins, all the information about it can be loaded here. */
    public void initialiseInformation(String levelID, int timeRemaining, int chipsRemaining){
        countdownTimer.stop(); //Done in the case of a timer already running.

        this.levelID = levelID;
        this.timeRemaining = timeRemaining;
        this.chipsRemaining = chipsRemaining;

        countdownTimer.restart();
    }

    /** Decreases the amount of time remaining by one. (Which will be one second in the game.) */
    public void decreaseTimeRemaining(){ timeRemaining--; }

    /** @return The time remaining in the game. */
    public int getTimeRemaining(){ return timeRemaining; }

    /** Decreases the amount of chips remaining by one. */
    public void decreaseChipsRemaining(){ chipsRemaining--; }

    /** @return The number of chips left to collect in the game. */
    public int getChipsRemaining(){ return chipsRemaining; }

    /** @return The level we are on. (Currently returning its ID; this may change) */
    public String getLevelID(){ return levelID; }
}