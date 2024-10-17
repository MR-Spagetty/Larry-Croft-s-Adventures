package nz.ac.wgtn.swen225.lc.app;

public class GameInfo{
    protected String levelID;
    protected int timeRemaining;
    protected int chipsRemaining;

    /** Decreases the amount of time remaining by one. (Which will be one second in the game.) */
    public void decreaseTimeRemaining(){ timeRemaining--; }

    /** @return The time remaining in the game. */
    public int getTimeRemaining(){ return timeRemaining; }
}