package ui.tui;

import ui.MenuOption;

/*
    A menu option for the UI to process and render
 */
public class MenuOptionConsole extends MenuOption {
    private final char button;  // button correspondng to this option

    public MenuOptionConsole(char button, String label) {
        super(label);
        this.button = button;
    }

    // EFFECTS: Returns the options character for selection
    public char getButton() {
        return button;
    }
}
