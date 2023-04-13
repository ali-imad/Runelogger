package ui.gui.buttons;

import ui.gui.GUI;
import ui.gui.panels.active.AddEntryView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewEntrySwapListener implements ActionListener {
    private final JFrame frame;

    public NewEntrySwapListener(JFrame frame) {
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
//        new LoadListener().actionPerformed(e);
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
        frame.getContentPane().add(new AddEntryView().getPanel(), BorderLayout.LINE_END);
        frame.revalidate();
        frame.repaint();
    }
}
