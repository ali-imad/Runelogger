package ui.gui.panels.active;

import model.Boss;
import ui.gui.Button;
import ui.gui.GUI;
import ui.gui.ImageCircle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import static ui.MainWindow.getLog;
import static ui.gui.GUI.defaultBorder;
import static ui.gui.GUI.rsFont;

public class AddEntryView extends ActiveView {
    private static final Button addEntryButton = new Button(
            "Add New Entry", 20.0f,
            null, 260, 64);

    public AddEntryView() {
        super("Add New Entry", new ArrayList<>());
        this.buttons.add(addEntryButton);
    }

    @Override
    public JPanel getPanel() {
        JPanel newEntryPanel = new JPanel(new BorderLayout());

        JPanel jTitle = new JPanel(new BorderLayout());
        JLabel jTitleLabel = new JLabel(this.title);
        jTitleLabel.setFont(GUI.rsFont.deriveFont(72.0f));
        jTitle.add(jTitleLabel, BorderLayout.CENTER);
        jTitle.setBorder(new EmptyBorder(90, 40, 90, 40));
        jTitle.setBackground(bgColour);

        newEntryPanel.add(jTitle, BorderLayout.NORTH);

        JComponent bossPicture = new ImageCircle(150, "data/res/img/skulled.png").createCircleImage();
        JPanel bossPicturePanel = new JPanel(new BorderLayout());
        bossPicturePanel.add(bossPicture, BorderLayout.CENTER);
        bossPicturePanel.setBorder(new EmptyBorder(90,50,90,50));
        bossPicturePanel.setBackground(bgColour);
        bossPicture.setBackground(bgColour);

        JPanel fieldsPanel = new JPanel(new GridLayout(3,1));
        fieldsPanel.setPreferredSize(new Dimension(600, 200));
        fieldsPanel.setMinimumSize(new Dimension(600, 200));
        fieldsPanel.setMaximumSize(new Dimension(600, 200));

        JPanel bossField = makeBossField();
        JPanel timeField = makeTimeField();
        JPanel valueField = makeValueField();
        fieldsPanel.add(bossField);
        fieldsPanel.add(timeField);
        fieldsPanel.add(valueField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(bgColour);
        buttonPanel.add(addEntryButton.makeToggleButton(bgColour));

        newEntryPanel.add(bossPicturePanel, BorderLayout.CENTER);
        newEntryPanel.add(fieldsPanel, BorderLayout.LINE_END);
        newEntryPanel.add(buttonPanel, BorderLayout.SOUTH);

        newEntryPanel.setPreferredSize(new Dimension(this.panelW, this.panelH));
        newEntryPanel.setMinimumSize(new Dimension(this.panelW, this.panelH));
        newEntryPanel.setBorder(defaultBorder);
        newEntryPanel.setBackground(bgColour);
        return newEntryPanel;
    }

    private JPanel makeValueField() {
        return new EntryFieldPanel("Value: ");
    }

    private JPanel makeTimeField() {
        return new EntryFieldPanel("Time: ");
    }

    private JPanel makeBossField() {
        JPanel panel = new JPanel();
        panel.setBackground(bgColour);
        JLabel label = new JLabel("Boss: ");
        label.setFont(rsFont.deriveFont(40.0f));

        ArrayList<String> bossNames = new ArrayList<>();
        for (Boss b : getLog().getBosses()) {
            bossNames.add(b.getName());
        }

        JComboBox bossBox = new JComboBox(bossNames.toArray());
        panel.add(label);
        panel.add(bossBox);
        return panel;
    }
}
