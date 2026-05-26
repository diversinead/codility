package test.java.brownbelt;

import main.java.brownbelt.EraseAdjacentLetters;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EraseAdjacentLettersTest {

    private final EraseAdjacentLetters eraseAdjacentLetters = new EraseAdjacentLetters();

    // === Provided examples ===
    @Test
    public void example1_CBACD() {
        assertEquals("C", eraseAdjacentLetters.solution("CBACD"));
    }

    @Test
    public void example2_CABABD() {
        assertEquals("", eraseAdjacentLetters.solution("CABABD"));
    }

    @Test
    public void example3_ACBDACBD() {
        assertEquals("ACBDACBD", eraseAdjacentLetters.solution("ACBDACBD"));
    }

    // === Edge cases ===
    @Test
    public void emptyString() {
        assertEquals("", eraseAdjacentLetters.solution(""));
    }

    @Test
    public void singleA() {
        assertEquals("A", eraseAdjacentLetters.solution("A"));
    }

    @Test
    public void singleB() {
        assertEquals("B", eraseAdjacentLetters.solution("B"));
    }

    @Test
    public void ab() {
        assertEquals("", eraseAdjacentLetters.solution("AB"));
    }

    @Test
    public void ba() {
        assertEquals("", eraseAdjacentLetters.solution("BA"));
    }

    @Test
    public void cd() {
        assertEquals("", eraseAdjacentLetters.solution("CD"));
    }

    @Test
    public void dc() {
        assertEquals("", eraseAdjacentLetters.solution("DC"));
    }

    @Test
    public void acNoCancel() {
        assertEquals("AC", eraseAdjacentLetters.solution("AC"));
    }

    @Test
    public void adNoCancel() {
        assertEquals("AD", eraseAdjacentLetters.solution("AD"));
    }

    @Test
    public void bcNoCancel() {
        assertEquals("BC", eraseAdjacentLetters.solution("BC"));
    }

    @Test
    public void aaNoCancel() {
        assertEquals("AA", eraseAdjacentLetters.solution("AA"));
    }

    // === Nested cancellations ===
    @Test
    public void abba() {
        assertEquals("", eraseAdjacentLetters.solution("ABBA"));
    }

    @Test
    public void acdb() {
        assertEquals("", eraseAdjacentLetters.solution("ACDB"));
    }

    @Test
    public void acdcdb() {
        assertEquals("", eraseAdjacentLetters.solution("ACDCDB"));
    }

    @Test
    public void cabdWrapsAb() {
        assertEquals("", eraseAdjacentLetters.solution("CABD"));
    }

    // === Long alternating ===
    @Test
    public void thousandAbsAllCancel() {
        StringBuilder allCancel = new StringBuilder();
        for (int i = 0; i < 1000; i++) allCancel.append("AB");
        assertEquals("", eraseAdjacentLetters.solution(allCancel.toString()));
    }

    @Test
    public void thousandAcsNoCancel() {
        StringBuilder noCancel = new StringBuilder();
        for (int i = 0; i < 1000; i++) noCancel.append("AC");
        assertEquals(noCancel.toString(), eraseAdjacentLetters.solution(noCancel.toString()));
    }

    // === Partial cancellation ===
    @Test
    public void aabb() {
        assertEquals("", eraseAdjacentLetters.solution("AABB"));
    }

    @Test
    public void ccdd() {
        assertEquals("", eraseAdjacentLetters.solution("CCDD"));
    }

    @Test
    public void aabbc() {
        assertEquals("C", eraseAdjacentLetters.solution("AABBC"));
    }

    @Test
    public void cababababd() {
        assertEquals("", eraseAdjacentLetters.solution("CABABABABD"));
    }

    // === Mixed with no-cancel residue ===
    @Test
    public void acbd() {
        assertEquals("ACBD", eraseAdjacentLetters.solution("ACBD"));
    }

    @Test
    public void abcdba() {
        assertEquals("", eraseAdjacentLetters.solution("ABCDBA"));
    }

    // === Stress test ===
    @Test
    public void worstCase250kAllCancel() {
        StringBuilder worst = new StringBuilder();
        for (int i = 0; i < 125_000; i++) worst.append("AB");
        assertEquals("", eraseAdjacentLetters.solution(worst.toString()));
    }
}
