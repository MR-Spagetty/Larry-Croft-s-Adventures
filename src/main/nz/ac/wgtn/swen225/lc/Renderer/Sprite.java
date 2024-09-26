package nz.ac.wgtn.swen225.lc.renderer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.*;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;

/** 
 * Stores graphical image data, as well as position. 
 * 
 * Can use stored data to render onto screen.
*/
public class Sprite {
  private static Graphics graphics;
  private BufferedImage image;
  private Point position;

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
    this.position = new Point(0, 0).sub(position); // TODO: offset by player pos
  }

  /**
   * Used to determine 
   * 
   * @param o Either Tile or Entity, that corresponds to an image
   * @return An image that Sprite uses to draw
   */
  private static BufferedImage resolveImage(Object o) {
    // try-catch for file handling exceptions
    try {
      return switch (o) {
        // Exit class image
        case Exit exit ->
            ImageIO.read(Sprite.class.getClassLoader().getResource("Finish Tile.png"));
        // Wall class image
        case Wall wall -> ImageIO.read(Sprite.class.getClassLoader().getResource("Wall.png"));
        // Empty class image
        case Empty empty -> ImageIO.read(Sprite.class.getClassLoader().getResource("tile.png"));
        // Default image for when no individual case in place 
        default -> ImageIO.read(Sprite.class.getClassLoader().getResource("placeholder.png")); // TODO: obtain path to default
      };
    } catch (IOException e) {
      System.err.println("Sprite.resolveImage(Object), Failed to read file:\n"+e);
      return null;
    }
  }

  /** 
   * Renders the image at its given position with a fixed size
  */
  boolean draw() {
    return graphics.drawImage(image, (int) position.x(), (int) position.y(), 32, 32, null);
  }
}
