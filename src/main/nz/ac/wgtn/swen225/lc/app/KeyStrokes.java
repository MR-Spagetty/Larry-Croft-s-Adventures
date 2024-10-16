package nz.ac.wgtn.swen225.lc.app;

import java.util.*;

import nz.ac.wgtn.swen225.lc.domain.*;

/**
 * Stores the keystrokes that associate with an action that is executed, and a direction that is set
 * when a key is pressed.
 *
 * @author Developer 1 <dev1@example.internal>
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
     * Gets the direction that is associated with the given key code. No action will be performed if the given
     * keycode doesn't map to a direction.
     *
     * @param keyStroke The keystroke of the key that will be associated with the action.
     * @return The direction associated with the keystroke.
     */
    public PlayerAction getPlayerAction(int keyStroke){
        if (strokeToUIAction.get(keyStroke) != null)
            throw new IllegalCallerException("Given keystroke maps to a runnable Action!");

        return strokeToPlayerAction.getOrDefault(keyStroke, PlayerAction.None);
    }

    /**
     * From a given keystroke, a Runnable action is run. This is different to the "getPlayerAction" method which
     * returns a direction associated with a keystroke.
     *
     * @param keyStroke The keystroke of the key that will be associated with the action.
     */
    public void performAction(int keyStroke){
        if (strokeToPlayerAction.get(keyStroke) != null)
            throw new IllegalCallerException("Given keystroke maps to a Player Action!");

        Runnable runAction = strokeToUIAction.getOrDefault(keyStroke, () -> {});
        runAction.run();
    }

    /**
     * Returns a COMBINED set of all the keystrokes that have been assigned to a Player Action or an
     * action to the GUI in the game.
     */
    public Set<Integer> getKeyStrokes(){
        Set<Integer> keyStrokesToPlayerAction = strokeToPlayerAction.keySet();
        Set<Integer> keyStrokesToUIAction = strokeToUIAction.keySet();

        return new HashSet<>(){{
            addAll(keyStrokesToPlayerAction);
            addAll(keyStrokesToUIAction);
        }};
    }

    /** Returns an unmodifiable map of the keystrokes mapped to their player actions. */
    public Map<Integer, PlayerAction> strokesToPlayerAction(){
        return Collections.unmodifiableMap(strokeToPlayerAction);
    }

    /** Returns an unmodifiable map of the keystrokes mapped to UI actions. */
    public Map<Integer, Runnable> strokesToUIAction(){
        return Collections.unmodifiableMap(strokeToUIAction);
    }
}