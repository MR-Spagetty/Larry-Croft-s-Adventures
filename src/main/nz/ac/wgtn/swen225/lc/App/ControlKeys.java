package nz.ac.wgtn.swen225.lc.App;

import java.awt.event.KeyEvent;
import nz.ac.wgtn.swen225.lc.domain.*;
//import nz.ac.wgtn.swen225.lc.recorder.*;

/**
 * An extension of the class "KeyStrokes", which is responsible for actually controlling key events, rather than
 * mapping and storing them.
 *
 * @author Developer 1 <dev1@example.internal>
 */
public class ControlKeys extends KeyStrokes{

    //Current player action that the player is moving in a tick.
    private static PlayerAction active = PlayerAction.None;

    /**
     * If multiple keys are hit during a singular tick, rather than changing the direction the character is moving
     * mid-tick, the key will be taken note of, and executed in the next tick.
     * Take note that the first key pressed is only taken note of; the rest are discarded.
     */
    private final int INVALID_KEY_STROKE = -1;
    private int pendingKeyStroke = INVALID_KEY_STROKE;

    /**
     * When you initialise the "ControlKeys" class, the actions will be bound to their specific keystrokes,
     * and the directions will also be bound to their keystrokes.
     * This is done in two separate methods to separate the two different stages of key assignments.
     */
    public ControlKeys(){
        assignKeysToDirections();
        assignKeysToActions();
    }

    public void assignKeysToDirections(){
        assignKeyToPlayerAction(KeyEvent.VK_KP_UP, PlayerAction.Up);
        assignKeyToPlayerAction(KeyEvent.VK_KP_DOWN, PlayerAction.Down);
        assignKeyToPlayerAction(KeyEvent.VK_KP_LEFT, PlayerAction.Left);
        assignKeyToPlayerAction(KeyEvent.VK_KP_RIGHT, PlayerAction.Right);
    }

    /**
     * TODO Create actions for each key!
     */
    public void assignKeysToActions(){
        assignKeyToAction(KeyEvent.VK_X, () -> {});
        assignKeyToAction(KeyEvent.VK_S, () -> {});
        assignKeyToAction(KeyEvent.VK_R, () -> {});
        assignKeyToAction(KeyEvent.VK_C, () -> {});
        assignKeyToAction(KeyEvent.VK_1, () -> {});
        assignKeyToAction(KeyEvent.VK_2, () -> {});
        assignKeyToAction(KeyEvent.VK_SPACE, () -> GameGUI.ps.showScreen());
        assignKeyToAction(KeyEvent.VK_ESCAPE, () -> GameGUI.ps.hideScreen());
    }

    /**
     * When you press a key, you could either be making the player move, or perforDeveloper 4 <dev4@example.internal> an action on the GUI.
     * We will first check to see if CTRL is being held down, as this will indicate whether to perform an action
     * that required the CTRL key to be held down. We then check to see if the key pressed is an action, or a
     * direction.
     * Take note for a direction, as one player action is only executed per tick, we will cache the keystroke for the
     * first key pressed.
     *
     * @param e The key that was pressed in the form of a "KeyEvent".
     */
    public void keyPressed(KeyEvent e) {
        int keystroke = e.getKeyCode();

        if (((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0) || strokeGoesToAction(keystroke)){
            performAction(keystroke);
            return;
        }

        if (pendingKeyStroke == INVALID_KEY_STROKE) pendingKeyStroke = keystroke;
    }

    /**
     * Every time a tick occurs, the action that is being performed or the direction in which the
     * character is moving stops moving, and the next action/direction is performed.
     * Also, the new Player Action will be passed to the recorder for recording.
     */
    public void setPlayerActionAtTick(){
        //We first need to set the current player action back to no value, so the player stops moving.
        active = PlayerAction.None;

        //If there is a player action that is pending to be executed, this will be executed for this tick.
        if (pendingKeyStroke != INVALID_KEY_STROKE) {
            active = getPlayerAction(pendingKeyStroke);
            pendingKeyStroke = INVALID_KEY_STROKE;
        }
    }

    /**
     * Returns the action that is player is currently carrying out in a tick.
     * This method is static to allow for the Recorder to access this method without needing to create an instance
     * of this class first.
     */
    public static PlayerAction getActivePlayerAction(){ return active; }
}