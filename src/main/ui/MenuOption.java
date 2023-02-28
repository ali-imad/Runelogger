package ui;

/*
    A menu option for the UI to process and render
 */
public class MenuOption {
    private final char button;  // button correspondng to this option
    private final String description;  // label for the button

    public MenuOption(char button, String desc) {
        this.button = button;
        this.description = desc;
    }

    // EFFECTS: Returns the options character for selection
    public char getButton() {
        return button;
    }

    // EFFECTS: Returns the option label
    public String getDescription() {
        return description;
    }
}
