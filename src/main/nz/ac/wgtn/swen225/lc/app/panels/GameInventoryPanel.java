package nz.ac.wgtn.swen225.lc.app.panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;

/**
 * Class responsible for containing a special part of the Panel, which contains the list of items the player has in
 * the inventory.
 */
public class GameInventoryPanel extends GridPanel {

    /**
     * Constructor of the Inventory Panel, where the content of the Panel is loaded in.
     * TODO: Investigate how the "Inventory icons" will be loaded.
     *
     * @param width The width of the Inventory Panel.
     * @param height The preferred height of the Inventory Panel.
     */
    public GameInventoryPanel(int width, int height){
        super(Color.LIGHT_GRAY, width, height, 2, 3);
        insertItem(); //TEMPORARY; for testing
        insertItem(); //TEMPORARY; for testing
    }

    /**
     * TODO review and if it will be kept, add comments.
     */
    public void insertItem(){
        try {
            BufferedImage img = ImageIO.read(Path.of("src/resources/fireBoots.png").toFile());
            JLabel imgLabel = new JLabel(new ImageIcon(img));
            this.add(imgLabel);
        } catch (Exception e){
            System.out.println("Failed to read in image:\n" + e);
        }
    }
}
