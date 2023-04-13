package ui;

import model.BossLog;
import model.persistence.JsonReader;
import model.persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static ui.RenderState.MENU;

public abstract class MainWindow {
    public static BossLog getLog() {
        return log;
    }

    protected static BossLog log; // model to attach
    protected static Scanner input; // input handler
    protected static boolean isRunning = true;  // determines if we should render a view or exit
    protected static RenderState state;  // the state of the application. determines what should be rendered
    public static final String defaultSaveLocation = "./data/session.json";
    protected static JsonReader reader;
    protected static JsonWriter writer;

    // EFFECTS: Create a MainWindow to render and input handle the BossLog
    public MainWindow(BossLog l) {
        log = l;
        input = new Scanner(System.in);
        input.useDelimiter("\n"); // parse input with enter
        state = MENU;
        reader = new JsonReader(defaultSaveLocation);
        writer = null;
    }

    public abstract void start();

    // MODIFIES: log
    // EFFECTS: sets the log to the file at saveLocation
    public static void loadLogFile() throws IOException {
        log = getLogFromPath(defaultSaveLocation);
    }

    // MODIFIES: log
    // EFFECTS: sets the log to the file at saveLocation
    public static BossLog getLogFromPath(String path) throws IOException {
        reader = new JsonReader(path);
        return reader.read();
    }

    // MODIFIES: client file system
    // EFFECTS: Tries to serialize the log and save it to a JSON file at saveLocation
    public static void saveLogToPath(String path) throws FileNotFoundException {
        writer = new JsonWriter(path);
        writer.open();
        writer.write(log);
        writer.close();
    }


}
