package nz.ac.wgtn.swen225.lc.App;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayDeque;

/**
 * Stores all of the keystrokes that associate with an action that is executed (or a direction that is set)
 * when a key is pressed.
 */
public class KeyStrokes <V extends Direction, Runnable> {
    V active = null;

    private final <Integer, V> strokes = new HashMap<>();
    private Queue<Integer> pendingKeyStrokes = new ArrayDeque<>();

    /**
     * Binds a key stroke to a direction in which the character can move, or an action that can be initiated on the
     * game's UI during gameplay.
     *
     * @param keyStroke The key stroke of the key
     * @param elem A direction or an action that will be assiocated with the key.
     */
    public void assignKeyStroke(int keyStroke, V elem){
        strokes.put(keyStroke, elem);
    }

    /**
     * Gets the direction or the action that is associated with the given key code.
     * The return value cannot be null.
     *
     * @param keyStroke The key stroke of the key that will be associated with the action.
     * @return A direction or an action associcated with the key stroke.
     */
    public V getKeyStroke(int keyStroke){
        V returnValue = strokes.get(keyStroke);
        assert returnValue != null; /** are assertions enabled? */
        return returnValue;
    }

    /**
     * From a given key code, a Direction is retrieved or a Runnable action is run.
     *
     * @param keyStroke The key stroke of the key that will be associated with the action.
     * @return If the retrived element is a direction, it will be returned. Otherwise, the
     *         runnable action will be run and "null" is returned.
     */
    public Direction performKeyStrokeAction(int keyStroke){
        V selElem = getKeyStroke(keyStroke);

        if (selElem instanceof Direction d) return d;

        (Runnable)selElem.run();
        return null;
    }

    /**
     * Every time a tick occurs, the action that is being performed or the direction in which the
     * character is moving stops moving, and the next action/direction is selected.
     */
    public void tick(){
        active = null;
        if (!pendingKeyStrokes.isEmpty()) active = getKeyStroke(pendingKeyStrokes.poll())
        pendingKeyStrokes.clear();
    }
}