package app;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayDeque;
import java.lang.Error;

/**
 * Stores all of the keystrokes that associate with an action that is executed when
 * a key is pressed.
 */

// TODO make the direction emus
class KeyStrokes {
    Direction active = null;

    private final <Integer, Direction> keyStrokes = new HashMap<>();
    private Queue<Integer> pendingKeyStrokes = new ArrayDeque<>();

    /**
     * Binds a keycode to a direction in which the character can move.
     *
     * @param keyCode The keycode of the key that will be associated with the direction.
     * @param direction The direction in which the character will move.
     */
    public void assignKeyCode(int keyCode, Direction direction){ keyStrokes.put(keyCode, direction); }

    /**
     * todo
     */
    public Direction getDirection(int keyCode){
        if (!keyStrokes.contains(keyCode)) throw new Error();
        return keyStrokes.get(keyCode);
    }

    /**
     * todo
     */
    public void tick(){
        active = null;
        if (!pendingKeyStrokes.isEmpty()) active = getDirection(pendingKeyStrokes.poll())
        pendingKeyStrokes.clear();
    }
}