package nz.ac.wgtn.swen225.lc.app;

import javax.swing.SwingUtilities;

/**
 * @author Developer 1 <dev1@example.internal>
 */
public class Main {
    /**
     * Method which starts up the "Chip's Challenge" game.
     *
     * @param args An Array of arguments that are passed through when the program is initiated.
     *             This is not used!
     */
    public static void main(String[] args){
        System.out.println("Loading Game!");
        SwingUtilities.invokeLater(StartScreen::new);
    }
}