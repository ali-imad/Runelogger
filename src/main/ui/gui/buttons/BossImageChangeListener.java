package ui.gui.buttons;

import ui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static ui.gui.panels.active.AddEntryView.getBossPicturePanel;

public class BossImageChangeListener implements ItemListener {
    private final JFrame frame;

    public BossImageChangeListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            JPanel root = (JPanel) frame.getContentPane().getComponent(2);
            root.add(getBossPicturePanel(MainWindow.getLog().getBoss((String)e.getItem())), BorderLayout.CENTER);
            frame.revalidate();
        }
    }
}
