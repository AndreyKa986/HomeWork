package lesson13.task3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestATM {
    public AutomatedTellerMachine math;

    @Before
    public void init() {
        math = new AutomatedTellerMachine(100, 100, 100);
    }

    @After
    public void tearDown() {
        math = null;
    }

    @Test
    public void isPossible() {
        assertTrue(math.isPossible(110));
        assertFalse(math.isPossible(30));
        assertFalse(math.isPossible(-10));
    }

    @Test
    public void returnCash() {
        assertEquals(3, math.operation(110).twenty);
        assertEquals(1, math.operation(110).fifty);
        assertEquals(0, math.operation(110).hundred);
    }

    @Test
    public void moneyInATM() {
        assertEquals(100, math.numberOfFifty);
        assertNotEquals(50, math.numberOfTwenty);
    }

    @Test
    public void isPossibleToIssue() {
        assertTrue(math.isPossibleToIssue(new Cash(100, 100, 100)));
        assertFalse(math.isPossibleToIssue(new Cash(10000, 0, 0)));
    }

    @Test(expected = NullPointerException.class)
    public void NullPointerException() {
        math.isPossibleToIssue(null);
    }

    @Test(expected = NullPointerException.class)
    public void NullPointerException2() {
        math.withdrawal(null);
    }

    @Test(expected = NullPointerException.class)
    public void NullPointerException3() {
        math.adding(null);
    }
}
