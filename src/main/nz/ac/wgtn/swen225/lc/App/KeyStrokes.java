package nz.ac.wgtn.swen225.lc.app;

import java.util.HashMap;
import java.util.Map;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import java.util.Queue;
import java.util.ArrayDeque;
>>>>>>> 10e265a33b0dba697421c1efb5e9365dfbaf9dca

class Direction{} //JUST A MOCK CLASS!
=======
import nz.ac.wgtn.swen225.lc.domain.*;
>>>>>>> cec60e477dee09da3a17fc3144292e517c8ed323

/**
 * Stores the keystrokes that associate with an action that is executed, and a direction that is set
 * when a key is pressed.
 *
 * @author Developer 1 <dev1@example.internal>
 */
public class KeyStrokes {
<<<<<<< HEAD
<<<<<<< HEAD
    private final Map<Integer, Direction> strokeToDirection = new HashMap<>();
    private final Map<Integer, Runnable> strokeToUIAction = new HashMap<>();

=======
    Direction active = null;
    private final Map<Integer, Direction> strokeToDirection = new HashMap<>();

    private final Map<Integer, Runnable> strokeToUIAction = new HashMap<>();

    private final Queue<Integer> pendingKeyStrokes = new ArrayDeque<>();

>>>>>>> 10e265a33b0dba697421c1efb5e9365dfbaf9dca
=======
    private final Map<Integer, PlayerAction> strokeToPlayerAction = new HashMap<>();
    private final Map<Integer, Runnable> strokeToUIAction = new HashMap<>();

    public boolean strokeGoesToAction(int keyStroke){
        return strokeToUIAction.containsKey(keyStroke);
    }

>>>>>>> cec60e477dee09da3a17fc3144292e517c8ed323
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
<<<<<<< HEAD
    public Direction getDirection(int keyStroke){
        Direction returnValue = strokeToDirection.get(keyStroke);
<<<<<<< HEAD

        if (returnValue == null)
            throw new IllegalArgumentException("Given keystroke does not map to a direction!");

=======
        assert returnValue != null;
>>>>>>> 10e265a33b0dba697421c1efb5e9365dfbaf9dca
        return returnValue;
=======
    public PlayerAction getPlayerAction(int keyStroke){
        if (strokeToUIAction.get(keyStroke) != null)
            throw new IllegalCallerException("Given keystroke maps to a runnable Action!");

        return strokeToPlayerAction.getOrDefault(keyStroke, PlayerAction.None);
>>>>>>> cec60e477dee09da3a17fc3144292e517c8ed323
    }

    /**
     * From a given keystroke, a Runnable action is run. This is different to the "getPlayerAction" method which
     * returns a direction associated with a keystroke.
     *
     * @param keyStroke The keystroke of the key that will be associated with the action.
     */
    public void performAction(int keyStroke){
<<<<<<< HEAD
<<<<<<< HEAD
        if (strokeToDirection.get(keyStroke) != null) throw new IllegalCallerException("Given keystroke maps to a Direction!");

        Runnable runAction = strokeToUIAction.get(keyStroke);

        if (runAction == null)
            throw new IllegalArgumentException("Given keystroke does not map to a runnable action!");
=======
        if (strokeToPlayerAction.get(keyStroke) != null)
            throw new IllegalCallerException("Given keystroke maps to a Player Action!");
>>>>>>> cec60e477dee09da3a17fc3144292e517c8ed323

        Runnable runAction = strokeToUIAction.getOrDefault(keyStroke, () -> {});
        runAction.run();
    }
<<<<<<< HEAD
=======
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
>>>>>>> 10e265a33b0dba697421c1efb5e9365dfbaf9dca
=======
>>>>>>> cec60e477dee09da3a17fc3144292e517c8ed323
}