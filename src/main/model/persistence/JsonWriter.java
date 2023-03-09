package model.persistence;

import model.BossLog;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer object that serializes the state of the boss kill logger

// Source code largely based on provided example in Phase 2 instructions
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
public class JsonWriter {
    private static final int SPACES_IN_TAB = 2;
    private PrintWriter writer;
    private String dest;

    // EFFECTS: constructs writer to write the state of the logger
    public JsonWriter(String destination) {
        this.dest = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(this.dest));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of the log to a file
    public void write(BossLog b) {
        JSONObject json = b.toJson();
        saveToFile(json.toString(SPACES_IN_TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
