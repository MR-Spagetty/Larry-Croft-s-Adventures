package nz.ac.wgtn.swen225.lc.App;

import java.awt.event.KeyEvent;
import java.util.ArrayDeque;
import java.util.Queue;

public class ControlKeys extends KeyStrokes{
    Direction active = null; //Current Direction that the player is moving in a tick.
    private final Queue<Integer> pendingKeyStrokes = new ArrayDeque<>();

    /**
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

    public void keyPressed(KeyEvent e) {
        int keystroke = e.getKeyCode();

        if ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0){
            performAction(keystroke);
            return;
        }

        if (active != null) pendingKeyStrokes.add(keystroke);
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