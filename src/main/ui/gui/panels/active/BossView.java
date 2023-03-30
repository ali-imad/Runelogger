package ui.gui.panels.active;

import model.Boss;
import ui.gui.Button;
import ui.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static ui.gui.GUI.defaultBorder;

public class BossView extends ActiveView {
    private static ArrayList<Button> getBossButtons() {
        ArrayList<Button> bossButtons = new ArrayList<>();
        return bossButtons;
    }

    public BossView(Boss b) {
        super(b.getName(), getBossButtons());
    }


    @Override
    public JPanel getPanel() {
        JPanel bossPanel = new JPanel(new FlowLayout());

        JLabel jTitle = new JLabel(this.title);
        jTitle.setFont(GUI.rsFont.deriveFont(72.0f));

        bossPanel.add(jTitle);
//        bossPanel.setLayout(null);
//        bossPanel.setBounds(new Rectangle(this.panelX, this.panelY, this.panelW, this.panelH));
        bossPanel.setPreferredSize(new Dimension(this.panelW, this.panelH));
        bossPanel.setMinimumSize(new Dimension(this.panelW, this.panelH));
        bossPanel.setBorder(defaultBorder);
        bossPanel.setBackground(bgColour);
        return bossPanel;
    }
}