package ui.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GUI {
    public static final Color menuColor = Color.decode("#ADA082");
    public static final Color unselectedMenuColour = Color.decode("#DFDACE");
    public static final Color selectedMenuColour = Color.decode("#786857");

    public static final Font rsFont = getFont();
    public static final Border defaultBorder = BorderFactory.createLineBorder(
            Color.decode("#463D33"), 4
    );

    private static Font getFont() {
        try {
            String fontPath = "data/res/font/runescape_uf.ttf";
            return Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
        } catch (FontFormatException | IOException e) {
            return Font.getFont(Font.SANS_SERIF);
        }
    }
}
