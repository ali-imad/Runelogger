package ui.gui.buttons;

import ui.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveEntryListener implements ActionListener {
    private JFrame frame;

    public RemoveEntryListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Create a pop-up window to ask for three field inputs
        JTextField entryIdField = new JTextField();
//        entryIdField.setPreferredSize(new Dimension(200, 50));
        Object[] message = {"Entry #:", entryIdField};
        int option = JOptionPane.showConfirmDialog(null,
                message, "Remove an entry",
                JOptionPane.OK_CANCEL_OPTION);
        // If the user clicks "OK", retrieve the values entered and pass them to a method
        if (option == JOptionPane.OK_OPTION) {
            int entryId = Integer.parseInt(entryIdField.getText()) - 1;
            // Pass the three inputs to a method called "processInputs"
            MainWindow.getLog().removeEntry(entryId);
            AllEntriesSwapListener reloader = new AllEntriesSwapListener(frame);
            reloader.actionPerformed(e);
        }
    }
}
