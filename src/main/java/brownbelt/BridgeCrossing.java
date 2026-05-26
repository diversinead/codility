package main.java.brownbelt;

import java.util.Arrays;

public class BridgeCrossing {

    public static void main(String[] args) {
        int passed = 0, total = 0;

        // === Examples from the problem ===
        total++; passed += check("Example 1",
                solution(9, new int[]{5, 3, 8, 1, 8, 7, 7, 6}), 4);
        total++; passed += check("Example 2",
                solution(7, new int[]{7, 6, 5, 2, 7, 4, 5, 4}), 5);
        total++; passed += check("Example 3",
                solution(7, new int[]{3, 4, 3, 1}), 0);
        total++; passed += check("Example 4",
                solution(2, new int[]{3, 7, 5, 5, 6, 3, 9, 10, 8, 4}), 10);

        // === Edge cases ===
        total++; passed += check("Single car within limit",
                solution(5, new int[]{5}), 0);
        total++; passed += check("Single car over limit",
                solution(5, new int[]{6}), 1);
        total++; passed += check("All zero-cost (U max)",
                solution(1_000_000_000, new int[]{1, 1, 1, 1}), 0);
        total++; passed += check("Pair exactly equals U",
                solution(10, new int[]{4, 6, 5, 5}), 1);
        total++; passed += check("All same, sum > U",
                solution(10, new int[]{6, 6, 6, 6}), 3);

        // === Stress test ===
        int N = 100_000;
        int[] big = new int[N];
        for (int i = 0; i < N; i++) big[i] = (i % 7) + 1; // weights 1..7
        long start = System.currentTimeMillis();
        int res = solution(8, big);
        long elapsed = System.currentTimeMillis() - start;
        System.out.printf("[PERF] N=100k, U=8 -> turnbacks=%d, time=%dms%n", res, elapsed);

        System.out.println("\nPassed: " + passed + " / " + total);
    }

    private static int check(String name, int actual, int expected) {
        boolean ok = actual == expected;
        System.out.printf("[%s] %-30s got=%d expected=%d%n",
                ok ? "PASS" : "FAIL", name, actual, expected);
        return ok ? 1 : 0;
    }

    public static int solution(int U, int[] weight) {
        int N = weight.length;
        if (N == 0) return 0;

        // Compress weights into ranks 1..K.
        int[] sorted = weight.clone();
        Arrays.sort(sorted);
        int K = 0;
        int[] uniq = new int[N];
        for (int v : sorted) {
            if (K == 0 || uniq[K - 1] != v) uniq[K++] = v;
        }

        // Fenwick tree storing prefix max of dp keyed on compressed weight.
        int[] bit = new int[K + 1];

        int best = 0;
        for (int i = 0; i < N; i++) {
            int w = weight[i];
            if (w > U) continue;                       // car alone overloads the bridge

            int threshold = U - w;
            int hi = upperBoundIdx(uniq, K, threshold); // largest j (1-based) with uniq[j-1] <= threshold
            int prev = (hi == 0) ? 0 : queryMax(bit, hi);
            int dp = prev + 1;

            int idx = lowerBoundIdx(uniq, K, w) + 1;   // 1-based rank of w in uniq
            updateMax(bit, K, idx, dp);

            if (dp > best) best = dp;
        }

        return N - best;
    }

    // Returns count of uniq[0..K-1] entries <= target (i.e., largest 1-based index whose value <= target).
    private static int upperBoundIdx(int[] uniq, int K, int target) {
        int lo = 0, hi = K;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (uniq[mid] <= target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    // Returns 0-based index of first element >= target (uniq must contain target for our use).
    private static int lowerBoundIdx(int[] uniq, int K, int target) {
        int lo = 0, hi = K;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (uniq[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    private static int queryMax(int[] bit, int i) {
        int r = 0;
        while (i > 0) {
            if (bit[i] > r) r = bit[i];
            i -= i & -i;
        }
        return r;
    }

    private static void updateMax(int[] bit, int K, int i, int v) {
        while (i <= K) {
            if (bit[i] < v) bit[i] = v;
            i += i & -i;
        }
    }
}
