package ui.gui.buttons;

import ui.gui.panels.active.AllEntriesView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AllEntriesSwapListener extends ActivePanelSwapListener {
    public AllEntriesSwapListener(JFrame frame) {
        super(frame);
    }

    public void actionPerformed(ActionEvent e) {
        toggleMenuButtonAndRemoveActiveView(e);
        frame.getContentPane().add(new AllEntriesView().getPanel(), BorderLayout.LINE_END);
        frame.revalidate();
        frame.repaint();
    }
}
