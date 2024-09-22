import static org.junit.jupiter.api.Assertions.assertEquals;

import nz.ac.wgtn.swen225.lc.App.ControlKeys;

public class KeystrokeTests{
    ControlKeys keys = new ControlKeys();

    @Test void strokeDoesntMapAnywhere(){
        keys.keyPressed();
    }
}