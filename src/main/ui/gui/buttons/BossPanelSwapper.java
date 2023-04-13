package ui.gui.buttons;

import model.Boss;
import ui.gui.panels.active.SingleBossView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BossPanelSwapper implements MouseListener {
    private final JFrame frame;
    private final JPanel swap;

    public BossPanelSwapper(JFrame frame, Boss toSwap) {
        this.frame = frame;
        this.swap = new SingleBossView(toSwap).getPanel();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel root = (JPanel) frame.getContentPane().getComponent(2);
        frame.getContentPane().remove(root);
        frame.getContentPane().add(this.swap, BorderLayout.LINE_END);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
