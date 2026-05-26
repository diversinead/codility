package main.java.brownbelt;

import java.math.BigInteger;
import java.util.Random;

public class ClosestDigitSwap {
    
    public static void main(String[] args) {
        int passed = 0, total = 0;

        // Problem example
        total++; passed += check("Example", solution("29162", "10524"), 2);

        // Edge cases
        total++; passed += check("Equal strings", solution("12345", "12345"), 0);
        total++; passed += check("Single digit same", solution("5", "5"), 0);
        total++; passed += check("Single digit diff", solution("5", "3"), 0);
        // For single digit, swapping just exchanges values, |5-3|=|3-5|=2, no swap needed

        total++; passed += check("All swap helpful", solution("99", "11"), 1);

        total++; passed += check("Two-digit", solution("10", "01"), 0);

        total++; passed += check("Already minimal", solution("50", "49"), 0);

        // Compare against brute force on random small cases
        Random rnd = new Random(7);
        int randomTests = 200;
        int randomPassed = 0;
        for (int t = 0; t < randomTests; t++) {
            int n = 1 + rnd.nextInt(6);
            String s = randomDigitString(n, rnd);
            String tt = randomDigitString(n, rnd);
            int expected = bruteForce(s, tt);
            int actual = solution(s, tt);
            if (expected == actual) randomPassed++;
            else System.out.printf("[FAIL] S=%s T=%s -> got %d, expected %d%n", s, tt, actual, expected);
        }
        total += randomTests;
        passed += randomPassed;
        System.out.printf("[Random] %d / %d%n", randomPassed, randomTests);

        // Performance: 100,000 digit strings
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb1.append('5'); sb2.append('5');
        for (int i = 1; i < 100000; i++) {
            sb1.append((char) ('0' + rnd.nextInt(10)));
            sb2.append((char) ('0' + rnd.nextInt(10)));
        }
        long start = System.currentTimeMillis();
        int res = solution(sb1.toString(), sb2.toString());
        long elapsed = System.currentTimeMillis() - start;
        System.out.printf("[PERF] N=100000 -> swaps=%d, time=%dms%n", res, elapsed);

        System.out.println("\nPassed " + passed + " / " + total);
    }

    private static String randomDigitString(int n, Random rnd) {
        StringBuilder sb = new StringBuilder();
        sb.append((char) ('1' + rnd.nextInt(9))); // no leading zero
        for (int i = 1; i < n; i++) sb.append((char) ('0' + rnd.nextInt(10)));
        return sb.toString();
    }

    // Brute force: try all 2^k subsets of differing positions, find min |D| then min swaps
    private static int bruteForce(String S, String T) {
        int n = S.length();
        BigInteger best = null;
        int bestSwaps = Integer.MAX_VALUE;
        for (int mask = 0; mask < (1 << n); mask++) {
            char[] sa = S.toCharArray();
            char[] ta = T.toCharArray();
            int swaps = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    char tmp = sa[i]; sa[i] = ta[i]; ta[i] = tmp;
                    if (S.charAt(i) != T.charAt(i)) swaps++;
                        // Note: swapping equal digits is wasteful but allowed; we only count "real" swaps
                        // Actually, the problem counts all swaps. But swapping equal digits is never useful, so skip.
                    else { mask = mask; } // keep as-is logic
                }
            }
            // Recount swaps properly: count bits where positions actually changed
            int realSwaps = 0;
            for (int i = 0; i < n; i++) if ((mask & (1<<i)) != 0) realSwaps++;
            BigInteger sv = new BigInteger(new String(sa));
            BigInteger tv = new BigInteger(new String(ta));
            BigInteger diff = sv.subtract(tv).abs();
            if (best == null || diff.compareTo(best) < 0 || (diff.compareTo(best) == 0 && realSwaps < bestSwaps)) {
                best = diff;
                bestSwaps = realSwaps;
            }
        }
        return bestSwaps;
    }

    private static int check(String name, int actual, int expected) {
        boolean ok = actual == expected;
        System.out.printf("[%s] %-30s got=%d expected=%d%n",
                ok ? "PASS" : "FAIL", name, actual, expected);
        return ok ? 1 : 0;
    }
    private static int solution(String S, String T) {
        int N = S.length();
        long carry = 0;
        int swaps = 0;

        final long THRESHOLD = (long) 1e17;
        boolean locked = false;
        int lockedSign = 0;

        for (int i = 0; i < N; i++) {
            int a = S.charAt(i) - '0';
            int b = T.charAt(i) - '0';
            int d = a - b;

            if (locked) {
                if (d != 0 && Integer.signum(d) == lockedSign) {
                    swaps++;
                }
            } else {
                long noSwap = carry * 10 + d;
                long swap   = carry * 10 - d;
                if (Math.abs(swap) < Math.abs(noSwap)) {
                    carry = swap;
                    swaps++;
                } else {
                    carry = noSwap;  // tie or no-swap better -> prefer no swap
                }
                if (Math.abs(carry) > THRESHOLD) {
                    locked = true;
                    lockedSign = carry > 0 ? 1 : -1;
                }
            }
        }

        return swaps;
    }
}
