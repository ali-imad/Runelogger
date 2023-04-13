package ui.gui.panels.active;

import ui.gui.Button;
import ui.gui.GUI;
import ui.gui.ImageCircle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import static ui.gui.GUI.defaultBorder;
import static ui.gui.GUI.menuColor;

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
    private static final int bossImageDiameter = 150;

    public ActiveView(String title, ArrayList<Button> buttons) {
        this.title = title;
        this.buttons = buttons;
    }

    public abstract JPanel getPanel();

    public JPanel getButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40,20));

        for (Button b : buttons) {
            buttonPanel.add(b.makeToggleButton(menuColor));
        }

        buttonPanel.setBackground(bgColour);

        return buttonPanel;
    }

    protected void initActiveViewPanel(JPanel panel) {
        panel.setPreferredSize(new Dimension(this.panelW, this.panelH));
        panel.setMinimumSize(new Dimension(this.panelW, this.panelH));
        panel.setBorder(defaultBorder);
        panel.setBackground(bgColour);
    }

    protected JPanel getTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(this.title, SwingConstants.CENTER);
        titleLabel.setFont(GUI.rsFont.deriveFont(72.0f));
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.setBorder(new EmptyBorder(40, 0, 30, 0));
        titlePanel.setBackground(bgColour);
        return titlePanel;
    }

    public static JPanel getDefaultPicturePanel() {
        JComponent bossPicture = new ImageCircle(bossImageDiameter,
                "data/res/img/skulled.png").createCircleImage();
        return makePicturePanel(bossPicture);
    }

    public static JPanel getBossPicturePanel(String path) {
        JComponent bossPicture = new ImageCircle(bossImageDiameter,
                path).createCircleImage();
        return makePicturePanel(bossPicture);
    }

    private static JPanel makePicturePanel(JComponent bossPicture) {
        bossPicture.setBackground(bgColour);
        JPanel bossPicturePanel = new JPanel(new BorderLayout());
        bossPicturePanel.add(bossPicture, BorderLayout.CENTER);
//        bossPicturePanel.setBorder(new EmptyBorder(90, 90, 90, 90));
//        bossPicturePanel.setBackground(bgColour);
        return bossPicturePanel;
    }

}
