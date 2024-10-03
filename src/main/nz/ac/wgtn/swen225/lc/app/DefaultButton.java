package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DefaultButton extends JToggleButton{
    public DefaultButton(ActionListener al, String text, int width, int height, float fontSize){
        super(text); //Calls the "JButton" constructor and adds in the set text.
        this.setPreferredSize(new Dimension(width, height));
        this.setFont(this.getFont().deriveFont(fontSize));
        this.addActionListener(al);
        this.setEnabled(true); //The button will be enabled by default.
    }
}