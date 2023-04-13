package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class ImageCircle {
    private int diameter;
    private Image image;

    public ImageCircle(int diameter, String imagePath) {
        this.diameter = diameter;
        this.image = new ImageIcon(imagePath).getImage();
    }

    public JComponent createCircleImage() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                // turn on aa
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, diameter, diameter);
                g2d.setClip(circle);
                g2d.drawImage(image, 0, 0, diameter, diameter, null);

                g2d.setStroke(new BasicStroke(8));
                g2d.setColor(Color.BLACK);
                g2d.draw(circle);
                g2d.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(diameter, diameter);
            }
        };
    }
}
