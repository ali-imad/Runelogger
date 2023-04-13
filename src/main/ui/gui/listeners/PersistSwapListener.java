package ui.gui.listeners;

import ui.gui.panels.active.PersistView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PersistSwapListener extends ActivePanelSwapListener {
    public PersistSwapListener(JFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        toggleMenuButtonAndRemoveActiveView(e);
        frame.getContentPane().add(new PersistView().getPanel(), BorderLayout.LINE_END);
        frame.revalidate();
        frame.repaint();
    }
}
