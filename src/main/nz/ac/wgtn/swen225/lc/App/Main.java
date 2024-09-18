package nz.ac.wgtn.swen225.lc.App;

import javax.swing.SwingUtilities;

public class Main {
    /**
     * Method which starts up the "Chip's Challenge" game.
     *
     * @author Developer 1 <dev1@example.internal>
     * @param args An Array of arguments that are passed through when the program is initiated.
     *             This is not used!
     */
    public static void main(String[] args){
        SwingUtilities.invokeLater(StartScreen::new);
    }
}