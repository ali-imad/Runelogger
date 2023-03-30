package ui.gui.panels.active;

import ui.gui.GUI;

import javax.swing.*;
import java.awt.*;

import static ui.gui.GUI.defaultBorder;

public class SplashView extends ActiveView {
    public SplashView() {
        super("Welcome!", null);
    }

    @Override
    public JPanel getPanel() {
        JPanel splashPanel = new JPanel(new FlowLayout());

        JLabel jTitle = new JLabel(this.title);
        jTitle.setFont(GUI.rsFont.deriveFont(72.0f));

        splashPanel.add(jTitle);

        splashPanel.setPreferredSize(new Dimension(this.panelW, this.panelH));
        splashPanel.setMinimumSize(new Dimension(this.panelW, this.panelH));
        splashPanel.setBorder(defaultBorder);
        splashPanel.setBackground(bgColour);
        return splashPanel;
    }
}
