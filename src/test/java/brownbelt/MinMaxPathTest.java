package test.java.brownbelt;

import main.java.brownbelt.MinMaxPath;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinMaxPathTest {

    private final MinMaxPath minMaxPath = new MinMaxPath();

    // === Provided examples ===
    @Test
    public void example1() {
        assertEquals(5, minMaxPath.solution(new int[]{3, 4, 6}, new int[]{6, 5, 4}));
    }

    @Test
    public void example2() {
        assertEquals(2, minMaxPath.solution(new int[]{1, 2, 1, 1, 1, 4}, new int[]{1, 1, 1, 3, 1, 1}));
    }

    @Test
    public void example3_negatives() {
        assertEquals(-1, minMaxPath.solution(new int[]{-5, -1, -3}, new int[]{-5, 5, -2}));
    }

    // === Edge cases ===
    @Test
    public void singleColumn() {
        assertEquals(7, minMaxPath.solution(new int[]{7}, new int[]{3}));
    }

    @Test
    public void singleColumnDescent() {
        assertEquals(8, minMaxPath.solution(new int[]{-2}, new int[]{8}));
    }

    @Test
    public void twoColumnsStraight() {
        assertEquals(9, minMaxPath.solution(new int[]{1, 9}, new int[]{9, 2}));
    }

    @Test
    public void allEqual() {
        assertEquals(4, minMaxPath.solution(new int[]{4, 4, 4, 4}, new int[]{4, 4, 4, 4}));
    }

    @Test
    public void topAllSmallBottomAllBig() {
        assertEquals(9, minMaxPath.solution(new int[]{1, 1, 1, 1}, new int[]{9, 9, 9, 9}));
    }

    @Test
    public void topBigAtEndBottomSmall() {
        assertEquals(1, minMaxPath.solution(new int[]{1, 1, 1, 99}, new int[]{1, 1, 1, 1}));
    }

    @Test
    public void topSmallBottomBigAtStart() {
        assertEquals(1, minMaxPath.solution(new int[]{1, 1, 1, 1}, new int[]{99, 1, 1, 1}));
    }

    @Test
    public void allNegatives() {
        assertEquals(-2, minMaxPath.solution(new int[]{-7, -3, -5}, new int[]{-8, -6, -2}));
    }

    @Test
    public void minTrappedByBoundaries() {
        assertEquals(5, minMaxPath.solution(new int[]{5, 1, 1}, new int[]{1, 1, 5}));
    }

    // === Stress test ===
    @Test
    public void large100kAscendingDescending() {
        int N = 100_000;
        int[] A = new int[N];
        int[] B = new int[N];
        for (int i = 0; i < N; i++) { A[i] = i; B[i] = N - i; }
        assertEquals(50000, minMaxPath.solution(A, B));
    }
}
