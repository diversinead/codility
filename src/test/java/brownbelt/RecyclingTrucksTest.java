package test.java.brownbelt;

import main.java.brownbelt.RecyclingTrucks;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecyclingTrucksTest {

    private final RecyclingTrucks recyclingTrucks = new RecyclingTrucks();

    // === Provided examples ===
    @Test
    public void example1() {
        assertEquals(15, recyclingTrucks.solution(
                new int[]{2, 5},
                new String[]{"PGP", "M"}));
    }

    @Test
    public void example2() {
        assertEquals(19, recyclingTrucks.solution(
                new int[]{3, 2, 4},
                new String[]{"MPM", "", "G"}));
    }

    @Test
    public void example3() {
        assertEquals(12, recyclingTrucks.solution(
                new int[]{2, 1, 1, 1, 2},
                new String[]{"", "PP", "PP", "GM", ""}));
    }

    // === Edge cases ===
    @Test
    public void emptyTrash() {
        assertEquals(0, recyclingTrucks.solution(
                new int[]{5, 5, 5},
                new String[]{"", "", ""}));
    }

    @Test
    public void singleHouseSingleP() {
        assertEquals(7, recyclingTrucks.solution(
                new int[]{3},
                new String[]{"P"}));
    }

    @Test
    public void singleHouseAllThree() {
        assertEquals(9, recyclingTrucks.solution(
                new int[]{4},
                new String[]{"PGM"}));
    }

    @Test
    public void onlyPlastic() {
        assertEquals(18, recyclingTrucks.solution(
                new int[]{1, 2, 3},
                new String[]{"P", "PP", "PPP"}));
    }

    @Test
    public void onlyLastHouse() {
        assertEquals(11, recyclingTrucks.solution(
                new int[]{1, 1, 1, 1, 1},
                new String[]{"", "", "", "", "PGM"}));
    }

    @Test
    public void onlyFirstHouse() {
        assertEquals(3, recyclingTrucks.solution(
                new int[]{1, 100, 100, 100},
                new String[]{"P", "", "", ""}));
    }

    @Test
    public void tiedTrucks() {
        assertEquals(11, recyclingTrucks.solution(
                new int[]{2, 3},
                new String[]{"P", "G"}));
    }

    // === Stress tests ===
    @Test
    public void large100kFarP() {
        int N = 100_000;
        int[] bigD = new int[N];
        Arrays.fill(bigD, 1);
        String[] bigT = new String[N];
        for (int i = 0; i < N; i++) bigT[i] = (i == N - 1) ? "P" : "";
        assertEquals(2 * N + 1, recyclingTrucks.solution(bigD, bigT));
    }

    @Test
    public void large100kAllPGM() {
        int N = 100_000;
        int[] bigD = new int[N];
        Arrays.fill(bigD, 1);
        String[] bigT = new String[N];
        for (int i = 0; i < N; i++) bigT[i] = "PGM";
        assertEquals(3 * N, recyclingTrucks.solution(bigD, bigT));
    }
}
