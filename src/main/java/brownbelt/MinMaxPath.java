package main.java.brownbelt;

public class MinMaxPath {

    public static void main(String[] args) {
        int passed = 0, total = 0;

        // === Examples from the problem ===
        total++; passed += check("Example 1",
                solution(new int[]{3, 4, 6}, new int[]{6, 5, 4}), 5);
        total++; passed += check("Example 2",
                solution(new int[]{1, 2, 1, 1, 1, 4}, new int[]{1, 1, 1, 3, 1, 1}), 2);
        total++; passed += check("Example 3 (negatives)",
                solution(new int[]{-5, -1, -3}, new int[]{-5, 5, -2}), -1);

        // === Edge cases ===
        total++; passed += check("Single column",
                solution(new int[]{7}, new int[]{3}), 7);
        total++; passed += check("Single column descent",
                solution(new int[]{-2}, new int[]{8}), 8);
        total++; passed += check("Two columns straight",
                solution(new int[]{1, 9}, new int[]{9, 2}), 9);
        total++; passed += check("All equal",
                solution(new int[]{4, 4, 4, 4}, new int[]{4, 4, 4, 4}), 4);
        total++; passed += check("Top all small, bottom all big",
                solution(new int[]{1, 1, 1, 1}, new int[]{9, 9, 9, 9}), 9);
        total++; passed += check("Top big at end, bottom small",
                solution(new int[]{1, 1, 1, 99}, new int[]{1, 1, 1, 1}), 1);
        total++; passed += check("Top small, bottom big at start",
                solution(new int[]{1, 1, 1, 1}, new int[]{99, 1, 1, 1}), 1);
        total++; passed += check("All negatives",
                solution(new int[]{-7, -3, -5}, new int[]{-8, -6, -2}), -2);
        total++; passed += check("Min trapped by boundaries",
                solution(new int[]{5, 1, 1}, new int[]{1, 1, 5}), 5);

        // === Stress test ===
        int N = 100_000;
        int[] A = new int[N];
        int[] B = new int[N];
        for (int i = 0; i < N; i++) { A[i] = i; B[i] = N - i; }
        long start = System.currentTimeMillis();
        int res = solution(A, B);
        long elapsed = System.currentTimeMillis() - start;
        System.out.printf("[PERF] N=100k ascending/descending -> result=%d, time=%dms%n", res, elapsed);

        System.out.println("\nPassed: " + passed + " / " + total);
    }

    private static int check(String name, int actual, int expected) {
        boolean ok = actual == expected;
        System.out.printf("[%s] %-40s got=%d expected=%d%n",
                ok ? "PASS" : "FAIL", name, actual, expected);
        return ok ? 1 : 0;
    }

    public static int solution(int[] A, int[] B) {
        int N = A.length;

        int[] prefMaxA = new int[N];
        prefMaxA[0] = A[0];
        for (int i = 1; i < N; i++) {
            prefMaxA[i] = Math.max(prefMaxA[i - 1], A[i]);
        }

        int[] sufMaxB = new int[N];
        sufMaxB[N - 1] = B[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            sufMaxB[i] = Math.max(sufMaxB[i + 1], B[i]);
        }

        int best = Integer.MAX_VALUE;
        for (int k = 0; k < N; k++) {
            int pathMax = Math.max(prefMaxA[k], sufMaxB[k]);
            if (pathMax < best) best = pathMax;
        }
        return best;
    }
}
