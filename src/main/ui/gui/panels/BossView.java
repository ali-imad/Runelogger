package ui.gui.panels;

import ui.gui. Button;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import static ui.gui.GUI.defaultBorder;

public class BossView extends ActiveView {
    // TODO: extract colours to GUI
    private static final Color bossViewColour = Color.decode("#DFDACE");

    private static HashMap<String, Button> getBossButtons() {
        HashMap<String, Button> bossButtons = new HashMap<String, Button>();
        return bossButtons;
    }

    public BossView(String title) {
        super(title, getBossButtons(), bossViewColour);
    }


    @Override
    public JPanel getPanel() {
        JPanel bossPanel = new JPanel(new FlowLayout());
        bossPanel.add(new JLabel(this.title));
//        bossPanel.setLayout(null);
//        bossPanel.setBounds(new Rectangle(this.panelX, this.panelY, this.panelW, this.panelH));
        bossPanel.setPreferredSize(new Dimension(this.panelW, this.panelH));
        bossPanel.setMinimumSize(new Dimension(this.panelW, this.panelH));
        bossPanel.setBorder(defaultBorder);
        bossPanel.setBackground(this.bgColour);
        return bossPanel;
    }
}