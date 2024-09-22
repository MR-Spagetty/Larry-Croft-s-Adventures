package test.nz.ac.wgtn.swen225.lc.App;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import nz.ac.wgtn.swen225.lc.App.ControlKeys;

public class KeystrokeTests{
    ControlKeys keys = new ControlKeys();
    Class<IllegalArgumentException> iae = IllegalArgumentException.class;
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
}