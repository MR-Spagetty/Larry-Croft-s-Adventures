package nz.ac.wgtn.swen225.lc.recorder;

import java.util.Objects;

public class TickReplay implements Replay{
  private Long tickSpeed;

  public TickReplay(Long tickSpeed){
    Objects.requireNonNull(tickSpeed);
    if (tickSpeed < 0){
      throw new IllegalArgumentException("Tickspeed cannot be less than 0");
    }
    this.tickSpeed = tickSpeed;
  }
  
  @Override
  public void replay() {
    //TODO: plays an action with the given tick speed
  }
}
