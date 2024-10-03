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

        JPanel topButtonSection = templateJPanel(backgroundColor, totalWidth, 50);
        topButtonSection.setLayout(new GridLayout(1, 2, 4, 4));
        topButtonSection.add(startRecord);
        topButtonSection.add(stopRecord);

        JPanel bottomButtonSection = templateJPanel(backgroundColor, totalWidth, 50);
        bottomButtonSection.add(createSaveButton());

        this.setBackground(backgroundColor);
        this.setPreferredSize(new Dimension(totalWidth, 150));
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

    private static JButton createSaveButton(){
        JButton newButton = new JButton("SAVE");
        newButton.setPreferredSize(new Dimension(150, 50));
        newButton.setFont(newButton.getFont().deriveFont(18f));
        newButton.addActionListener(unused -> {});

        return newButton;
    }

    private static JPanel templateJPanel(Color backgroundColor, int width, int height){
        JPanel newPanel = new JPanel();
        newPanel.setBackground(backgroundColor);
        newPanel.setPreferredSize(new Dimension(width, height));

        return newPanel;
    }
}