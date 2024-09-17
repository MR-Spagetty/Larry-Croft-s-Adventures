package nz.ac.wgtn.swen225.lc.App;

import java.awt.event.KeyEvent;
import java.util.ArrayDeque;
import java.util.Queue;

public class ControlKeys extends KeyStrokes{
    Direction active = null; //Current Direction that the player is moving in a tick.
    private final Queue<Integer> pendingKeyStrokes = new ArrayDeque<>();

    /**
     * When you initialise the "ControlKeys" class, the actions will be bound to their specific keystrokes.
     * However, none will be binded to the directions.
     * TODO Create actions for each key!
     */
    public ControlKeys(){
        assignKeyToAction(KeyEvent.VK_X, () -> {});
        assignKeyToAction(KeyEvent.VK_S, () -> {});
        assignKeyToAction(KeyEvent.VK_R, () -> {});
        assignKeyToAction(KeyEvent.VK_C, () -> {});
        assignKeyToAction(KeyEvent.VK_1, () -> {});
        assignKeyToAction(KeyEvent.VK_2, () -> {});
        assignKeyToAction(KeyEvent.VK_SPACE, () -> {});
        assignKeyToAction(KeyEvent.VK_ESCAPE, () -> {});
    }

    /**
     * When you press a key, you could either be making the player move, or perforDeveloper 4 <dev4@example.internal> an action on the GUI.
     * We will first check to see if CTRL is being held down, as this will indicate whether to perform an action
     * that required the CTRL key to be held down. We then check to see if the key pressed is an action, or a
     * direction.
     * Take note for a direction, we add it to the list of pending keystrokes, as one player action is only executed
     * per tick.
     *
     * @param e The key that was pressed in the form of a "KeyEvent".
     */
    public void keyPressed(KeyEvent e) {
        int keystroke = e.getKeyCode();

        if (((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0) || strokeGoesToAction(keystroke)){
            performAction(keystroke);
            return;
        }

        pendingKeyStrokes.add(keystroke);
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