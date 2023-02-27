package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}