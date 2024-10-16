package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.recorder.Recorder;

import java.nio.file.Path;

/**
 * Class which contains the Recorders that will be recording the game. The player has the option to decide whether to
 * record a game or not, so when the instance of the "Recorders" class is created, no recorders are initially created.
 */
public class Recorders{
    private static final Recorders RECORDERS = new Recorders();
    public static Recorders recs = RECORDERS;

    /**
     * The recorder that will record the current game the user is playing.
     * Here, the recorder and file path is initially "null" in the case that the user does not want a game to be recorded!
     * In addition, if the recorder is to be enabled, the user will need to select the folder to save the files!
     */
    private Path recorderPath = null;
    private Recorder rec = null;

    private Recorders(){}
}