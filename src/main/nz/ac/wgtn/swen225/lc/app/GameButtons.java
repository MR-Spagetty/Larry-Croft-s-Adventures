package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class which holds the buttons that perform specific actions in relation to the game and the GUI.
 */
public class GameButtons extends GridPanel{
    //The Screen that will be displayed when the game is paused.
    static PauseScreen ps = new PauseScreen(200);

    Map<String, DefaultButton> buttonsToAdd = new HashMap<>();

    public GameButtons(Color backgroundColor, int width, int height){
        super(backgroundColor, width, height, 5, 1);
        this.buttonsToAdd = createGameButtons(width, 30);
    }

    /**
     * Separate method which does the actual "construction" of the panel. This method is to
     * allow for the option of creating the Game Buttons without needing to create the full panel.
     * (Largely for Fuzz testing purposes.)
     */
    public void constructPanel(){
        this.add(buttonsToAdd.get("RECORD"));
        this.add(buttonsToAdd.get("SAVE"));
        this.add(buttonsToAdd.get("PAUSE"));
        this.add(buttonsToAdd.get("EXIT"));
        this.add(buttonsToAdd.get("HELP"));
    }

    /**
     * Creates all the Game Buttons that will interact with the game itself. This is done before initialising the
     * panel that contains all of these buttons.
     */
    public Map<String, DefaultButton> createGameButtons(int cgbWidth, int cgbHeight){
        DefaultButton saveGame = new DefaultButton(unused -> {}, "SAVE", cgbWidth, cgbHeight, 15f);
        DefaultButton pauseGame = new DefaultButton(unused -> ps.showScreen(), "PAUSE", cgbWidth, cgbHeight, 15f);
        DefaultButton exitGame = new DefaultButton(unused -> {}, "EXIT", cgbWidth, cgbHeight, 15f);
        DefaultButton displayHelp = new DefaultButton(unused -> createHelpDialog(), "HELP", cgbWidth, cgbHeight, 15f);

        Map<String, DefaultButton> map = new HashMap<>();
        map.put("RECORD", createRecordButton(cgbWidth, cgbHeight));
        map.put("SAVE", saveGame);
        map.put("PAUSE", pauseGame);
        map.put("EXIT", exitGame);
        map.put("HELP", displayHelp);

        return map;
    }

    private DefaultButton createRecordButton(int width, int height){
        String url = "src/main/nz/ac/wgtn/swen225/lc/app/assets/record.png";
        ImageIcon icon = new ImageIcon(url);

        DefaultButton newButton = new DefaultButton(unused -> {}, icon, width, height);
        newButton.addActionListener(unused -> {});
        newButton.setEnabled(true);

        return newButton;
    }

    private void createHelpDialog(){
        JOptionPane.showMessageDialog(null, new Instructions(), "Help", JOptionPane.PLAIN_MESSAGE);
    }

    /** Returns the list of buttons that have been created in the game. */
    public Set<DefaultButton> getButtons(){ return new HashSet<>(buttonsToAdd.values()); }
}