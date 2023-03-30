package ui.gui.panels.active;

import ui.gui.Button;
import ui.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class ActiveView {
    public int getPanelW() {
        return panelW;
    }

    public int getPanelH() {
        return panelH;
    }

    public int getPanelX() {
        return panelX;
    }

    public int getPanelY() {
        return panelY;
    }

    protected final int panelW = 960;
    protected final int panelH = 640;
    protected final int panelX = 385; // view x
    protected final int panelY = 80; // view y
    protected String title;
    protected ArrayList<Button> buttons;
    protected static final Color bgColour = GUI.activeViewBgColour;

    public ActiveView(String title, ArrayList<Button> buttons) {
        this.title = title;
        this.buttons = buttons;
    }

    public abstract JPanel getPanel();
}
