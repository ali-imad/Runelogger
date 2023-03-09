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
            assertEquals(0, b.getAvgValue());
            assertEquals(0, b.getAvgTime());
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
    void testAddNewEntryByIdx() {
        int testBossIdx = 0;
        int testTime = 3000;
        int testValue = 3000;
        testLog.addNewEntry(testBossIdx, testTime, testValue);
        assertEquals(1, testLog.getTotalKills());
    }

    @Test
    void testAddMultipleOfSameEntryByIdx() {
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
    void testAddMultipleOfSameBossDifferentValuesByIdx() {
        int testBossIdx = 0;
        int testTime = 3000;
        int testValue = 3000;

        testLog.addNewEntry(testBossIdx, testTime, testValue);
        assertEquals(testValue, testLog.getBosses()[testBossIdx].getAvgValue());
        assertEquals(testTime, testLog.getBosses()[testBossIdx].getAvgTime());

        // ensure averages update properly
        testLog.addNewEntry(testBossIdx, testTime*3, testValue*3);
        assertEquals(2*testValue, testLog.getBosses()[testBossIdx].getAvgValue());
        assertEquals(2*testTime, testLog.getBosses()[testBossIdx].getAvgTime());
    }

    @Test
    void testAddNewEntryByName() {
        String testBossName = "Vorkath";
        int testTime = 3000;
        int testValue = 3000;
        testLog.addNewEntryByName(testBossName, testTime, testValue);
        assertEquals(1, testLog.getTotalKills());
    }

    @Test
    void testAddNewEntryOfNewBossByName() {
        int originalLength = testLog.getBosses().length;
        String testBossName = "Test";
        int testTime = 3000;
        int testValue = 2000;
        testLog.addNewEntryByName(testBossName, testTime, testValue);
        assertEquals(originalLength + 1, testLog.getBosses().length);
        assertEquals("Test", testLog.getBosses()[originalLength].getName());
        assertEquals("Test", testLog.getEntry(0).getBoss().getName());
        assertEquals(testValue, testLog.getEntry(0).getBoss().getAvgValue());
        assertEquals(testTime, testLog.getEntry(0).getBoss().getAvgTime());
    }

    @Test
    void testAddMultipleOfSameEntryByName() {
        int testBossName = 0;
        int testTime = 3000;
        int testValue = 3000;

        int count = 4;

        for (int i = 0; i < count; i++) {
            testLog.addNewEntry(testBossName, testTime, testValue);
            assertEquals(i+1, testLog.getTotalKills());
            assertEquals(testValue, testLog.getBosses()[testBossName].getAvgValue());
            assertEquals(testTime, testLog.getBosses()[testBossName].getAvgTime());
        }
    }

    @Test
    void testAddMultipleOfSameBossDifferentValuesByName() {
        String testBossName = "Vorkath";
        int testBossIdx = 0;
        int testTime = 3000;
        int testValue = 3000;

        testLog.addNewEntryByName(testBossName, testTime, testValue);
        assertEquals(testValue, testLog.getBosses()[testBossIdx].getAvgValue());
        assertEquals(testTime, testLog.getBosses()[testBossIdx].getAvgTime());

        // ensure averages update properly
        testLog.addNewEntryByName(testBossName, testTime*3, testValue*3);
        assertEquals(2*testValue, testLog.getBosses()[testBossIdx].getAvgValue());
        assertEquals(2*testTime, testLog.getBosses()[testBossIdx].getAvgTime());
    }

    @Test
    void testRemoveEntry() {
        testAddMultipleOfSameEntryByIdx();

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

    @Test
    void testTwoSameNameBossesHaveSameHashCode() {
        Boss bossOne = new Boss("test");
        Boss bossTwo = new Boss("test");
        assertEquals(bossTwo.hashCode(), bossOne.hashCode());
    }

    @Test
    void testTwoDifferentBossesHaveSameHashCode() {
        Boss bossOne = new Boss("test");
        Boss bossTwo = new Boss("best");
        assertNotEquals(bossTwo.hashCode(), bossOne.hashCode());
    }

    @Test
    void testNewBossCanBeAdded() {
        int originalLength = testLog.getBosses().length;
        testLog.addNewBoss("Test");  // a default boss
        assertEquals(originalLength + 1, testLog.getBosses().length);
        assertEquals("Test", testLog.getBosses()[originalLength].getName());
    }

    @Test
    void testDuplicateBossesNotAdded() {
        int originalLength = testLog.getBosses().length;
        testLog.addNewBoss("Vorkath");  // a default boss
        assertEquals(originalLength, testLog.getBosses().length);
    }

//    @Test
//    void testRemoveEntryThatDoesntExist() {
//        assertThrows(, testLog.removeEntry(1283));
//    }
//
}