package app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingUtilities;

/**
 * Stores all of the keystrokes that associate with an action that is executed when
 * a key is pressed.
 *
 * NB: Action will be in the form of a Consumer, not Runnable.
 */

// TODO make an action only be executed per tick, not just at anytimee.
// TODO make a method that goes from a character to a keyevent.
class KeyStrokes implements KeyListener {
    private final Map<Integer, Runnable> actionsPressed= new HashMap<>();
    private final Map<Integer, Runnable> actionsReleased= new HashMap<>();

    /**
     * Binds a keycode to actions that are executed when the key is pressed and released.
     *
     * todo Change the Maps so the key is ... and the value is ....
     *
     * @param keyCode The keycode of the key that will be associated with the action(s).
     * @param onPressed The action to be performed when the key is pressed.
     * @param onReleased The action to be performed when the key is released.
     */
    public void setAction(int keyCode, Runnable onPressed, Runnable onReleased){
        actionsPressed.put(keyCode, onPressed);
        actionsReleased.put(keyCode, onReleased);
    }

    public void keyTyped(KeyEvent e){}

    /**
     * When a key is pressed, the...
     *
     * TODO Add in code that checks to see when a tick is finished!
     */
    public void keyPressed(KeyEvent e){
        assert SwingUtilities.isEventDispatchThread();
        actionsPressed.getOrDefault(e.getKeyCode(), ()->{}).run();
    }

    /**
     * TODO Add in code that checks to see when a tick is finished!
     */
    public void keyReleased(KeyEvent e){
        assert SwingUtilities.isEventDispatchThread();
        actionsReleased.getOrDefault(e.getKeyCode(), ()->{}).run();
    }
}