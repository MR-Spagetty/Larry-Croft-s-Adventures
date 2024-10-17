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

    /**
     * When you initialise the "ControlKeys" class, the actions will be bound to their specific keystrokes,
     * and the directions will also be bound to their keystrokes.
     * This is done in two separate methods to separate the two different stages of key assignments.
     *
     * @param uiActions A Map of the Actions that will need to be assigned to the certain keys on the keyboard.
     */
    public ControlKeys(Map<String, Runnable> uiActions){
        this();
        assignKeysToActions(uiActions);
    }

    /**
     * It is possible to create an instance of "ControlKeys" without loading in the UI Actions; this constructor
     * is only used on its own for the testing of the mappings of keyboard keys to Player Actions.
     */
    public ControlKeys(){
        assignIDsToKeys();
        assignKeysToDirections();
    }

    private void assignIDsToKeys(){
        assignIDToKey(KeyEvent.VK_KP_UP, "P_UP");
        assignIDToKey(KeyEvent.VK_KP_DOWN, "P_DOWN");
        assignIDToKey(KeyEvent.VK_KP_LEFT, "P_LEFT");
        assignIDToKey(KeyEvent.VK_KP_RIGHT, "P_RIGHT");

        assignIDToKey(KeyEvent.VK_X, "EXIT");
        assignIDToKey(KeyEvent.VK_S, "SAVE");
        assignIDToKey(KeyEvent.VK_R, "RESUME");
        assignIDToKey(KeyEvent.VK_1, "L1");
        assignIDToKey(KeyEvent.VK_2, "L2");
        assignIDToKey(KeyEvent.VK_SPACE, "PAUSE");
        assignIDToKey(KeyEvent.VK_P, "S_REPLAY");
    }

    private void assignKeysToDirections(){
        assignKeyToPlayerAction(KeyEvent.VK_KP_UP, PlayerAction.Up);
        assignKeyToPlayerAction(KeyEvent.VK_KP_DOWN, PlayerAction.Down);
        assignKeyToPlayerAction(KeyEvent.VK_KP_LEFT, PlayerAction.Left);
        assignKeyToPlayerAction(KeyEvent.VK_KP_RIGHT, PlayerAction.Right);
    }

    private void assignKeysToActions(Map<String, Runnable> uiAction) {
        assignKeyToAction(KeyEvent.VK_X, uiAction.get("EXIT"));
        assignKeyToAction(KeyEvent.VK_S, uiAction.get("SAVE"));
        assignKeyToAction(KeyEvent.VK_R, uiAction.get("RESUME"));
        assignKeyToAction(KeyEvent.VK_1, () -> {}); //Currently doesn't map to anything.
        assignKeyToAction(KeyEvent.VK_2, () -> {}); //Currently doesn't map to anything.
        assignKeyToAction(KeyEvent.VK_SPACE, uiAction.get("PAUSE"));
        assignKeyToAction(KeyEvent.VK_S, uiAction.get("S_REPLAY")); //Hidden action.
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

        PlayerAction nextAction = getPlayerAction(keystroke);
        GameState.getGameState().getPlayer().queueAction(nextAction);
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
}