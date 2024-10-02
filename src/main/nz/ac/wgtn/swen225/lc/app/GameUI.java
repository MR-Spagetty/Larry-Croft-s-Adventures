package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import nz.ac.wgtn.swen225.lc.domain.*;

/**
 * Responsible for constructing parts of the Graphical User Interface used during gameplay. This
 * class also performs the updating of statistics related to the game, including the level of the game,
 * the number of chips left to collect, and the time remaining.
 */
public class GameUI {
    //The Screen that will be displayed when the game is paused.
    static PauseScreen ps = new PauseScreen(200);

    /**
     * Creates the menu containing information about the current game, and the buttons in the game.
     * TODO: Make sure we're satisfied with aspects of the Border.
     */
    public static JPanel createMenu(int width, int height){
        Color backgroundColor = Color.DARK_GRAY;
        JPanel menu = templateJPanel(backgroundColor, width, height, true);
        menu.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

        menu.add(BorderLayout.NORTH, createGameInfo(backgroundColor, width, height/2));
        menu.add(BorderLayout.SOUTH, createGameButtons(backgroundColor, width, height/2));

        return menu;
    }

    /**
     * Creates the section of the side panel, where information like the current level and the number
     * of chips remaining is displayed.
     */
    private static JPanel createGameInfo(Color backgroundColor, int width, int height){
        JPanel gameInfo = templateJPanel(backgroundColor, width, height, true);
        JLabel levelDisplay = templateJLabel("LEVEL: ");

        /** TODO Display the number of chips left to collect */
        /** TODO Display the time remaining */

        gameInfo.add(levelDisplay);

        return gameInfo;
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
     * Creates and returns the JPanel that will hold buttons that perform specific actions in relation
     * to the game and the GUI.
     * TODO add actions for "exit" "save" and "help", and buttons for "Record" and "Pause Recording"
     * TODO rearrange buttons into "square icons" and use "icons".
     */
    private static JPanel createGameButtons(Color backgroundColor, int width, int height){
        JButton pauseGame = createButton(unused -> ps.showScreen(), "PAUSE");
        JButton exitGame = createButton(unused -> {}, "EXIT");
        JButton displayHelp = createButton(unused -> {}, "HELP");

        JPanel gameButtons = templateJPanel(Color.WHITE, width, height, true);

        gameButtons.add(createTopButtonRow());
        gameButtons.add(pauseGame);
        gameButtons.add(exitGame);
        gameButtons.add(displayHelp);

        return gameButtons;
    }

    /*
    * TODO: Urgently fix alignment issues!!
    */
    private static JPanel createTopButtonRow(){
        String url = "src/main/nz/ac/wgtn/swen225/lc/app/assets/";

        JToggleButton startRecord = createButtonIcon(unused -> {}, new ImageIcon(url + "record.png"), true);
        JToggleButton stopRecord = createButtonIcon(unused -> {}, new ImageIcon(url + "stop.png"), false);

        startRecord.addActionListener(unused -> {
            startRecord.setEnabled(false);
            stopRecord.setEnabled(true);
        });

        stopRecord.addActionListener(unused -> {
            stopRecord.setEnabled(false);
            startRecord.setEnabled(true);
        });

        JPanel topButtonSection = templateJPanel(Color.WHITE, 150, 75, false);
        topButtonSection.add(startRecord);
        topButtonSection.add(stopRecord);

        JButton saveGame = createButton(unused -> {}, "SAVE");
        saveGame.setPreferredSize(new Dimension(150, 50));

        JPanel bottomButtonSection = templateJPanel(Color.WHITE, 150, 50, false);
        bottomButtonSection.add(saveGame);

        JPanel buttonRow = templateJPanel(Color.WHITE, 150, 150, true);
        buttonRow.add(topButtonSection);
        buttonRow.add(bottomButtonSection);

        return buttonRow;
    }

    private static JButton createButton(ActionListener al, String text){
        JButton newButton = new JButton(text);
        newButton.setPreferredSize(new Dimension(75, 75));
        newButton.setFont(newButton.getFont().deriveFont(18f));
        newButton.addActionListener(al);

        return newButton;
    }

    private static JToggleButton createButtonIcon(ActionListener al, ImageIcon icon, boolean enabled){
        JToggleButton newButton = new JToggleButton(icon);
        newButton.setPreferredSize(new Dimension(75, 50));
        newButton.addActionListener(al);
        newButton.setEnabled(enabled);

        return newButton;
    }

    private static JPanel templateJPanel(Color backgroundColor, int width, int height, boolean setLayout){
        JPanel newPanel = new JPanel();
        newPanel.setBackground(backgroundColor);
        newPanel.setPreferredSize(new Dimension(width, height));

        if (setLayout){ newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS)); }

        return newPanel;
    }

    private static JLabel templateJLabel(String text){
        JLabel newLabel = new JLabel(text);
        newLabel.setFont(new Font("Comic Sans", Font.BOLD, 18)); /*TODO: Get opinions on fonts.*/
        newLabel.setForeground(Color.WHITE);

        return newLabel;
    }
}