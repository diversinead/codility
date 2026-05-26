package test.java.brownbelt;

import main.java.brownbelt.NotEqualDigits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotEqualDigitsTest {

    private final NotEqualDigits notEqualDigits = new NotEqualDigits();

    // === Provided examples ===
    @Test
    public void example1_n55() {
        assertEquals(56, notEqualDigits.solution(55));
    }

    @Test
    public void example2_n1765() {
        assertEquals(1767, notEqualDigits.solution(1765));
    }

    @Test
    public void example3_n98() {
        assertEquals(101, notEqualDigits.solution(98));
    }

    @Test
    public void example4_n44432() {
        assertEquals(45010, notEqualDigits.solution(44432));
    }

    @Test
    public void example5_n3298() {
        assertEquals(3401, notEqualDigits.solution(3298));
    }

    // === Edge cases ===
    @Test
    public void n1() {
        assertEquals(2, notEqualDigits.solution(1));
    }

    @Test
    public void n9() {
        assertEquals(10, notEqualDigits.solution(9));
    }

    @Test
    public void n10() {
        assertEquals(12, notEqualDigits.solution(10));
    }

    @Test
    public void n99() {
        assertEquals(101, notEqualDigits.solution(99));
    }

    @Test
    public void n100() {
        assertEquals(101, notEqualDigits.solution(100));
    }

    @Test
    public void alreadyValid_n12() {
        assertEquals(13, notEqualDigits.solution(12));
    }

    @Test
    public void wrapsTrailingDup_n199() {
        assertEquals(201, notEqualDigits.solution(199));
    }

    @Test
    public void bigCarry_n8999() {
        assertEquals(9010, notEqualDigits.solution(8999));
    }

    @Test
    public void nearLimit_n999999999() {
        assertEquals(1_010_101_010, notEqualDigits.solution(999_999_999));
    }

    @Test
    public void limit_n1000000000() {
        assertEquals(1_010_101_010, notEqualDigits.solution(1_000_000_000));
    }
}
