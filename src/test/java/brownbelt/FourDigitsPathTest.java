package test.java.brownbelt;

import main.java.brownbelt.FourDigitsPath;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FourDigitsPathTest {

    private final FourDigitsPath fourDigitsPath = new FourDigitsPath();

    // === Provided examples ===
    @Test
    public void example1_3x5() {
        int[][] board = {
                {9, 1, 1, 0, 7},
                {1, 0, 2, 1, 0},
                {1, 9, 1, 1, 0}
        };
        assertEquals(9121, fourDigitsPath.solution(board));
    }

    @Test
    public void example2_3x3() {
        int[][] board = {
                {1, 1, 1},
                {1, 3, 4},
                {1, 4, 3}
        };
        assertEquals(4343, fourDigitsPath.solution(board));
    }

    @Test
    public void example3_1x5() {
        int[][] board = {{0, 1, 5, 0, 0}};
        assertEquals(1500, fourDigitsPath.solution(board));
    }

    // === Single-row boards ===
    @Test
    public void oneByFourStraight() {
        int[][] board = {{1, 2, 3, 4}};
        assertEquals(4321, fourDigitsPath.solution(board));
    }

    @Test
    public void oneByFourAllNines() {
        int[][] board = {{9, 9, 9, 9}};
        assertEquals(9999, fourDigitsPath.solution(board));
    }

    @Test
    public void oneByFiveStartsWithZero() {
        int[][] board = {{0, 9, 8, 7, 6}};
        assertEquals(9876, fourDigitsPath.solution(board));
    }

    // === 2x2 boards ===
    @Test
    public void twoByTwoWithNine() {
        int[][] board = {
                {9, 8},
                {7, 6}
        };
        assertEquals(9867, fourDigitsPath.solution(board));
    }

    @Test
    public void twoByTwoAllOnes() {
        int[][] board = {
                {1, 1},
                {1, 1}
        };
        assertEquals(1111, fourDigitsPath.solution(board));
    }

    // === Uniform board ===
    @Test
    public void threeByFiveAllNines() {
        int[][] board = {
                {9, 9, 9, 9, 9},
                {9, 9, 9, 9, 9},
                {9, 9, 9, 9, 9}
        };
        assertEquals(9999, fourDigitsPath.solution(board));
    }
}
