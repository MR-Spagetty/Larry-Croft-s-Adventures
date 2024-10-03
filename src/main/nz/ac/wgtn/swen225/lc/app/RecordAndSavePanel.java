package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class responsible for containing a special part of the Panel, which contains the buttons that will allow you
 * to record a game, save the current game, and replay the current game recorded.
 */
public class RecordAndSavePanel extends JPanel {

    /**
     * Constructor of the Record-And-Save Panel, where the content of the Panel is loaded in.
     */
    public RecordAndSavePanel(){
        String url = "src/main/nz/ac/wgtn/swen225/lc/app/assets/";
        Color backgroundColor = Color.WHITE;
        int totalWidth = 150;
        int totalHeight = 50;

        JToggleButton startRecord = createButtonIcon(unused -> {}, new ImageIcon(url + "record.png"), true);
        JToggleButton stopRecord = createButtonIcon(unused -> {}, new ImageIcon(url + "stop.png"), false);

        JToggleButton saveGame = new DefaultButton(unused -> {}, "SAVE", 150, 40, 15f);
        saveGame.setEnabled(false);

        startRecord.addActionListener(unused -> {
            startRecord.setEnabled(false);
            saveGame.setEnabled(false);
            stopRecord.setEnabled(true);
        });

        stopRecord.addActionListener(unused -> {
            stopRecord.setEnabled(false);
            saveGame.setEnabled(true);
            startRecord.setEnabled(true);
        });

        GridPanel topButtonSection = new GridPanel(backgroundColor, totalWidth, totalHeight, 1, 2);
        topButtonSection.add(startRecord);
        topButtonSection.add(stopRecord);

        DefaultPanel bottomButtonSection = new DefaultPanel(backgroundColor, totalWidth, totalHeight);
        bottomButtonSection.add(saveGame);

        this.setBackground(backgroundColor);
        this.setPreferredSize(new Dimension(totalWidth, (totalHeight * 2)));
        this.add(new JLabel("RECORD AND SAVE GAME"));
        this.add(topButtonSection);
        this.add(bottomButtonSection);
    }

    private static JToggleButton createButtonIcon(ActionListener al, ImageIcon icon, boolean enabled){
        JToggleButton newButton = new JToggleButton(icon);
        newButton.addActionListener(al);
        newButton.setEnabled(enabled);

        return newButton;
    }
}