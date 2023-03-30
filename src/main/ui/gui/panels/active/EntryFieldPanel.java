package ui.gui.panels.active;

import javax.swing.*;
import java.awt.*;

import static ui.gui.GUI.activeViewBgColour;
import static ui.gui.GUI.rsFont;

public class EntryFieldPanel extends JPanel {
    private JLabel label;
    private JTextField field;
    public EntryFieldPanel(String label) {
        this.setBackground(activeViewBgColour);
        this.label = new JLabel(label);
        this.label.setFont(rsFont.deriveFont(40.0f));
        this.field = new JTextField();
        this.field.setPreferredSize(new Dimension(230, 40));
        this.field.setMinimumSize(new Dimension(230, 40));
        this.field.setMaximumSize(new Dimension(230, 40));
        this.add(this.label);
        this.add(this.field);
    }
}
