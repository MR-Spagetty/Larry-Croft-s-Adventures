package nz.ac.wgtn.swen225.lc.app;

public class GameInfo{
    private static final GameInfo INFO = new GameInfo();
    public static GameInfo info = INFO;

    private String levelID;
    private int timeRemaining;
    private int chipsRemaining;

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

    /** When a new level begins, all the information about it can be loaded here. */
    public void initialiseInformation(String levelID, int timeRemaining, int chipsRemaining){
        this.levelID = levelID;
        this.timeRemaining = timeRemaining;
        this.chipsRemaining = chipsRemaining;
    }

    /** Decreases the amount of time remaining by one. (Which will be one second in the game.) */
    public void decreaseTimeRemaining(){ timeRemaining--; }

    /** @return The time remaining in the game. */
    public int getTimeRemaining(){ return timeRemaining; }
}