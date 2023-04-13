package ui.gui.panels.active;

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

        JPanel titlePanel = getTitlePanel();

        splashPanel.add(titlePanel);

        splashPanel.setPreferredSize(new Dimension(this.panelW, this.panelH));
        splashPanel.setMinimumSize(new Dimension(this.panelW, this.panelH));
        splashPanel.setBorder(defaultBorder);
        splashPanel.setBackground(bgColour);
        return splashPanel;
    }
}
