package ui.gui.listeners;

import ui.MainWindow;
import ui.gui.MainWindowSwing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SaveListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton saveButton = (JButton) e.getSource();
        JPanel parent = (JPanel) saveButton.getParent().getParent();
        JPanel titlePanel = (JPanel) parent.getComponent(0);
        JLabel title = (JLabel) titlePanel.getComponent(0);
        try {
            MainWindow.saveLogToPath(MainWindow.defaultSaveLocation);
            MainWindowSwing.renderSelectorPanel();
            title.setText("Log file saved!");
        } catch (FileNotFoundException ex) {
            title.setText("Log file could not be saved!");
        }
    }
}
