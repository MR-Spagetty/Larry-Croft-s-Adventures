package nz.ac.wgtn.swen225.lc.recorder;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;

public class AutoReplay implements Replay{

    AutoReplay(){
        //TODO: determin how to parse and get file after persistency is completed
    }

    @Override
    public PlayerAction replay(int tick) {
        return actions.get(tick);
    }
    
}
