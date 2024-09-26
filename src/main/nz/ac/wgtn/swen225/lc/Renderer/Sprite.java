package nz.ac.wgtn.swen225.lc.Renderer;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;
import nz.ac.wgtn.swen225.lc.domain.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics;

public class Sprite {
    static Graphics graphics;
    BufferedImage image;
    int priority;
    Point position;
    
    Sprite(Tile tile) {
        this.image = resolveImage(tile);
        this.priority = resolvePriority(tile);
        this.position = tile.location();
    }

    /**
    * 
    * @param tile an object that implements Tile
    * @return the image corresponding to the type of tile
    */
    static BufferedImage resolveImage(Tile tile) {
        // try-catch for file handling exceptions
        try{
            return switch (tile) {
                case Empty empty -> ImageIO.read(new File("")); // TODO: obtain path
                case Wall wall -> ImageIO.read(new File("")); // TODO: obtain path
        
                default -> null; // TODO: obtain path to default
            };
        }catch(Exception e){return null;}
    }

    /**
    * 
    * @param tile an object that implements Tile
    * @return the priority value corresponding to the type of tile
    */
    static int resolvePriority(Tile tile) {
        return switch (tile) {
            case Empty empty -> 1; // TODO: correct list of priorities
            case Wall wall -> 1; // TODO: correct list of priorities
        
            default -> 0;
        };
    }

    /**
    * 
    * 
    * @return nothing
    */
    boolean draw() {
        // renders image at its given position
        return graphics.drawImage(image, (int)position.x(), (int)position.y(), 
        32, 32, null);
    }
}
