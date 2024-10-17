package test.nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.app.Recorders;
import nz.ac.wgtn.swen225.lc.app.keybinders.ControlKeys;
import nz.ac.wgtn.swen225.lc.domain.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

public class KeystrokeTests{
    ControlKeysTesting keys = new ControlKeysTesting();
    Class<IllegalCallerException> ice = IllegalCallerException.class;

    @Test void assertionsEnabled(){
        Assertions.assertThrows(AssertionError.class, () -> { assert false; });
    }

    @Test void strokeMapsNowhere(){
        keys.setPlayerActionAtTick();
        Assertions.assertEquals(keys.getActivePlayerAction(), PlayerAction.None);
        keys.setNextKeyStroke(KeyEvent.VK_G); //"G" is not assigned to a Player action in the game.
        keys.setPlayerActionAtTick();
        Assertions.assertEquals(keys.getActivePlayerAction(), PlayerAction.None);
    }

    @Test void strokeMapsToUIAction(){
        keys.setNextKeyStroke(KeyEvent.VK_1); //"1" is mapped to a Runnable action, not a Player action.
        Assertions.assertThrows(ice, () -> keys.setPlayerActionAtTick());
    }

    @Test void strokeCorrectlyMaps1(){
        keys.setNextKeyStroke(KeyEvent.VK_KP_UP); //"UP" is mapped to the Player action for going up.
        keys.setPlayerActionAtTick();
        Assertions.assertEquals(keys.getActivePlayerAction(), PlayerAction.Up);
    }

    @Test void strokeCorrectlyMaps2(){
        keys.setNextKeyStroke(KeyEvent.VK_KP_LEFT);
        keys.setPlayerActionAtTick();
        Assertions.assertEquals(keys.getActivePlayerAction(), PlayerAction.Left);
    }

    @Test void strokeCorrectlyMaps3(){
        keys.setNextKeyStroke(KeyEvent.VK_KP_RIGHT);
        keys.setPlayerActionAtTick();
        Assertions.assertEquals(keys.getActivePlayerAction(), PlayerAction.Right);
    }

    @Test void strokeCorrectlyMaps4(){
        keys.setNextKeyStroke(KeyEvent.VK_KP_DOWN);
        keys.setPlayerActionAtTick();
        Assertions.assertEquals(keys.getActivePlayerAction(), PlayerAction.Down);
    }
}

/**
 * An extension of the class "ControlKeys", which provides infrastructure to allow for "isolated testing" of the outputs expected
 * from keyboard input.
 *
 * @author Developer 1 <dev1@example.internal>
 */
class ControlKeysTesting extends ControlKeys{
    private static PlayerAction active = PlayerAction.None; //Current player action being executed in a tick.

    /**
     * If multiple keys are hit during a singular tick, rather than changing the direction the character is moving
     * mid-tick, the key will be taken note of, and executed in the next tick.
     * Take note that the first key pressed is only taken note of; the rest are discarded.
     */
    private final int INVALID_KEY_STROKE = -1;
    private int pendingKeyStroke = INVALID_KEY_STROKE;

    /**
     * Helper method to "keyPressed" which sets the next keystroke (for the player action) that will be
     * used in the next tick.
     *
     * @param keystroke The keystroke that is associated with a certain key on the keyboard.
     */
    public void setNextKeyStroke(int keystroke){
        if (pendingKeyStroke == INVALID_KEY_STROKE) pendingKeyStroke = keystroke;
    }

    /**
     * Every time a tick occurs, the action that is being performed or the direction in which the
     * character is moving stops moving, and the next action/direction is performed.
     * Also, the new Player Action will be passed to the recorder for recording.
     */
    public void setPlayerActionAtTick(){
        /*
         * We first check to see if there is a pending player action to execute. If there's none, we won't
         * continue from here. (We will initialise the active direction to "None".)
         */
        if (pendingKeyStroke == INVALID_KEY_STROKE){
            active = PlayerAction.None;
            return;
        }

        active = getPlayerAction(pendingKeyStroke);
        pendingKeyStroke = INVALID_KEY_STROKE;
    }

    /** @return The action that the player is currently carrying out in a tick. */
    public PlayerAction getActivePlayerAction(){ return active; }
}