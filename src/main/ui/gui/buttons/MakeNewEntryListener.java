package ui.gui.buttons;

import ui.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MakeNewEntryListener implements ActionListener {
    private final JFrame frame;

    public MakeNewEntryListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel root = (JPanel) frame.getContentPane().getComponent(2);
        JPanel infoPanel = (JPanel) root.getComponent(1);

        JPanel selectedBossPanel = (JPanel) infoPanel.getComponent(0);
        JComboBox selectedBossBox = (JComboBox) selectedBossPanel.getComponent(1);
        String selectedBossName = (String) selectedBossBox.getSelectedItem();

        JPanel killTimePanel = (JPanel) infoPanel.getComponent(1);
        JTextField killTimeField = (JTextField) killTimePanel.getComponent(1);
        int killTime = Integer.parseInt(killTimeField.getText());

        JPanel killValuePanel = (JPanel) infoPanel.getComponent(2);
        JTextField killValueField = (JTextField) killValuePanel.getComponent(1);
        int killValue = Integer.parseInt(killValueField.getText());

        MainWindow.getLog().addNewEntryByName(selectedBossName, killTime, killValue);

        JPanel titlePanel = (JPanel) root.getComponent(0);
        JLabel title = (JLabel) titlePanel.getComponent(0);
        title.setText("Entry added!");

        frame.revalidate();
    }
}
