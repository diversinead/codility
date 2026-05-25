package main.java.brown_belt;

public class recyclingTrucks {

    public static void main(String[] args) {
        
        int passed = 0, total = 0;

        // === Examples from the problem ===
        total++; passed += check("Example 1",
                solution(new int[]{2, 5}, new String[]{"PGP", "M"}), 15);

        total++; passed += check("Example 2",
                solution(new int[]{3, 2, 4}, new String[]{"MPM", "", "G"}), 19);

        total++; passed += check("Example 3",
                solution(new int[]{2, 1, 1, 1, 2}, new String[]{"", "PP", "PP", "GM", ""}), 12);

        // === Edge cases ===
        // No trash at all
        total++; passed += check("Empty trash",
                solution(new int[]{5, 5, 5}, new String[]{"", "", ""}), 0);

        // Single house, single bag
        total++; passed += check("Single house single P",
                solution(new int[]{3}, new String[]{"P"}), 7);  // 2*3 + 1

        // Single house, three types
        total++; passed += check("Single house all three",
                solution(new int[]{4}, new String[]{"PGM"}), 9);  // max(2*4+1, 2*4+1, 2*4+1)

        // Only one type collected
        total++; passed += check("Only plastic",
                solution(new int[]{1, 2, 3}, new String[]{"P", "PP", "PPP"}), 18);
        // farthest=2, prefix=6, travel=12, bags=6, total=18

        // Trash only at last house
        total++; passed += check("Only last house",
                solution(new int[]{1, 1, 1, 1, 1}, new String[]{"", "", "", "", "PGM"}), 11);
        // travel = 2*5 = 10, +1 bag = 11 per truck

        // Trash only at first house, very long street
        total++; passed += check("Only first house",
                solution(new int[]{1, 100, 100, 100}, new String[]{"P", "", "", ""}), 3);
        // 2*1 + 1 = 3

        // Two types tied
        total++; passed += check("Tied trucks",
                solution(new int[]{2, 3}, new String[]{"P", "G"}), 11);
        // P: 2*2+1=5; G: 2*5+1=11. Max=11

        // === Stress test ===
        int N = 100_000;
        int[] bigD = new int[N];
        String[] bigT = new String[N];
        java.util.Arrays.fill(bigD, 1);
        // Distribute total length 100,000 across houses; mark a 'P' at the very last
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (i == N - 1) bigT[i] = "P";
            else bigT[i] = "";
        }
        long start = System.currentTimeMillis();
        int res = solution(bigD, bigT);
        long elapsed = System.currentTimeMillis() - start;
        System.out.printf("[PERF] N=100k, far P -> result=%d, time=%dms%n", res, elapsed);
        // Expected: 2*N + 1 = 200,001
        total++; passed += (res == 2 * N + 1 ? 1 : 0);

        // Heavy bags spread out
        java.util.Arrays.fill(bigD, 1);
        for (int i = 0; i < N; i++) bigT[i] = "PGM";
        start = System.currentTimeMillis();
        res = solution(bigD, bigT);
        elapsed = System.currentTimeMillis() - start;
        System.out.printf("[PERF] N=100k, all PGM -> result=%d, time=%dms%n", res, elapsed);
        // travel = 2*N = 200,000, bags per truck = N = 100,000, total per truck = 300,000
        total++; passed += (res == 3 * N ? 1 : 0);

        System.out.println("\nPassed: " + passed + " / " + total);
    }

    private static int check(String name, int actual, int expected) {
        boolean ok = actual == expected;
        System.out.printf("[%s] %-30s got=%d expected=%d%n",
                ok ? "PASS" : "FAIL", name, actual, expected);
        return ok ? 1 : 0;
    }
    
    public static int solution(int[] D, String[] T) {
        int N = D.length;

        long farthestP = -1, farthestG = -1, farthestM = -1;
        long countP = 0, countG = 0, countM = 0;

        for (int k = 0; k < N; k++) {
            String t = T[k];
            int p = 0, g = 0, m = 0;
            for (int i = 0; i < t.length(); i++) {
                char c = t.charAt(i);
                if      (c == 'P') p++;
                else if (c == 'G') g++;
                else if (c == 'M') m++;
            }
            if (p > 0) { countP += p; farthestP = k; }
            if (g > 0) { countG += g; farthestG = k; }
            if (m > 0) { countM += m; farthestM = k; }
        }

        // prefix[k+1] = D[0] + ... + D[k]. Compute lazily for the three farthest indices.
        long timeP = truckTime(D, farthestP, countP);
        long timeG = truckTime(D, farthestG, countG);
        long timeM = truckTime(D, farthestM, countM);

        long ans = Math.max(timeP, Math.max(timeG, timeM));
        return (int) ans;
    }

    private static long truckTime(int[] D, long farthest, long bags) {
        if (farthest < 0) return 0;
        long travel = 0;
        for (int i = 0; i <= farthest; i++) travel += D[i];
        return 2 * travel + bags;
    }
}
