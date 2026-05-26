package main.java.brownbelt;

public class GreenNeighbourhood {

    public static void main(String[] args) {
        int passed = 0, total = 0;

        // === Examples from the problem ===
        total++; passed += check("Example 1",
                solution(new int[]{1, -3, 2}), 2);
        total++; passed += check("Example 2",
                solution(new int[]{-3, 2, 4, -5, 3}), 3);
        total++; passed += check("Example 3",
                solution(new int[]{-2, 1, -3, 1}), 4);

        // === Edge cases ===
        total++; passed += check("Single positive",
                solution(new int[]{5}), 0);
        total++; passed += check("Single zero",
                solution(new int[]{0}), 0);
        total++; passed += check("Single negative",
                solution(new int[]{-7}), 7);
        total++; passed += check("All positive",
                solution(new int[]{3, 4, 5, 6}), 0);
        total++; passed += check("All zero",
                solution(new int[]{0, 0, 0, 0}), 0);
        total++; passed += check("Two negatives",
                solution(new int[]{-5, -5}), 10);
        total++; passed += check("Pollution at both ends",
                solution(new int[]{-5, 0, 0, 0, -5}), 10);
        total++; passed += check("Alternating pollution/forest",
                solution(new int[]{-10, 10, -10, 10}), 10);
        total++; passed += check("Large pollution single field",
                solution(new int[]{-10000}), 10000);

        // === Stress test ===
        int N = 100_000;
        int[] big = new int[N];
        for (int i = 0; i < N; i++) big[i] = -10_000; // worst-case pollution everywhere
        long start = System.currentTimeMillis();
        int res = solution(big);
        long elapsed = System.currentTimeMillis() - start;
        System.out.printf("[PERF] N=100k all -10000 -> result=%d, time=%dms%n", res, elapsed);

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
        int[] x = new int[N];
        long total = 0;

        for (int K = 0; K < N; K++) {
            long sumA = A[K];
            if (K - 1 >= 0) sumA += A[K - 1];
            if (K + 1 < N) sumA += A[K + 1];

            long sumX = x[K];
            if (K - 1 >= 0) sumX += x[K - 1];
            if (K + 1 < N) sumX += x[K + 1];

            long need = -sumA;
            if (sumX < need) {
                long add = need - sumX;
                int target = (K + 1 < N) ? K + 1 : K;
                x[target] += (int) add;
                total += add;
            }
        }
        return (int) total;
    }
}
