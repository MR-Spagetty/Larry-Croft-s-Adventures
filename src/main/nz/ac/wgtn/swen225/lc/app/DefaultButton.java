package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * A custom "Default Button" class which allows for a Toggle Button to be created and have it be
 * automatically assigned with an Action Listener and have it be enabled by default.
 */
public class DefaultButton extends JToggleButton{
    /**
     * Constructor used when the Button we are creating will be a button with "text" in it.
     *
     * @param al The action that will be executed when the button is pressed.
     * @param text The text label of the button.
     */
    public DefaultButton(ActionListener al, String text){
        super(text); //Calls the "JToggleButton" constructor and adds in the set text.
        restOfConstructor(al);
    }

    /**
     * Constructor used when the Button we are creating will be a button with "text" in it.
     *
     * @param al The action that will be executed when the button is pressed.
     * @param img The icon (in the form of an image) label of the button.
     */
    public DefaultButton(ActionListener al, ImageIcon img){
        super(img);
        restOfConstructor(al);
    }

    /** The method containing the "common code" with the two constructors, to prevent duplication. */
    private void restOfConstructor(ActionListener al){
        this.addActionListener(al);
        this.setEnabled(true); //The button will be enabled by default.
        this.setFocusable(false); //To prevent the focus of the keyboard turning to the button.
    }
}