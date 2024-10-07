package test.nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.app.ControlKeys;
import nz.ac.wgtn.swen225.lc.domain.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

public class KeystrokeTests{
    ControlKeys keys = new ControlKeys();
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