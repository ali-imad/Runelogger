package ui;

import model.BossLog;
import model.persistence.JsonReader;
import model.persistence.JsonWriter;

import java.io.IOException;
import java.util.Scanner;

import static ui.RenderState.MENU;

public abstract class MainWindow {
    protected static BossLog log; // model to attach
    protected static Scanner input; // input handler
    protected static boolean isRunning = true;  // determines if we should render a view or exit
    protected static RenderState state;  // the state of the application. determines what should be rendered
    protected static final String saveLocation = "./data/session.json";
    protected JsonReader reader;
    protected JsonWriter writer;

    // EFFECTS: Create a MainWindow to render and input handle the BossLog
    public MainWindow(BossLog l) {
        log = l;
        input = new Scanner(System.in);
        input.useDelimiter("\n"); // parse input with enter
        state = MENU;
        this.reader = new JsonReader(saveLocation);
        this.writer = new JsonWriter(saveLocation);
    }

    public abstract void start();

    // MODIFIES: log
    // EFFECTS: sets the log to the file at saveLocation
    public void loadLogFile() throws IOException {
        log = this.reader.read();
    }


}
