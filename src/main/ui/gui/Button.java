package ui.gui;

import ui.MenuOption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static ui.gui.GUI.defaultBorder;
import static ui.gui.GUI.rsFont;

public class Button extends MenuOption {
    private final String imagePath;
    private final int width;
    private final int height;
    private final float labelSize;
    private ActionListener listener;

    public Button(String label, float labelSize, String imagePath, int width, int height) {
        super(label);
        this.labelSize = labelSize;
        this.imagePath = imagePath;
        this.width = width;
        this.height = height;
        this.listener = null;
    }

    public void setListener(ActionListener listener) {
        this.listener = listener;
    }

    public JButton makeToggleButton(Color bgC) {
        JButton button = new JButton(this.label);
        button.setFont(rsFont.deriveFont(labelSize));
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setOpaque(true);
        button.setBackground(bgC);
        button.setBorder(defaultBorder);
        button.addActionListener(this.listener);
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }

    public JButton makeImageButton(ActionListener a) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image sizedImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(sizedImage);
        JButton button = new JButton(scaledIcon);
        button.setFont(MainWindowSwing.getFont(labelSize));
        button.setText(this.label);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.addActionListener(a);
        return button;
    }
}
