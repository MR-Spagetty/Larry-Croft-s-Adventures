package nz.ac.wgtn.swen225.lc.App;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayDeque;

class Direction{} //JUST A MOCK CLASS!

/**
 * Stores the keystrokes that associate with an action that is executed, and a direction that is set
 * when a key is pressed.
 */
public class KeyStrokes {
    Direction active = null;
    private final Map<Integer, Direction> strokeToDirection = new HashMap<>();

    private final Map<Integer, Runnable> strokeToUIAction = new HashMap<>();

    private final Queue<Integer> pendingKeyStrokes = new ArrayDeque<>();

    /**
     * Binds a keystroke to a direction in which the character can move.
     *
     * @param keyStroke The keystroke of the key
     * @param direction A direction that will be associated with the key.
     */
    public void assignKeyToDirection(int keyStroke, Direction direction){
        strokeToDirection.put(keyStroke, direction);
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
    public Direction getDirection(int keyStroke){
        Direction returnValue = strokeToDirection.get(keyStroke);
        assert returnValue != null;
        return returnValue;
    }

    /**
     * From a given keystroke, a Runnable action is run. This is different to the "getDirection" method which
     * returns a direction associated with a keystroke.
     *
     * @param keyStroke The keystroke of the key that will be associated with the action.
     */
    public void performAction(int keyStroke){
        Runnable runAction = strokeToUIAction.get(keyStroke);
        runAction.run();
    }

    /**
     * Every time a tick occurs, the action that is being performed or the direction in which the
     * character is moving stops moving, and the next action/direction is selected.
     */
    public void tick(){
        active = null;
        if (!pendingKeyStrokes.isEmpty()) active = getDirection(pendingKeyStrokes.poll());
        pendingKeyStrokes.clear();
    }
}