package App;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayDeque;

/**
 * Stores all of the keystrokes that associate with an action that is executed (or a direction that is set)
 * when a key is pressed.
 */

// TODO make the direction emus
class KeyStrokes {
    Direction active = null;

    private final <Integer, Direction> strokeToDirection = new HashMap<>();
    private final <Integer, Runnable> strokeToRunnable = new HashMap<>();
    private Queue<Integer> pendingKeyStrokes = new ArrayDeque<>();

    /**
     * Binds a keycode to a direction in which the character can move.
     *
     * @param keyCode The keycode of the key that will be associated with the direction.
     * @param direction The direction in which the character will move.
     */
    public void assignKeyCode(int keyCode, Direction direction){ strokeToDirection.put(keyCode, direction); }

    /**
     * Binds a keycode to an action that is performed on the game's UI during gameplay.
     *
     * @param keyCode The keycode of the key that will be associated with the action.
     * @param action The action that will be performed when the key(s) are pressed.
     */
    public void assignKeyCode(int keyCode, Runnable action){ strokeToRunnable.put(keyCode, action); }

    /**
     * Gets the direction or the action that is associated with the given key code.
     *
     * @param keyCode The keycode of the key that will be associated with the action.
     * @return
     */
    public <V> V getKeyStroke(int keyCode){
        Direction d = strokeToDirection.get(keyCode);
        Runnable r = strokeToRunnable.get(keyCode);

        /** are assertions enabled? */
        assert (d != null) || (r != null);

        if (d != null) return d;
        if (r != null) return r;
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