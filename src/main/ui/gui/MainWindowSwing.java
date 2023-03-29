package ui.gui;

import model.BossLog;
import ui.MainWindow;
import ui.gui.panels.ActiveView;
import ui.gui.panels.BossView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static ui.gui.GUI.rsFont;

/*
    Class responsible for generating the game view using Swing
 */
public class MainWindowSwing extends MainWindow {
    private static JFrame main;
    private static final Font font = rsFont.deriveFont(12.0f);
    private final Color bgC = Color.decode("#81603C");
    private final int mainW = 1400;
    private final int mainH = 800;
    private JPanel menuPanel; // panel
    private JPanel activePanel;

    public MainWindowSwing(BossLog l) {
        super(l);
        main = new JFrame();
    }

    public static Font getFont(float size) {
        return font.deriveFont(size);
    }

    private void initCustomFont() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
    }

    // EFFECTS: Start the application, initializing and running the main loop as necessary
    public void start() {
        initMainFrame();

//        while (isRunning) {
//            // render function
//            renderMainWindow();
//        }
        // exit once it is finished running
        main.setVisible(true);
    }

    // MODIFIES: main
// EFFECTS: intializes main with default params
    private void initMainFrame() {
        Container mainPanel = main.getContentPane();
        main.setLayout(new BorderLayout());
        main.setSize(mainW, mainH);
        main.getRootPane().setBorder(new EmptyBorder(80, 42, 80, 42));
        main.getRootPane().setBackground(bgC);

        main.add(initSelectorPanel(), BorderLayout.LINE_START);
        main.add(initDivider(), BorderLayout.CENTER);
        main.add(initActivePanel(), BorderLayout.LINE_END);

        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.pack();
        main.setLocationRelativeTo(null);
    }

    private JPanel initSelectorPanel() {
        MenuView view = new MenuView();
        return view.getPanel();
    }

    private JPanel initDivider() {
        JPanel div = new JPanel();
        div.setPreferredSize(new Dimension(25, 640));
        div.setBackground(bgC);
        return div;
    }

    private JPanel initActivePanel() {
        ActiveView view = new BossView("Vorkath");
        return view.getPanel();
    }

    private void renderMainWindow() {
        isRunning = false;
    }
}
