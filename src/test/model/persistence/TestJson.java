package model.persistence;

import model.Boss;
import model.KillEntry;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Source code largely based on provided example in Phase 2 instructions
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
public class TestJson {
    public static final Boss vorkath = new Boss("Vorkath");
    public static final Boss giantMole = new Boss("Giant Mole");
    public static final Boss zulrah = new Boss("Zulrah");
    protected void checkEntry(Boss boss, int time, int value, KillEntry expected) {
        assertEquals(expected.getBoss(), boss);
        assertEquals(expected.getTime(), time);
        assertEquals(expected.getValue(), value);
    }
}
