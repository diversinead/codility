package main.java.brownbelt;

import java.util.HashMap;
import java.util.Map;

public class EqualSegments {

    public static void main(String[] args) {
        int passed = 0, total = 0;

        // === Examples from the problem ===
        total++; passed += check("Example 1",
                solution(new int[]{10, 1, 3, 1, 2, 2, 1, 0, 4}), 3);
        total++; passed += check("Example 2 (all overlapping)",
                solution(new int[]{5, 3, 1, 3, 2, 3}), 1);
        total++; passed += check("Example 3 (all 9s)",
                solution(new int[]{9, 9, 9, 9, 9}), 2);
        total++; passed += check("Example 4",
                solution(new int[]{1, 5, 2, 4, 3, 3}), 3);

        // === Edge cases ===
        total++; passed += check("Two elements",
                solution(new int[]{4, 6}), 1);
        total++; passed += check("All same, even length",
                solution(new int[]{7, 7, 7, 7}), 2);
        total++; passed += check("All same, odd length",
                solution(new int[]{7, 7, 7, 7, 7}), 2);
        total++; passed += check("All different",
                solution(new int[]{1, 2, 3, 4, 5}), 1);
        total++; passed += check("Two best sums tied at 2",
                solution(new int[]{1, 2, 1, 2, 5, 0, 5, 0}), 2);
        total++; passed += check("Zeros",
                solution(new int[]{0, 0, 0, 0}), 2);
        total++; passed += check("Large values (no overflow)",
                solution(new int[]{1_000_000_000, 1_000_000_000, 0, 1_000_000_000, 1_000_000_000, 1_000_000_000}), 2);

        // === Stress test ===
        int N = 100_000;
        int[] big = new int[N];
        for (int i = 0; i < N; i++) big[i] = 5; // every pair sums to 10
        long start = System.currentTimeMillis();
        int res = solution(big);
        long elapsed = System.currentTimeMillis() - start;
        System.out.printf("[PERF] N=100k all-equal -> result=%d, time=%dms%n", res, elapsed);
        total++; passed += (res == N / 2 ? 1 : 0);

        System.out.println("\nPassed: " + passed + " / " + total);
    }

    private static int check(String name, int actual, int expected) {
        boolean ok = actual == expected;
        System.out.printf("[%s] %-40s got=%d expected=%d%n",
                ok ? "PASS" : "FAIL", name, actual, expected);
        return ok ? 1 : 0;
    }

    public static int solution(int[] A) {
        int N = A.length;
        if (N < 2) return 0;

        Map<Long, Integer> lastIdx = new HashMap<>();
        Map<Long, Integer> count = new HashMap<>();

        int best = 0;
        for (int i = 0; i + 1 < N; i++) {
            long sum = (long) A[i] + A[i + 1];
            Integer last = lastIdx.get(sum);
            if (last == null || i - last >= 2) {
                int c = count.getOrDefault(sum, 0) + 1;
                count.put(sum, c);
                lastIdx.put(sum, i);
                if (c > best) best = c;
            }
        }
        return best;
    }
}
