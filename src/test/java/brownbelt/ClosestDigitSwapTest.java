package test.java.brownbelt;

import main.java.brownbelt.ClosestDigitSwap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestDigitSwapTest {

    private final ClosestDigitSwap closestDigitSwap = new ClosestDigitSwap();

    // === Problem example ===
    @Test
    public void example() {
        assertEquals(2, closestDigitSwap.solution("29162", "10524"));
    }

    // === Edge cases ===
    @Test
    public void equalStrings() {
        assertEquals(0, closestDigitSwap.solution("12345", "12345"));
    }

    @Test
    public void singleDigitSame() {
        assertEquals(0, closestDigitSwap.solution("5", "5"));
    }

    // Swapping a single digit just exchanges values, so |5-3| == |3-5|.
    @Test
    public void singleDigitDifferent() {
        assertEquals(0, closestDigitSwap.solution("5", "3"));
    }
    
    @Test
    public void allSwapsHelpful() {
        assertEquals(1, closestDigitSwap.solution("99", "11"));
    }

    @Test
    public void twoDigit() {
        assertEquals(0, closestDigitSwap.solution("10", "01"));
    }

    @Test
    public void alreadyMinimal() {
        assertEquals(0, closestDigitSwap.solution("50", "49"));
    }
}
