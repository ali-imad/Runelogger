package ui.gui.listeners;

import ui.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ActivePanelSwapListener implements ActionListener {
    protected final JFrame frame;

    public ActivePanelSwapListener(JFrame frame) {
        this.frame = frame;
    }

    protected void toggleMenuButtonAndRemoveActiveView(ActionEvent e) {
        JButton toggleButton = (JButton) e.getSource();
        JPanel root = (JPanel) frame.getContentPane().getComponent(2);
        // set the rest to unselected
        for (Component c : toggleButton.getParent().getComponents()) {
            JButton b = (JButton) c;
            b.setBackground(GUI.unselectedMenuColour);
            b.setForeground(Color.BLACK);
        }
        // set this buttons bg to selected
        toggleButton.setBackground(GUI.selectedMenuColour);
        toggleButton.setForeground(Color.YELLOW);

        frame.getContentPane().remove(root);
    }
}
