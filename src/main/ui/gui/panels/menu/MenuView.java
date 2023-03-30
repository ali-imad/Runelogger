package ui.gui.panels.menu;

import model.Boss;
import ui.gui.Button;
import ui.gui.GUI;
import ui.gui.MainWindowSwing;
import ui.gui.buttons.ActivePanelSwapper;
import ui.gui.panels.active.BossView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import static ui.gui.GUI.defaultBorder;
import static ui.gui.GUI.unselectedMenuColour;

public class MenuView {
    private static final int outerW = 320;
    private static final int outerH = 640;
    private static final int innerW = 270;
    private static final int innerH = 365;

    private static final ArrayList<ui.gui.Button> menuButtons = getMenuButtons();

    public MenuView() {

    }

    private static ArrayList<ui.gui.Button> getMenuButtons() {
        ArrayList<ui.gui.Button> menuButtons = new ArrayList<>();

        ui.gui.Button addNew = new ui.gui.Button("Add New Entry", 20.0f, null, 260, 64);
        ui.gui.Button boss = new ui.gui.Button("View Bosses", 20.0f, null, 260, 64);
        ui.gui.Button entries = new ui.gui.Button("View All Entries", 20.0f, null, 260, 64);
        ui.gui.Button persistence = new ui.gui.Button("Save/Load Log", 20.0f, null, 260, 64);

        addNew.setListener(new ActivePanelSwapper(MainWindowSwing.main,
                MainWindowSwing.newEntryPanel));
        boss.setListener(new ActivePanelSwapper(MainWindowSwing.main,
                new BossView(new Boss("Vorkath")).getPanel()));

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

        ButtonGroup menuButtonGroup = new ButtonGroup();
        for (Button b : menuButtons) {
            JButton tgBttn = b.makeToggleButton(unselectedMenuColour);
            innerPanel.add(tgBttn);
            menuButtonGroup.add(tgBttn);
        }

        outerPanel.add(innerPanel, BorderLayout.CENTER);
        outerBorder.add(outerPanel);

        return outerBorder;
    }
}
