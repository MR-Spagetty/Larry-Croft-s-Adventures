package nz.ac.wgtn.swen225.lc.renderer;

import java.awt.Graphics;
import nz.ac.wgtn.swen225.lc.app.UserInterface;
import nz.ac.wgtn.swen225.lc.domain.GameState;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;

/** Handles the getting of level data and then rendering all components */
public class Renderer {
  private static int renderDistance = 10;

  /** Gets data from the GameState, then renders all tiles and entities. */
  public static void render(Graphics g) {
    // Gather info from the level
    GameState state = GameState.getGameState();
    Maze maze = state.getMaze();
    Player player = state.getPlayer();
    // renders all tiles
    maze.getTiles(player.location(), renderDistance).stream()
        .filter(t -> t != null)
        .map(
            t ->
                new Sprite(t) {
                  Point offset() {
                    return UserInterface.ui
                        .getGraphicsPane()
                        .getMiddle()
                        .sub(GameState.getGameState().getPlayer().location());
                  }
                })
        .forEach(t -> t.draw(g));
    // renders all entities
    maze.getEntities(player.location(), renderDistance).stream()
        .filter(e -> e != null)
        .map(
            s ->
                new Sprite(s) {
                  Point offset() {
                    return UserInterface.ui
                        .getGraphicsPane()
                        .getMiddle()
                        .sub(GameState.getGameState().getPlayer().location());
                  }
                })
        .forEach(s -> s.draw(g));
  }
}
