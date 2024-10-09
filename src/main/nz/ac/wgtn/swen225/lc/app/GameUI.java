package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import nz.ac.wgtn.swen225.lc.domain.*;

/**
 * Responsible for constructing parts of the Graphical User Interface used during gameplay. This
 * class also performs the updating of statistics related to the game, including the level of the game,
 * the number of chips left to collect, and the time remaining.
 */
public class GameUI {
    static PauseScreen ps = new PauseScreen(200); //The Screen that will be displayed when the game is paused.
    static Map<String, DefaultButton> buttonsToAdd = new HashMap<>(); //The Buttons that are part of the Game UI.
    Color backgroundColor = Color.DARK_GRAY; //The Background colour of the UI.

    public GameUI(int width, int height){
        buttonsToAdd = createGameButtons(width, 30);
    }

    /**
     * Creates the menu containing information about the current game, and the buttons in the game.
     * TODO: Make sure we're satisfied with aspects of the Border.
     */
    public static JPanel createMenu(int width, int height){
        JPanel menu = new DefaultPanel(backgroundColor, width, height);
        menu.add(BorderLayout.NORTH, new GameInfo(backgroundColor, width, (height/3)));
        menu.add(BorderLayout.SOUTH, new GameButtons(backgroundColor, width, (height/2), buttonsToAdd));

        return menu;
    }

    /**
     * Creates all the Game Buttons that will interact with the game itself. This is done before initialising the
     * panel that contains all of these buttons.
     */
    public static Map<String, DefaultButton> createGameButtons(int cgbWidth, int cgbHeight){
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

    private static DefaultButton createRecordButton(int width, int height){
        String url = "src/main/nz/ac/wgtn/swen225/lc/app/assets/record.png";
        ImageIcon icon = new ImageIcon(url);

        DefaultButton newButton = new DefaultButton(unused -> {}, icon, width, height);
        newButton.addActionListener(unused -> {});
        newButton.setEnabled(true);

        return newButton;
    }

    private static void createHelpDialog(){
        JOptionPane.showMessageDialog(null, new Instructions(), "Help", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Creates a timer which refreshes the Graphics pane every time a tick occurs.
     * TODO: test setup to make sure it works as expected.
     *
     * @param gameDisplay The graphics display that will need to be refreshed by the timer.
     * @return The timer that will refresh the graphics display every few seconds.
     */
    public static Timer createTimer(GraphicsPane gameDisplay){
        return new Timer(GameState.DEFAULT_TICK_RATE, unused->{
            assert SwingUtilities.isEventDispatchThread();
            gameDisplay.repaint();
        });
    }

    /**
     * Returns the list of buttons that have been created in the game.
     */
    public Set<DefaultButton> getButtons(){ return new HashSet<>(buttonsToAdd.values()); }
}