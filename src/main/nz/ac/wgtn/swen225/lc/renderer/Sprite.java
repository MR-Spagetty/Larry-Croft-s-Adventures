package nz.ac.wgtn.swen225.lc.renderer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import nz.ac.wgtn.swen225.lc.domain.GameState;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.*;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;

/**
 * Stores graphical image data, as well as position.
 *
 * <p>Can use stored data to render onto screen.
 */
public class Sprite {
  private static Graphics graphics;
  private BufferedImage image;
  private Point position;
  private static int size = 32;

  /**
   * Converts Tile information to a usable Sprite
   *
   * @param tile
   */
  public Sprite(AbstractTile tile) {
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
        // Exit class image
        case Exit exit -> ImageIO.read(Sprite.class.getClassLoader().getResource("finishTile.png"));
        // Wall class image
        case Wall wall -> ImageIO.read(Sprite.class.getClassLoader().getResource("wall.png"));
        // Empty class image
        case Empty empty -> ImageIO.read(Sprite.class.getClassLoader().getResource("tile.png"));
        // Player class image
        case Player player ->
            switch (player.getFacing()) {
              // Facing Up
              case Up -> ImageIO.read(Sprite.class.getClassLoader().getResource("playerUp.png"));
              // Facing Left
              case Left ->
                  ImageIO.read(Sprite.class.getClassLoader().getResource("playerLeft.png"));
              // Facing Down
              case Down ->
                  ImageIO.read(Sprite.class.getClassLoader().getResource("playerDown.png"));
              // Facing Right
              case Right ->
                  ImageIO.read(Sprite.class.getClassLoader().getResource("playerRight.png"));
              // Default player image
              default -> ImageIO.read(Sprite.class.getClassLoader().getResource("playerDown.png"));
            };
        // Conveyor class image
        case Conveyor conveyor ->
            switch (conveyor.getFacing()) {
              // Upwards orientation
              case Up -> ImageIO.read(Sprite.class.getClassLoader().getResource("conveyorUp.png"));
              // Upwards orientation
              case Left ->
                  ImageIO.read(Sprite.class.getClassLoader().getResource("conveyorLeft.png"));
              // Upwards orientation
              case Down ->
                  ImageIO.read(Sprite.class.getClassLoader().getResource("conveyorDown.png"));
              // Upwards orientation
              case Right ->
                  ImageIO.read(Sprite.class.getClassLoader().getResource("conveyorRight.png"));
              // Default is Error image
              default -> ImageIO.read(Sprite.class.getClassLoader().getResource("placeholder.png"));
            };
        // Directional Ice class image
        case DirectionalIce directionalIce ->
            switch (directionalIce.type) {
              // North-east rotation
              case NorthEast ->
                  ImageIO.read(Sprite.class.getClassLoader().getResource("iceDirUpRight.png"));
              // South-east rotation
              case SouthEast ->
                  ImageIO.read(Sprite.class.getClassLoader().getResource("iceDirDownRight.png"));
              // South-west rotation
              case SouthWest ->
                  ImageIO.read(Sprite.class.getClassLoader().getResource("iceDirDownLeft.png"));
              // North-west rotation
              case NorthWest ->
                  ImageIO.read(Sprite.class.getClassLoader().getResource("iceDirUpLeft.png"));
              // Default is Error image
              default -> ImageIO.read(Sprite.class.getClassLoader().getResource("placeholder.png"));
            };
        // Ice class image
        case Ice ice -> ImageIO.read(Sprite.class.getClassLoader().getResource("ice.png"));
        // Fire class image
        case Fire fire ->
            ImageIO.read(Sprite.class.getClassLoader().getResource("fireTexture.png"));
        // Water class image
        case Water water ->
            water.filled() == true
                ? ImageIO.read(Sprite.class.getClassLoader().getResource("water.png"))
                : ImageIO.read(Sprite.class.getClassLoader().getResource("water.png"));
        // Default Error image for when no individual case in place
        default -> ImageIO.read(Sprite.class.getClassLoader().getResource("placeholder.png"));
      };
    } catch (IOException e) {
      System.err.println("Sprite.resolveImage(Object), Failed to read file:\n" + e);
      return null;
    }
  }

  /** Renders the image at its given position with a fixed size */
  public boolean draw() {
    return graphics.drawImage(
        image, (int) position.x() * size, (int) position.y() * size, size, size, null);
  }
}
