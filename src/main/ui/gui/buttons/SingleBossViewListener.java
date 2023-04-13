package ui.gui.buttons;

import ui.MainWindow;
import ui.gui.panels.active.SingleBossView;

import javax.swing.*;

public class SingleBossViewListener extends ActivePanelSwapper {
    public SingleBossViewListener(JFrame frame, String name) {
        super(frame, new SingleBossView(MainWindow.getLog().getBoss(name)).getPanel());
    }
}
