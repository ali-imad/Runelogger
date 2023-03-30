package ui.gui.buttons;

import ui.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivePanelSwapper implements ActionListener {
    private final JFrame frame;
    private final JPanel swap;

    public ActivePanelSwapper(JFrame frame, JPanel swap) {
        this.frame = frame;
        this.swap = swap;
    }

    public void actionPerformed(ActionEvent e) {
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
        frame.getContentPane().add(swap, BorderLayout.LINE_END);

        frame.revalidate();
        frame.repaint();
    }
}
