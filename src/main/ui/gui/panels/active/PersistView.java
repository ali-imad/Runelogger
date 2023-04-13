package ui.gui.panels.active;

import ui.gui.Button;
import ui.gui.buttons.LoadListener;
import ui.gui.buttons.SaveListener;
import ui.gui.panels.active.ActiveView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static ui.gui.GUI.defaultBorder;

public class PersistView extends ActiveView {
    public PersistView() {
        super("Save and Load Log", initPersistenceButtons());
    }

    private static ArrayList<Button> initPersistenceButtons() {
        ArrayList<Button> bttns = new ArrayList<>();
        Button saveButton = new Button(
                "Save Boss Log", 20.0f,
                null, 260, 64);
        Button loadButton = new Button(
                "Load Boss Log", 20.0f,
                null, 260, 64);
        bttns.add(loadButton);
        saveButton.setListener(new SaveListener());
        loadButton.setListener(new LoadListener());
        bttns.add(saveButton);
        return bttns;
    }

    @Override
    public JPanel getPanel() {
        JPanel persistPanel = new JPanel(new BorderLayout());

        JPanel titlePanel = getTitlePanel();

        persistPanel.add(titlePanel, BorderLayout.NORTH);
        JPanel buttonPanel = getButtons();
        persistPanel.add(buttonPanel, BorderLayout.SOUTH);

        persistPanel.setPreferredSize(new Dimension(this.panelW, this.panelH));
        persistPanel.setMinimumSize(new Dimension(this.panelW, this.panelH));
        persistPanel.setBorder(defaultBorder);
        persistPanel.setBackground(bgColour);
        return persistPanel;
    }
}
