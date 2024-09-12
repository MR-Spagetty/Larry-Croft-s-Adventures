package App;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayDeque;

/**
 * Stores all of the keystrokes that associate with an action that is executed (or a direction that is set)
 * when a key is pressed.
 */
class KeyStrokes <V extends Direction, Runnable> {
    V active = null;

    private final <Integer, V> strokes = new HashMap<>();
    private Queue<Integer> pendingKeyStrokes = new ArrayDeque<>();

    /**
     * Binds a keycode to a direction in which the character can move, or an action that can be initiated on the
     * game's UI during gameplay.
     *
     * @param keyCode The keycode of the key
     * @param elem A direction or an action that will be assiocated with the key.
     */
    public void assignKeyStroke(int keyCode, V elem){
        strokes.put(keyCode, elem);
    }

    /**
     * Gets the direction or the action that is associated with the given key code.
     * The return value cannot be null.
     *
     * @param keyCode The keycode of the key that will be associated with the action.
     * @return A direction or an action associcated with the keycode.
     */
    public V getKeyStroke(int keyCode){
        V returnValue = strokes.get(keyCode);
        assert returnValue != null; /** are assertions enabled? */
        return returnValue;
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