package main.java.brownbelt;

import java.util.Arrays;
import java.util.Random;

public class OptimisingFarthestDifferent {

    public static void main(String[] args) {
        int passed = 0, total = 0;

        // === Examples from the problem ===
        total++; passed += check("Example 1", solution(new int[]{4, 6, 2, 2, 6, 6, 4}), 5);

        int[] big = new int[75001];
        for (int i = 0; i <= 75000; i++) big[i] = i;
        total++; passed += check("Example 2 (A[K]=K, 75001 elements)", solution(big), 75000);

        // === Edge cases ===
        total++; passed += check("Single element", solution(new int[]{5}), 0);
        total++; passed += check("Two equal", solution(new int[]{3, 3}), 0);
        total++; passed += check("Two different", solution(new int[]{3, 7}), 1);
        total++; passed += check("All same", solution(new int[]{5, 5, 5, 5, 5}), 0);
        total++; passed += check("All different", solution(new int[]{1, 2, 3, 4, 5}), 4);

        // === Negative numbers ===
        total++; passed += check("With negatives", solution(new int[]{-1000000000, 0, 1000000000}), 2);
        total++; passed += check("Negative equals", solution(new int[]{-5, -5, -5}), 0);

        // === Endpoints same, middle different ===
        total++; passed += check("Endpoints same, mid diff",
                solution(new int[]{7, 1, 2, 3, 7}), 3);

        // === Endpoints different ===
        total++; passed += check("Endpoints different",
                solution(new int[]{1, 5, 5, 5, 2}), 4);

        // === Pattern where middle wins (it shouldn't, but verify) ===
        total++; passed += check("Same endpoints, big middle gap",
                solution(new int[]{1, 1, 2, 3, 1, 1}), 3);

        // === Large stress test ===
        int[] alternating = new int[100000];
        for (int i = 0; i < 100000; i++) alternating[i] = i % 2;
        total++; passed += check("Large alternating (100k)", solution(alternating), 99999);

        int[] allSameLarge = new int[100000];
        Arrays.fill(allSameLarge, 42);
        total++; passed += check("Large all same (100k)", solution(allSameLarge), 0);

        // Performance check
        long start = System.currentTimeMillis();
        int[] perf = new int[100000];
        for (int i = 0; i < 100000; i++) perf[i] = i;
        int res = solution(perf);
        long elapsed = System.currentTimeMillis() - start;
        System.out.printf("[PERF] 100k unique values -> result=%d, time=%dms%n", res, elapsed);

        // === Cross-check against brute force on random inputs ===
        Random rnd = new Random(42);
        int randomTests = 100;
        int randomPassed = 0;
        for (int t = 0; t < randomTests; t++) {
            int n = 1 + rnd.nextInt(50);
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(5) - 2; // small range to force duplicates
            int expected = bruteForce(arr);
            int actual = solution(arr);
            if (expected == actual) randomPassed++;
            else System.out.printf("[FAIL random] %s -> got %d, expected %d%n",
                    Arrays.toString(arr), actual, expected);
        }
        total += randomTests;
        passed += randomPassed;
        System.out.printf("[Random tests] %d / %d passed%n", randomPassed, randomTests);

        System.out.println("\n=== Results ===");
        System.out.println("Passed: " + passed + " / " + total);
        if (passed == total) System.out.println("All tests passed!");
    }

    // Reference O(N^2) brute force matching the original implementation
    private static int bruteForce(int[] A) {
        int N = A.length;
        int result = 0;
        for (int i = 0; i < N; i++)
            for (int j = i; j < N; j++)
                if (A[i] != A[j])
                    result = Math.max(result, j - i);
        return result;
    }

    private static int check(String name, int actual, int expected) {
        boolean ok = actual == expected;
        System.out.printf("[%s] %-40s got=%d expected=%d%n",
                ok ? "PASS" : "FAIL", name, actual, expected);
        return ok ? 1 : 0;
    }

    
    public static int solution(int[] A) {
        int N = A.length;
        int result = 0;

        // Farthest j with A[j] != A[0]
        for (int j = N - 1; j >= 0; j--) {
            if (A[j] != A[0]) {
                result = Math.max(result, j);   // distance = j - 0
                break;
            }
        }

        // Farthest i (from the left) with A[i] != A[N-1]
        for (int i = 0; i < N; i++) {
            if (A[i] != A[N - 1]) {
                result = Math.max(result, (N - 1) - i);
                break;
            }
        }

        return result;
    }
}
