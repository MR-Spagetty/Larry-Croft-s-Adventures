package nz.ac.wgtn.swen225.lc.app.keybinders;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import nz.ac.wgtn.swen225.lc.app.Recorders;
import nz.ac.wgtn.swen225.lc.app.UserInterface;
import nz.ac.wgtn.swen225.lc.domain.*;

/**
 * An extension of the class "KeyStrokes", which is responsible for actually controlling key events, rather than
 * mapping and storing them.
 *
 * @author Developer 1 <dev1@example.internal>
 */
public class ControlKeys extends KeyStrokes implements KeyListener{
    private static PlayerAction active = PlayerAction.None; //Current player action being executed in a tick.

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
     *
     * @param uiActions A Map of the Actions that will need to be assigned to the certain keys on the keyboard.
     */
    public ControlKeys(Map<String, Runnable> uiActions){
        assignIDsToKeys();
        assignKeysToDirections();
        assignKeysToActions(uiActions);
    }

    private void assignKeysToDirections(){
        assignKeyToPlayerAction(KeyEvent.VK_KP_UP, PlayerAction.Up);
        assignKeyToPlayerAction(KeyEvent.VK_KP_DOWN, PlayerAction.Down);
        assignKeyToPlayerAction(KeyEvent.VK_KP_LEFT, PlayerAction.Left);
        assignKeyToPlayerAction(KeyEvent.VK_KP_RIGHT, PlayerAction.Right);
    }

    private void assignKeysToActions(Map<String, Runnable> uiAction){
        assignKeyToAction(KeyEvent.VK_X, uiAction.get("EXIT"));
        assignKeyToAction(KeyEvent.VK_S, uiAction.get("SAVE"));
        assignKeyToAction(KeyEvent.VK_R, uiAction.get("RESUME"));
        assignKeyToAction(KeyEvent.VK_1, () -> {});
        assignKeyToAction(KeyEvent.VK_2, () -> {});
        assignKeyToAction(KeyEvent.VK_SPACE, uiAction.get("PAUSE"));
        assignKeyToAction(KeyEvent.VK_S, uiAction.get("S_REPLAY")); //Hidden action
    }

    private void assignIDsToKeys(){
        assignIDToKey("P_UP", KeyEvent.VK_KP_UP);
        assignIDToKey("P_DOWN", KeyEvent.VK_KP_DOWN);
        assignIDToKey("P_LEFT", KeyEvent.VK_KP_LEFT);
        assignIDToKey("P_RIGHT", KeyEvent.VK_KP_RIGHT);

        assignIDToKey("EXIT", KeyEvent.VK_X);
        assignIDToKey("SAVE", KeyEvent.VK_S);
        assignIDToKey("RESUME", KeyEvent.VK_R);
        assignIDToKey("L1", KeyEvent.VK_1);
        assignIDToKey("L2", KeyEvent.VK_1);
        assignIDToKey("PAUSE", KeyEvent.VK_SPACE);
        assignIDToKey("S_REPLAY", KeyEvent.VK_SPACE);
    }

    /**
     * When you press a key, you could either be making the player move, or perforDeveloper 4 <dev4@example.internal> an action on the GUI.
     * We will first check to see if CTRL is being held down, as this will indicate whether to perform an action
     * that required the CTRL key to be held down. We then check to see if the key pressed is an action, or a
     * direction.
     * Take note for a direction, as one player action is only executed per tick, we will cache the keystroke for the
     * first key pressed.
     * REFERENCE: https://stackoverflow.com/questions/11659801/java-check-if-control-key-is-being-pressed
     *
     * @param e The key that was pressed in the form of a "KeyEvent".
     */
    public void keyPressed(KeyEvent e) {
        int keystroke = e.getKeyCode();

        if (((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0) || strokeGoesToAction(keystroke)){
            performAction(keystroke);
            return;
        }

        setNextKeyStroke(keystroke);
    }

    /**
     * Helper method to "keyPressed" which sets the next keystroke (for the player action) that will be
     * used in the next tick.
     *
     * @param keystroke The keystroke that is associated with a certain key on the keyboard.
     */
    public void setNextKeyStroke(int keystroke){
        if (pendingKeyStroke == INVALID_KEY_STROKE) pendingKeyStroke = keystroke;
    }

    /**
     * By default, nothing will happen when a key is simply "typed".
     *
     * @param e The key that was typed in the form of a "KeyEvent".
     */
    public void keyTyped(KeyEvent e){}

    /**
     * By default, nothing will happen when a key is released after being pressed.
     *
     * @param e The key that was released in the form of a "KeyEvent".
     */
    public void keyReleased(KeyEvent e){}

    /**
     * Every time a tick occurs, the action that is being performed or the direction in which the
     * character is moving stops moving, and the next action/direction is performed.
     * Also, the new Player Action will be passed to the recorder for recording.
     */
    public void setPlayerActionAtTick(){
        /*
         * We first check to see if there is a pending player action to execute. If there's none, we won't
         * continue from here. (We will initialise the active direction to "None".)
         */
        if (pendingKeyStroke == INVALID_KEY_STROKE){
            active = PlayerAction.None;
            return;
        }

        active = getPlayerAction(pendingKeyStroke);
        pendingKeyStroke = INVALID_KEY_STROKE;

        Recorders.recs.forwardActionToRecorder(active);
    }

    /** @return The action that the player is currently carrying out in a tick. */
    public PlayerAction getActivePlayerAction(){ return active; }
}