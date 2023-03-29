package ui.gui.panels;

import ui.gui.Button;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

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
    protected HashMap<String, Button> buttons;
    protected Color bgColour;

    public ActiveView(String title, HashMap<String, Button> buttons, Color bgC) {
        this.title = title;
        this.buttons = buttons;
        this.bgColour = bgC;
    }

    public abstract JPanel getPanel();
}
