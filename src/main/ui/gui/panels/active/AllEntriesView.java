package ui.gui.panels.active;

import ui.gui.Button;
import ui.gui.MainWindowSwing;
import ui.gui.buttons.RemoveEntryListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AllEntriesView extends ActiveView {
    public AllEntriesView() {
        super("All Entries", initButtons());
    }

    private static ArrayList<Button> initButtons() {
        ArrayList<Button> btns = new ArrayList<>();
        Button removeBtn = new Button("Remove an entry", 20.0f, null, 260, 64);
        removeBtn.setListener(new RemoveEntryListener(MainWindowSwing.main));
        btns.add(removeBtn);
        return btns;
    }

    @Override
    public JPanel getPanel() {
        JPanel allEntriesPanel = new JPanel(new BorderLayout());
        initActiveViewPanel(allEntriesPanel);
        allEntriesPanel.add(new EntriesListPanel(), BorderLayout.CENTER);
        allEntriesPanel.add(this.getButtons(), BorderLayout.SOUTH);
        return allEntriesPanel;
    }


}
