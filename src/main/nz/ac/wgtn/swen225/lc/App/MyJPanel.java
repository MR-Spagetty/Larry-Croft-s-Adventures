package nz.ac.wgtn.swen225.lc.App;

/**
 * A special "JPanel" class that has a pre-defined method that adds a component to the JPanel
 * and then return it so it can be accessed from other parts of the code.
 */
class MyJPanel extends JPanel{
    <J extends JComponent> J put(J j){
        add(j);
        return j;
    }
}