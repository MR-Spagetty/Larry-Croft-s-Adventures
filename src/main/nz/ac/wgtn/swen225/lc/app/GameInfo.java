package nz.ac.wgtn.swen225.lc.app;

public class GameInfo{
    protected String levelID;
    protected int timeRemaining;
    protected int chipsRemaining;

    /**
     * Initialises the fields to "default" values. This is only done when the GameInfo class
     * is first created. The constructor is private as we also don't want multiple "Information"
     * instances to be created.
     */
    private GameInfo(){
        this.levelID = "null";
        this.timeRemaining = 0;
        this.chipsRemaining = 0;
    }

    /** Decreases the amount of time remaining by one. (Which will be one second in the game.) */
    public void decreaseTimeRemaining(){ timeRemaining--; }

    /** @return The time remaining in the game. */
    public int getTimeRemaining(){ return timeRemaining; }
}