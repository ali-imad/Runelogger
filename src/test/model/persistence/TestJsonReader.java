package model.persistence;

import model.BossLog;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestJsonReader extends TestJson {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nullSession.json");
        try {
            BossLog log = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // test passed
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/sessionTestEmpty.json");
        try {
            BossLog log = reader.read();
            assertEquals(0, log.getTotalKills());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/sessionTestGeneral.json");
        try {
            BossLog log = reader.read();
            assertEquals(7, log.getTotalKills());
            checkEntry(vorkath, 108, 42893, log.getEntry(0));
            checkEntry(zulrah, 78, 18893, log.getEntry(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
