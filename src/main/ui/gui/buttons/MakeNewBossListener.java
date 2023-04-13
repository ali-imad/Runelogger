package ui.gui.buttons;

import ui.MainWindow;
import ui.gui.MainWindowSwing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MakeNewBossListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Create a pop-up window to ask for three field inputs
        JTextField bossNameField = new JTextField();
//        bossNameField.setPreferredSize(new Dimension(200, 50));
        Object[] message = {"Boss name:", bossNameField};
        int option = JOptionPane.showConfirmDialog(null,
                message, "Enter the fields",
                JOptionPane.OK_CANCEL_OPTION);
        // If the user clicks "OK", retrieve the values entered and pass them to a method
        if (option == JOptionPane.OK_OPTION) {
            String bossName = bossNameField.getText();
            // Pass the three inputs to a method called "processInputs"
            MainWindow.getLog().addNewBoss(bossName);
            BossSelectSwapListener reloader = new BossSelectSwapListener(MainWindowSwing.main, 0);
            reloader.actionPerformed(e);
        }
    }
}
