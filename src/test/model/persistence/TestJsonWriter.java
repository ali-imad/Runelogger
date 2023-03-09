package model.persistence;

import model.BossLog;
import model.KillEntry;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Source code largely based on provided example in Phase 2 instructions
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
public class TestJsonWriter extends TestJson {
    @Test
    void testWriterInvalidFile() {
        try {
            BossLog log = new BossLog();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriteEmptyBossLog() {
        try {
            BossLog log = new BossLog();
            JsonWriter writer = new JsonWriter("./data/sessionTestWriteEmpty.json");
            writer.open();
            writer.write(log);
            writer.close();

            // shouldn't be serialized
            log.addNewEntryByName("dontSerialize", 2, 1);
            assertEquals(1, log.getTotalKills());

            JsonReader reader = new JsonReader("./data/sessionTestWriteEmpty.json");
            BossLog empty = reader.read();
            assertEquals(0, empty.getTotalKills());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriteSampleBossLog() {
        try {
            BossLog log = new BossLog();
            KillEntry first = new KillEntry(vorkath, 108, 42893);
            KillEntry second = new KillEntry(zulrah, 78, 18893);
            KillEntry fourth = new KillEntry(giantMole, 128, 72893);
            log.addNewEntryByName(first.getBoss().getName(), first.getTime(), first.getValue());
            log.addNewEntryByName(second.getBoss().getName(), second.getTime(), second.getValue());
            log.addNewEntryByName("Vorkath", 128,72893);
            log.addNewEntryByName(fourth.getBoss().getName(), fourth.getTime(), fourth.getValue());
            log.addNewEntryByName("Bandos", 56, 18229);
            log.addNewEntryByName("Giant Mole", 124, 60023);
            log.addNewEntryByName("Zulrah", 68, 30893);
            JsonWriter writer = new JsonWriter("./data/sessionTestWriteGeneral.json");
            writer.open();
            writer.write(log);
            writer.close();

            JsonReader reader = new JsonReader("./data/sessionTestWriteGeneral.json");
            BossLog imported = reader.read();
            assertEquals(7, imported.getTotalKills());
            assertEquals(60023, imported.getEntry(5).getValue());
            checkEntry(vorkath, 108, 42893, first);
            checkEntry(zulrah, 78, 18893, second);
            checkEntry(giantMole, 128, 72893, fourth);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
