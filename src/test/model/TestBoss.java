package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestBoss {

    @Test
    void testTwoSameNameBossesHaveSameHashCodeAndAreEqual() {
        Boss bossOne = new Boss("test");
        Boss bossTwo = new Boss("test");
        assertEquals(bossTwo, bossOne);
        assertEquals(bossTwo.hashCode(), bossOne.hashCode());
    }

    @Test
    void testTwoSameNameBossesHaveSameHashCode() {
        Boss bossOne = new Boss("test");
        Boss bossTwo = new Boss("test");
        assertEquals(bossTwo.hashCode(), bossOne.hashCode());
    }

    @Test
    void testTwoDifferentBossesHaveSameHashCodeAndNotEqual() {
        Boss bossOne = new Boss("test");
        Boss bossTwo = new Boss("best");
        assertNotEquals(bossTwo, bossOne);
        assertNotEquals(bossTwo.hashCode(), bossOne.hashCode());
    }

    @Test
    void testBossEqualityWorksProperly() {
        Boss bossOne = new Boss("test");
        assertNotEquals(null, bossOne);
        assertNotEquals("test", bossOne);
    }

}
