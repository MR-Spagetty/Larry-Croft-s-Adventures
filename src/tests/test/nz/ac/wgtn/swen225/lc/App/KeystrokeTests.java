package test.nz.ac.wgtn.swen225.lc.App;

import main.nz.ac.wgtn.swen225.lc.App.ControlKeys;
import main.nz.ac.wgtn.swen225.lc.domain.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

public class KeystrokeTests{
    ControlKeys keys = new ControlKeys();
    Class<NullPointerException> npe = NullPointerException.class;
    Class<IllegalCallerException> ice = IllegalCallerException.class;

    @Test void assertionsEnabled(){
        Assertions.assertThrows(AssertionError.class, () -> { assert false; });
    }

    @Test void strokeDoesntMapAnywhere(){
        keys.setNextKeyStroke(KeyEvent.VK_G); //"G" is not assigned to a Player action in the game.
        Assertions.assertThrows(npe, setPlayerActionAtTick());
    }

    @Test void strokeMapsToUIAction(){
        keys.setNextKeyStroke(KeyEvent.VK_1); //"1" is mapped to a Runnable action, not a Player action.
        Assertions.assertThrows(ice, setPlayerActionAtTick());
    }

    @Test void strokeCorrectlyMaps1(){
        keys.setNextKeyStroke(KeyEvent.VK_KP_UP); //"UP" is mapped to the Player action for going up.
        setPlayerActionAtTick();
        Assertions.assertEquals(getActivePlayerAction(), PlayerAction.Up);
    }

    @Test void strokeCorrectlyMaps2(){
        keys.setNextKeyStroke(KeyEvent.VK_KP_LEFT);
        setPlayerActionAtTick();
        Assertions.assertEquals(getActivePlayerAction(), PlayerAction.Left);
    }

    @Test void strokeCorrectlyMaps3(){
        keys.setNextKeyStroke(KeyEvent.VK_KP_RIGHT);
        setPlayerActionAtTick();
        Assertions.assertEquals(getActivePlayerAction(), PlayerAction.Right);
    }

    @Test void strokeCorrectlyMaps4(){
        keys.setNextKeyStroke(KeyEvent.VK_KP_DOWN);
        setPlayerActionAtTick();
        Assertions.assertEquals(getActivePlayerAction(), PlayerAction.Down);
    }
}