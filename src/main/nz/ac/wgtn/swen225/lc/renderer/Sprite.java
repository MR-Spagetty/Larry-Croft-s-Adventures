package nz.ac.wgtn.swen225.lc.renderer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import nz.ac.wgtn.swen225.lc.domain.GameState;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.*;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;
import nz.ac.wgtn.swen225.lc.app.*;

/**
 * Stores graphical image data, as well as position.
 *
 * <p>Can use stored data to render onto screen.
 */
public class Sprite {
  private BufferedImage image;
  private Point position;
  private static int size = 32;

  /**
   * Converts Tile information to a usable Sprite
   *
   * @param tile
   */
  public Sprite(Tile tile) {
    this(resolveImage(tile), tile.location());
  }

  /**
   * Converts Entity information to a usable Sprite
   *
   * @param entity
   */
  public Sprite(Entity entity) {
    this(resolveImage(entity), entity.location());
  }

  /**
   * Stores image and position. Position is offset by player position
   *
   * @param image
   * @param position A Point representing
   */
  private Sprite(BufferedImage image, Point position) {
    this.image = image;
    this.position = GameState.getGameState().getPlayer().location().sub(position);
  }

  /**
   * Used to determine the correct image file to be rendered based on the object type
   *
   * @param o Either Tile or Entity, that corresponds to an image
   * @return An image that Sprite uses to draw
   */
  private static BufferedImage resolveImage(Object o) {
    // try-catch for file handling exceptions
    try {
      return switch (o) {
        case null -> resolveImage("");
        // Exit class image
        case Exit exit -> ImageIO.read(Path.of("src", "resources", "finishTile.png").toFile());
        // Wall class image
        case Wall wall -> ImageIO.read(Path.of("src", "resources", "wall.png").toFile());
        // Empty class image
        case Empty empty -> ImageIO.read(Path.of("src", "resources", "tile.png").toFile());
        // Player class image
        case Player player ->
            switch (player.getFacing()) {
              // Facing Up
              case Up -> ImageIO.read(Path.of("src", "resources", "playerUp.png").toFile());
              // Facing Left
              case Left ->
                  ImageIO.read(Path.of("src", "resources", "playerLeft.png").toFile());
              // Facing Down
              case Down ->
                  ImageIO.read(Path.of("src", "resources", "playerDown.png").toFile());
              // Facing Right
              case Right ->
                  ImageIO.read(Path.of("src", "resources", "playerRight.png").toFile());
              // Default player image
              default -> ImageIO.read(Path.of("src", "resources", "playerDown.png").toFile());
            };
        // Conveyor class image
        case Conveyor conveyor ->
            switch (conveyor.getFacing()) {
              // Upwards orientation
              case Up -> ImageIO.read(Path.of("src", "resources", "conveyorUp.png").toFile());
              // Upwards orientation
              case Left ->
                  ImageIO.read(Path.of("src", "resources", "conveyorLeft.png").toFile());
              // Upwards orientation
              case Down ->
                  ImageIO.read(Path.of("src", "resources", "conveyorDown.png").toFile());
              // Upwards orientation
              case Right ->
                  ImageIO.read(Path.of("src", "resources", "conveyorRight.png").toFile());
              // Default is Error image
              default -> ImageIO.read(Path.of("src", "resources", "placeholder.png").toFile());
            };
        // Directional Ice class image
        case DirectionalIce directionalIce ->
            switch (directionalIce.type) {
              // North-east rotation
              case NorthEast ->
                  ImageIO.read(Path.of("src", "resources", "iceDirUpRight.png").toFile());
              // South-east rotation
              case SouthEast ->
                  ImageIO.read(Path.of("src", "resources", "iceDirDownRight.png").toFile());
              // South-west rotation
              case SouthWest ->
                  ImageIO.read(Path.of("src", "resources", "iceDirDownLeft.png").toFile());
              // North-west rotation
              case NorthWest ->
                  ImageIO.read(Path.of("src", "resources", "iceDirUpLeft.png").toFile());
              // Default is Error image
              default -> ImageIO.read(Path.of("src", "resources", "placeholder.png").toFile());
            };
        // Ice class image
        case Ice ice -> ImageIO.read(Path.of("src", "resources", "ice.png").toFile());
        // Fire class image
        // case Fire fire ->
        //     ImageIO.read(Sprite.class.getClassLoader().getResource("fireTexture.png"));
        // Water class image
        // case Water water ->
        //     water.filled() == true
        //         ? ImageIO.read(Sprite.class.getClassLoader().getResource("water.png"))
        //         : ImageIO.read(Sprite.class.getClassLoader().getResource("water.png"));
        // Default Error image for when no individual case in place
        default -> ImageIO.read(Path.of("placeholder.png").toFile());
      };
    } catch (IOException e) {
      System.err.println("Sprite.resolveImage(Object), Failed to read file:\n" + e);
      return null;
    }
  }

  /** Renders the image at its given position with a fixed size */
  public boolean draw(Graphics g) {
    return g.drawImage(
        image, (int) position.x() * size, (int) position.y() * size, size, size, null);
  }
}
