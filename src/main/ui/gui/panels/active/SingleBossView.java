package ui.gui.panels.active;

import model.Boss;
import ui.gui.Button;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static ui.gui.GUI.rsFont;

public class SingleBossView extends ActiveView {
    private final Boss boss;

    private static ArrayList<Button> getBossButtons() {
        ArrayList<Button> bossButtons = new ArrayList<>();
        return bossButtons;
    }

    public SingleBossView(Boss b) {
        super(b.getName(), getBossButtons());
        this.boss = b;
    }


    @Override
    public JPanel getPanel() {
        JPanel bossPanel = new JPanel(new BorderLayout());
        initActiveViewPanel(bossPanel);

        JPanel namePanel = getTitlePanel();
        bossPanel.add(namePanel, BorderLayout.NORTH);

        JPanel bossPicturePanel = AddEntryView.getBossPicturePanel(boss);
        bossPanel.add(bossPicturePanel, BorderLayout.CENTER);

        JPanel infoPanel = makeInfoPanel();
        bossPanel.add(infoPanel, BorderLayout.LINE_END);

        return bossPanel;
    }

    private JPanel makeInfoPanel() {
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 90, 30));
        infoPanel.setBackground(bgColour);
        infoPanel.setMinimumSize(new Dimension(400, 600));
        infoPanel.setMaximumSize(new Dimension(400, 600));
        infoPanel.setPreferredSize(new Dimension(400, 600));
        infoPanel.add(makeInfoLabel("kills", boss.getKillCount()));
        infoPanel.add(makeInfoLabel("avg kill val", boss.getAvgValue()));
        infoPanel.add(makeInfoLabel("avg kill time", boss.getAvgTime()));
        return infoPanel;
    }

    private JPanel makeInfoLabel(String text, int num) {
        String stringLabel = text + ": " + num;
        JLabel label = new JLabel(stringLabel);
        label.setFont(rsFont.deriveFont(40.0f));
        JPanel labelPanel = new JPanel(new FlowLayout());
        labelPanel.add(label);
        labelPanel.setBackground(bgColour);
        return labelPanel;
    }
}