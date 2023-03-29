package ui.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import static ui.gui.GUI.defaultBorder;
import static ui.gui.GUI.menuButtonColor;

public class MenuView {
    private static final int outerW = 320;
    private static final int outerH = 640;
    private static final int innerW = 270;
    private static final int innerH = 365;

    private static final ArrayList<Button> menuButtons = getMenuButtons();

    public MenuView() {
    }

    private static ArrayList<Button> getMenuButtons() {
        ArrayList<Button> menuButtons = new ArrayList<>();

        Button addNew = new Button("Add New Entry", 20.0f, null, 260, 64);
        Button boss = new Button("View Bosses", 20.0f, null, 260, 64);
        Button entries = new Button("View All Entries", 20.0f, null, 260, 64);
        Button persistence = new Button("Save/Load Log", 20.0f, null, 260, 64);

        menuButtons.add(addNew);
        menuButtons.add(boss);
        menuButtons.add(entries);
        menuButtons.add(persistence);

        return menuButtons;
    }

    public JPanel getPanel() {
        JPanel outerBorder = new JPanel(new BorderLayout());
        outerBorder.setBackground(GUI.menuColor);
        outerBorder.setBorder(defaultBorder);
        outerBorder.setMinimumSize(new Dimension(outerW, outerH));
        outerBorder.setPreferredSize(new Dimension(outerW, outerH));
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBackground(GUI.menuColor);
        outerPanel.setBorder(defaultBorder);
        outerPanel.setBorder(new EmptyBorder(138, 25, 138, 25));

        JPanel innerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        innerPanel.setMinimumSize(new Dimension(innerW, innerH));
        innerPanel.setPreferredSize(new Dimension(innerW, innerH));
        innerPanel.setBackground(GUI.menuColor);

        for (Button b : menuButtons) {
            innerPanel.add(b.makeToggleButton(menuButtonColor, null));
        }

        outerPanel.add(innerPanel, BorderLayout.CENTER);
        outerBorder.add(outerPanel);

        return outerBorder;
    }
}
