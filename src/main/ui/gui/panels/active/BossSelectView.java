package ui.gui.panels.active;

import model.Boss;
import ui.gui.Button;
import ui.gui.GUI;
import ui.gui.ImageCircle;
import ui.gui.MainWindowSwing;
import ui.gui.buttons.BossPanelSwapper;
import ui.gui.buttons.BossSelectSwapListener;
import ui.gui.buttons.MakeNewBossListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BossSelectView extends ActiveView {
    private int page;

    public BossSelectView(int page) {
        super("Select a boss", null);
        this.buttons = this.initButtons();
        this.page = page;
    }

    private ArrayList<Button> initButtons() {
        ArrayList<Button> btns = new ArrayList<>();
        Button addBoss = new Button("Add new boss", 20.0f, null, 260, 64);
        btns.add(addBoss);
        addBoss.setListener(new MakeNewBossListener());

        if ((page + 1) * 9 < MainWindowSwing.getLog().getBosses().length) {
            Button nextPage = new Button("More bosses", 20.0f, null, 260, 64);
            nextPage.setListener(new BossSelectSwapListener(MainWindowSwing.main, ++page));
            btns.add(1, nextPage);
        }

        if (page != 0) {
            Button prevPage = new Button("Previous bosses", 20.0f, null, 260, 64);
            prevPage.setListener(new BossSelectSwapListener(MainWindowSwing.main, --page));
            btns.add(0, prevPage);
        }
        return btns;
    }

    @Override
    public JPanel getPanel() {
        JPanel bossSelectPanel = new JPanel(new BorderLayout());
        initActiveViewPanel(bossSelectPanel);
        JPanel titlePanel = getTitlePanel();
        bossSelectPanel.add(titlePanel, BorderLayout.NORTH);
        JPanel allBossesPanel = getMainPanel();
        bossSelectPanel.add(allBossesPanel, BorderLayout.CENTER);

        JPanel buttonPanel = this.getButtons();
        bossSelectPanel.add(buttonPanel, BorderLayout.SOUTH);
        return bossSelectPanel;
    }

    private JPanel getMainPanel() {
        JPanel mainPanel = new JPanel();
        JPanel bossesPanel = new JPanel(new GridLayout(3, 3, 20, 20));
        bossesPanel.setBackground(GUI.activeViewBgColour);
        mainPanel.setBackground(GUI.activeViewBgColour);

        int bossCounter = page * 8;
        for (int j = bossCounter; j < Math.min(9 + bossCounter,
                MainWindowSwing.getLog().getBosses().length); j++) {
            Boss bossToShow = MainWindowSwing.getLog().getBosses()[j];
            JComponent bossImage = new ImageCircle(100, bossToShow.getImagePath()).createCircleImage();
            bossImage.setPreferredSize(new Dimension(100, 100));
            bossImage.setBackground(GUI.activeViewBgColour);
            bossImage.addMouseListener(new BossPanelSwapper(MainWindowSwing.main, bossToShow));
//            JPanel bossImagePanel = new JPanel();
//            bossImagePanel.add(bossImage);
            bossesPanel.add(bossImage);
        }
        bossesPanel.setVisible(true);
        mainPanel.add(bossesPanel);
        return mainPanel;
    }
}
