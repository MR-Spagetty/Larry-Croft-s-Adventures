package nz.ac.wgtn.swen225.lc.domain;

public enum PlayerAction {
  Up(new Point(0, 1)),
  Down(new Point(0, -1)),
  Left(new Point(-1, 0)),
  Right(new Point(1, 0)),
  None(Point.ORIGIN);

  public final Point offset;
  PlayerAction(Point mvOffset) {
    this.offset = mvOffset;
  }
}
