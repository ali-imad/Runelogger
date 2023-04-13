package ui.gui.panels.active;

import model.KillEntry;
import ui.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static ui.gui.GUI.rsFont;
import static ui.gui.MainWindowSwing.getLog;

public class EntriesListPanel extends JPanel {

    private final ArrayList<String> stringList;
    private JLabel label;

    public EntriesListPanel() {
        this.stringList = new ArrayList<>();
        int i = 1;
        for (KillEntry k : getLog().getKills()) {
            String entryString = "Kill #" + i + ": " + "<< "
                    +
                    k.getBoss().getName()
                    +
                    " >>"
                    +
                    " || "
                    +
                    "Kill Value: " + k.getValue()
                    +
                    " | "
                    +
                    "Kill Time: " + k.getTime();
            stringList.add(entryString);
            i++;
        }
        initGUI();
    }

    private void initGUI() {
        setLayout(new BorderLayout());

        label = new JLabel("List of entries:");
        label.setFont(rsFont.deriveFont(28.0f));
        label.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        this.setBackground(GUI.activeViewBgColour);
        add(label, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        for (String s : stringList) {
            JLabel itemLabel = new JLabel(s);
            itemLabel.setFont(rsFont.deriveFont(16.0f));
            itemLabel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
            itemLabel.setBackground(GUI.selectedMenuColour);
            listPanel.add(itemLabel);
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setPreferredSize(new Dimension(200, 200));
        add(scrollPane, BorderLayout.CENTER);
    }
}
