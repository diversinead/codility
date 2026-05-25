package main.java.brown_belt;

public class ballsInBucket {

    public static void main(String[] args) {
        int passed = 0;
        int total = 0;

        // === Examples from the problem ===
        total++; passed += check("Example 1", solution("..B....B.BB"), 2);
        total++; passed += check("Example 2", solution("BB.B.BBB..."), 4);
        total++; passed += check("Example 3", solution(".BBB.B"), -1);
        total++; passed += check("Example 4", solution(".......B.B"), 0);

        // === Edge cases ===
        total++; passed += check("Single ball", solution("B"), 0);
        total++; passed += check("Single ball with dots", solution("...B..."), 0);
        total++; passed += check("Two adjacent balls n=2", solution("BB"), -1);
        total++; passed += check("Two balls correct", solution("B.B"), 0);
        total++; passed += check("Two balls .BB", solution(".BB"), 1);
        total++; passed += check("Two balls BB.", solution("BB."), 1);

        // === Already correct ===
        total++; passed += check("Already correct B.B.B", solution("B.B.B"), 0);
        total++; passed += check("Already correct .B.B.B", solution(".B.B.B"), 0);
        total++; passed += check("Long alternating", solution("B.B.B.B.B.B"), 0);

        // === Impossible ===
        total++; passed += check("Too many balls BBBB", solution("BBBB"), -1);
        total++; passed += check("All B (5)", solution("BBBBB"), -1);

        // === Parity choice ===
        total++; passed += check("Cluster left BBBB......", solution("BBBB......"), 2);
        total++; passed += check("Cluster right ......BBBB", solution("......BBBB"), 2);
        total++; passed += check("Mixed .BB.B", solution(".BB.B"), 1);
        total++; passed += check("Mixed B.BB.", solution("B.BB."), 1);
        total++; passed += check("3 balls BBB..", solution("BBB.."), 1);

        // === Larger tests ===
        total++; passed += check("Long all correct", solution("B.B.B.B.B.B.B.B.B.B"), 0);

        // === Stress test: large input ===
        StringBuilder large = new StringBuilder();
        for (int i = 0; i < 50000; i++) large.append("B.");
        total++; passed += check("Large alternating (100k)", solution(large.toString()), 0);

        StringBuilder allBalls = new StringBuilder();
        for (int i = 0; i < 100000; i++) allBalls.append('B');
        total++; passed += check("Large all B (100k)", solution(allBalls.toString()), -1);

        // === Performance check ===
        long start = System.currentTimeMillis();
        StringBuilder mixed = new StringBuilder();
        for (int i = 0; i < 100000; i++) mixed.append(i % 3 == 0 ? 'B' : '.');
        int res = solution(mixed.toString());
        long elapsed = System.currentTimeMillis() - start;
        System.out.printf("[PERF] Large mixed (100k) -> result=%d, time=%dms%n", res, elapsed);

        System.out.println("\n=== Results ===");
        System.out.println("Passed: " + passed + " / " + total);
        if (passed == total) System.out.println("All tests passed!");
    }
    
    private static int check(String name, int actual, int expected) {
        boolean ok = actual == expected;
        System.out.printf("[%s] %-35s got=%d expected=%d%n",
                ok ? "PASS" : "FAIL", name, actual, expected);
        return ok ? 1 : 0;
    }

    private static int solution(String buckets) {
        int n = buckets.length();
        int K = 0;
        for (int i = 0; i < n; i++) if (buckets.charAt(i) == 'B') K++;
        if (K == 0) return 0;

        // Required physical span = 2*(K-1) + 1
        int span = 2 * (K - 1) + 1;
        if (span > n) return -1;

        // Prefix sums of balls at even indices and odd indices
        // evenPrefix[i] = number of 'B' at even indices in [0, i)
        int[] evenPrefix = new int[n + 1];
        int[] oddPrefix  = new int[n + 1];
        for (int i = 0; i < n; i++) {
            evenPrefix[i + 1] = evenPrefix[i] + ((buckets.charAt(i) == 'B' && i % 2 == 0) ? 1 : 0);
            oddPrefix[i + 1]  = oddPrefix[i]  + ((buckets.charAt(i) == 'B' && i % 2 == 1) ? 1 : 0);
        }

        int bestInside = 0;

        // Try every starting position s, window covers indices [s, s + span - 1]
        for (int s = 0; s + span - 1 < n; s++) {
            int end = s + span - 1; // inclusive
            if (s % 2 == 0) {
                // Even-parity window: slots s, s+2, ..., end (end has same parity as s)
                int balls = evenPrefix[end + 1] - evenPrefix[s];
                bestInside = Math.max(bestInside, balls);
            } else {
                // Odd-parity window
                int balls = oddPrefix[end + 1] - oddPrefix[s];
                bestInside = Math.max(bestInside, balls);
            }
        }

        return K - bestInside;
    }
}
