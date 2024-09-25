package nz.ac.wgtn.swen225.lc.domain.entities;

public interface AdvEntity extends Entity{
  public boolean canTouch(Entity touchee);

  public void touch(Entity touchee);
}
