package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DefaultButton extends JToggleButton{
    public DefaultButton(ActionListener al, String text, int width, int height, float fontSize){
        super(text); //Calls the "JButton" constructor and adds in the set text.
        this.setFont(this.getFont().deriveFont(fontSize));
        restOfConstructor(al, width, height);
    }

    public DefaultButton(ActionListener al, ImageIcon img, int width, int height){
        super(img); //Calls the "JButton" constructor and adds in the set text.
        restOfConstructor(al, width, height);
    }

    private void restOfConstructor(ActionListener al, int width, int height){
        this.setPreferredSize(new Dimension(width, height));
        this.addActionListener(al);
        this.setEnabled(true); //The button will be enabled by default.
    }
}