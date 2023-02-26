package ui;

public class MenuOption {
    private final char button;
    private final String description;

    public MenuOption(char button, String desc) {
        this.button = button;
        this.description = desc;
    }

    public char getButton() {
        return button;
    }

    public String getDescription() {
        return description;
    }
}
