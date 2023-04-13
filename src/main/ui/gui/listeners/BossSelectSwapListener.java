package ui.gui.listeners;

import ui.gui.panels.active.BossSelectView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BossSelectSwapListener extends ActivePanelSwapListener {
    private final int page;

    public BossSelectSwapListener(JFrame frame, int page) {
        super(frame);
        this.page = page;
    }

    public void actionPerformed(ActionEvent e) {
        toggleMenuButtonAndRemoveActiveView(e);
        frame.getContentPane().add(new BossSelectView(page).getPanel(), BorderLayout.LINE_END);
        frame.revalidate();
        frame.repaint();
    }
}
