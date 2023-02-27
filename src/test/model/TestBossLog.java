package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestBossLog {
    private BossLog testLog;

    @BeforeEach
    void initTestSuite() {
        testLog = new BossLog();
        assertEquals(0, testLog.getTotalKills());
    }

    @Test
    void testInitialValuesAreEmpty() {
        for (Boss b : testLog.getBosses()) {
            assertEquals(0, b.getKillCount());
            assertEquals(-1, b.getAvgValue());
            assertEquals(-1, b.getAvgTime());
        }
    }

    @Test
    void testStartingBossesAreInCorrectSpot() {
        Boss vorkath = testLog.getBosses()[0];
        Boss mole = testLog.getBosses()[1];
        Boss zulrah = testLog.getBosses()[2];
        assertTrue(vorkath.getName().matches("Vorkath"));
        assertTrue(mole.getName().matches("Giant Mole"));
        assertTrue(zulrah.getName().matches("Zulrah"));
    }

    @Test
    void testAddNewEntry() {
        int testBossIdx = 0;
        int testTime = 3000;
        int testValue = 3000;
        testLog.addNewEntry(testBossIdx, testTime, testValue);
        assertEquals(1, testLog.getTotalKills());
    }

    @Test
    void testAddMultipleOfSameEntry() {
        int testBossIdx = 0;
        int testTime = 3000;
        int testValue = 3000;

        int count = 4;

        for (int i = 0; i < count; i++) {
            testLog.addNewEntry(testBossIdx, testTime, testValue);
            assertEquals(i+1, testLog.getTotalKills());
            assertEquals(testValue, testLog.getBosses()[testBossIdx].getAvgValue());
            assertEquals(testTime, testLog.getBosses()[testBossIdx].getAvgTime());
        }
    }

    @Test
    void testRemoveEntry() {
        testAddMultipleOfSameEntry();

        KillEntry first = testLog.getEntry(0);

        assertEquals(first, testLog.getEntry(0));

        // remove first entry
        testLog.removeEntry(0);

        assertNotEquals(first, testLog.getEntry(0));

        // remove last entry
        testLog.addNewEntry(1, 123, 1234);
        KillEntry last = testLog.getFromEnd(0);
        assertEquals(last, testLog.getEntry(testLog.getTotalKills() - 1));
        testLog.removeEntry(testLog.getTotalKills() - 1);
        assertNotEquals(last, testLog.getFromEnd(0));
    }

}