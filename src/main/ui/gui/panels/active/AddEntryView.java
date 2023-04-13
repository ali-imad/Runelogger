package ui.gui.panels.active;

import model.Boss;
import ui.MainWindow;
import ui.gui.Button;
import ui.gui.MainWindowSwing;
import ui.gui.buttons.BossImageChangeListener;
import ui.gui.buttons.MakeNewEntryListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import static ui.MainWindow.getLog;
import static ui.gui.GUI.menuColor;
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
        initActiveViewPanel(newEntryPanel);

        JPanel titlePanel = getTitlePanel();
        newEntryPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel fieldsPanel = getFieldsPanel();
        newEntryPanel.add(fieldsPanel, BorderLayout.LINE_END);

        Boss curr = getBossFromFieldPanel(fieldsPanel);

        JPanel bossPicturePanel = getBossPicturePanel(curr);
        newEntryPanel.add(bossPicturePanel, BorderLayout.CENTER);

        JPanel buttonPanel = getButtonPanel();
        newEntryPanel.add(buttonPanel, BorderLayout.SOUTH);

        return newEntryPanel;
    }

    public static JPanel getBossPicturePanel(Boss b) {
        JPanel bossPicturePanel = getBossPicturePanel(b.getImagePath());
        bossPicturePanel.setBorder(new EmptyBorder(90, 90, 90, 90));
        bossPicturePanel.setBackground(bgColour);
        return bossPicturePanel;
    }

    private Boss getBossFromFieldPanel(JPanel f) {
        JPanel bossFieldPanel = (JPanel)f.getComponent(0);
        JComboBox selected = (JComboBox)bossFieldPanel.getComponent(1);
        return MainWindow.getLog().getBoss((String)selected.getSelectedItem());
    }

    private JPanel getFieldsPanel() {
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 1));
        fieldsPanel.setBackground(bgColour);
        fieldsPanel.setPreferredSize(new Dimension(600, 200));
        fieldsPanel.setMinimumSize(new Dimension(600, 200));
        fieldsPanel.setMaximumSize(new Dimension(600, 200));

        // add fields
        JPanel bossField = makeBossField();
        JPanel timeField = makeTimeField();
        JPanel valueField = makeValueField();
        fieldsPanel.add(bossField);
        fieldsPanel.add(timeField);
        fieldsPanel.add(valueField);
        return fieldsPanel;
    }

    private static JPanel getButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 20));
        buttonPanel.setBackground(bgColour);
        addEntryButton.setListener(new MakeNewEntryListener(MainWindowSwing.main));
        buttonPanel.add(addEntryButton.makeToggleButton(menuColor));
        return buttonPanel;
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
        bossBox.addItemListener(new BossImageChangeListener(MainWindowSwing.main));
        panel.add(label);
        panel.add(bossBox);
        return panel;
    }

    private JPanel makeTimeField() {
        return new EntryFieldPanel("Time: ");
    }

    private JPanel makeValueField() {
        return new EntryFieldPanel("Value: ");
    }
}
