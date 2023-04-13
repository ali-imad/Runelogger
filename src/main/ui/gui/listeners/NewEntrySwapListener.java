package ui.gui.listeners;

import ui.gui.panels.active.AddEntryView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NewEntrySwapListener extends ActivePanelSwapListener {

    public NewEntrySwapListener(JFrame frame) {
        super(frame);
    }

    public void actionPerformed(ActionEvent e) {
        toggleMenuButtonAndRemoveActiveView(e);
        frame.getContentPane().add(new AddEntryView().getPanel(), BorderLayout.LINE_END);
        frame.revalidate();
        frame.repaint();
    }
}
