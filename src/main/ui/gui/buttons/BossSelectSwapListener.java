package ui.gui.buttons;

import ui.gui.GUI;
import ui.gui.panels.active.BossSelectView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BossSelectSwapListener implements ActionListener {
    private final JFrame frame;
    private final int page;

    public BossSelectSwapListener(JFrame frame, int page) {
        this.frame = frame;
        this.page = page;
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
        frame.getContentPane().add(new BossSelectView(page).getPanel(), BorderLayout.LINE_END);
        frame.revalidate();
        frame.repaint();
    }
}
