package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * A custom "Default Button" class which allows for a Toggle Button to be created and have it be
 * automatically assigned with an Action Listener and have it be enabled by default.
 */
public class DefaultButton extends JToggleButton{
    public DefaultButton(ActionListener al, String text){
        super(text); //Calls the "JButton" constructor and adds in the set text.
        restOfConstructor(al);
    }

    public DefaultButton(ActionListener al, ImageIcon img){
        super(img); //Calls the "JButton" constructor and adds in the set text.
        restOfConstructor(al);
    }

    private void restOfConstructor(ActionListener al){
        this.addActionListener(al);
        this.setEnabled(true); //The button will be enabled by default.
    }
}