package nz.ac.wgtn.swen225.lc.App;

import java.util.HashMap;
import java.util.Map;
import nz.ac.wgtn.swen225.lc.domain.*;

/**
 * Stores the keystrokes that associate with an action that is executed, and a direction that is set
 * when a key is pressed.
 */
public class KeyStrokes {
    private final Map<Integer, PlayerAction> strokeToPlayerAction = new HashMap<>();
    private final Map<Integer, Runnable> strokeToUIAction = new HashMap<>();

    public boolean strokeGoesToAction(int keyStroke){
        return strokeToUIAction.containsKey(keyStroke);
    }

    /**
     * Binds a keystroke to a direction in which the character can move.
     *
     * @param keyStroke The keystroke of the key
     * @param action A direction that will be associated with the key.
     */
    public void assignKeyToPlayerAction(int keyStroke, PlayerAction action){
        strokeToPlayerAction.put(keyStroke, action);
    }

    /**
     * Binds a keystroke to a direction in which the character can move.
     *
     * @param keyStroke The keystroke of the key
     * @param action Thr action that will be associated with the keystroke.
     */
    public void assignKeyToAction(int keyStroke, Runnable action){
        strokeToUIAction.put(keyStroke, action);
    }

    /**
     * Gets the direction that is associated with the given key code. The direction cannot be null.
     *
     * @param keyStroke The keystroke of the key that will be associated with the action.
     * @return The direction associated with the keystroke.
     */
    public PlayerAction getPlayerAction(int keyStroke){
        PlayerAction returnValue = strokeToPlayerAction.get(keyStroke);

        if (returnValue == null)
            throw new IllegalArgumentException("Given keystroke does not map to a direction!");

        return returnValue;
    }

    /**
     * From a given keystroke, a Runnable action is run. This is different to the "getPlayerAction" method which
     * returns a direction associated with a keystroke.
     *
     * @param keyStroke The keystroke of the key that will be associated with the action.
     */
    public void performAction(int keyStroke){
        if (strokeToPlayerAction.get(keyStroke) != null)
            throw new IllegalCallerException("Given keystroke maps to a Player Action !");

        Runnable runAction = strokeToUIAction.get(keyStroke);

        if (runAction == null)
            throw new IllegalArgumentException("Given keystroke does not map to a runnable action!");

        runAction.run();
    }
}