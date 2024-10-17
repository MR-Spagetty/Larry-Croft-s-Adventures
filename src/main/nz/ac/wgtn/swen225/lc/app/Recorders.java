package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.recorder.AutoReplay;
import nz.ac.wgtn.swen225.lc.recorder.Recorder;
import nz.ac.wgtn.swen225.lc.recorder.StepReplay;
import nz.ac.wgtn.swen225.lc.recorder.TickReplay;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
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

    //Below is all the replay instances that the recorder will need to access.
    private static StepReplay sReplay;
    private static AutoReplay aReplay;
    private static TickReplay tReplay;

    private Recorders(){}

    /**
     * @return A string representation of the path in which to save the recorded game.
     *         This value can be "null" if the Recorder is not initialised.
     */
    public String getRecPath(){ return recorderPath.toString(); }

    /** Creates an instance of the "Auto Replay" */
    public void autoReplay(){ aReplay = new AutoReplay(recorderPath); }

    /** Creates an instance of the "Tick Replay" */
    public void tickReplay(){ tReplay = new TickReplay(recorderPath, 150); }

    /** Creates an instance of the "Step Replay" */
    public void stepReplay(){ sReplay = new StepReplay(recorderPath); }

    /** Simply triggers a "replay" in the Step Replay. This occurs every time a hidden key is pressed. */
    public void callStepReplay(){ if (sReplay != null) sReplay.replay(); }

    /**
     * Sets the main recorder to start recording a level.
     *
     * @param levelPath The path to the file containing a level in the game.
     */
    public void startRecordingLevel(Path levelPath){ if (rec != null) rec.startLevel(levelPath); }

    /** When the game is finished, the recorder is signalled to stop recording the game. */
    public void stopRecordingGame(){ if (rec != null) rec.endGame(); }

    /**
     * Passes a given player action to the recorder to allow for that action to be recorded.
     *
     * @param action The given player action
     */
    public void forwardActionToRecorder(PlayerAction action){ rec.record(action); }

    /**
     * Asks the user whether they want the game to be recorded or not.
     * If they ask for the game to be recorded, then they need to select where to store the files!
     */
    protected void askToRecordGame(){
        int recordGame = JOptionPane.showConfirmDialog(
                null, "Do you want to record the game?",
                "Record Game?", JOptionPane.YES_NO_OPTION
        );

        if (recordGame == JOptionPane.YES_OPTION){
            recorderPath = selectRecorderFolder();
            if (recorderPath == null) return;

            rec = new Recorder(recorderPath);
        }
    }

    /**
     * Selects the folder that will store the recorded files.
     *
     * @return The path to the folder. "null" is returned when no folder is selected, such as when the user aborts
     *         selecting a file.
     */
    private Path selectRecorderFolder(){
        JFileChooser chooseFolder = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        chooseFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //So we do not select a file by accident!
        int result = chooseFolder.showOpenDialog(null);

        //If you decide to cancel the operation, you will be told that the game will NOT be recorded!
        if (result != JFileChooser.APPROVE_OPTION){
            JOptionPane.showMessageDialog(
                    null, "No folder path has been selected! Recorder will not be initiated.",
                    "Info", JOptionPane.PLAIN_MESSAGE);
            return null;
        }

        return chooseFolder.getSelectedFile().toPath();
    }

    /**
     * Initiates a Game replay from a recorded game.
     *
     * @param recordedGamePath The path that stores the recorded game file.
     *
     */
    public void startGameReplay (Path recordedGamePath){

        rec.startLevel(recordedGamePath);
    }
}