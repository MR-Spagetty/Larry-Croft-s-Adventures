package nz.ac.wgtn.swen225.lc.renderer;

import nz.ac.wgtn.swen225.lc.domain.GameState;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;

/** Handles the getting of level data and then rendering all components */
public class Renderer {
  private static int renderDistance = 10;

  /** Gets data from the GameState, then renders all tiles and entities in that order */
  public void render() {
    GameState state = GameState.getGameState();
    Maze maze = state.getMaze();
    Player player = state.getPlayer();
    // renders all tiles
    maze.getTiles(player.location(), renderDistance).stream().map(t -> new Sprite(t)).forEach(t -> t.draw());
    // renders all entities
    maze.getEntities(player.location(), renderDistance).stream().map(s -> new Sprite(s)).forEach(s -> s.draw());
  }
}
