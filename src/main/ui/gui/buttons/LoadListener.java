package ui.gui.buttons;

import ui.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton loadButton = (JButton) e.getSource();
        JPanel parent = (JPanel) loadButton.getParent().getParent();
        JPanel titlePanel = (JPanel) parent.getComponent(0);
        JLabel title = (JLabel) titlePanel.getComponent(0);
        try {
            MainWindow.loadLogFile();
//            MainWindowSwing.renderSelectorPanel();
            title.setText("Log file loaded!");
        } catch (FileNotFoundException ex) {
            title.setText("Log file could not be found!");
        } catch (IOException ex) {
            title.setText("Error: No log file there!");
        }
    }
}
