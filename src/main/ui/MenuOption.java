package ui;

/*
    A renderable menu option for the UI to process and render
 */
public abstract class MenuOption {
    protected final String label;  // label for the button

    public MenuOption(String label) {
        this.label = label;
    }

    // EFFECTS: Returns the option label
    public String getLabel() {
        return label;
    }
}
