package main.java.brownbelt;

public class EraseAdjacentLetters {

    public static void main(String[] args) {
        int passed = 0, total = 0;

        // === Examples from the problem ===
        total++; passed += check("Example 1: CBACD",  solution("CBACD"),    "C");
        total++; passed += check("Example 2: CABABD", solution("CABABD"),   "");
        total++; passed += check("Example 3: ACBDACBD", solution("ACBDACBD"), "ACBDACBD");

        // === Edge cases ===
        total++; passed += check("Empty string",     solution(""),      "");
        total++; passed += check("Single A",         solution("A"),     "A");
        total++; passed += check("Single B",         solution("B"),     "B");
        total++; passed += check("AB",               solution("AB"),    "");
        total++; passed += check("BA",               solution("BA"),    "");
        total++; passed += check("CD",               solution("CD"),    "");
        total++; passed += check("DC",               solution("DC"),    "");
        total++; passed += check("AC (no cancel)",   solution("AC"),    "AC");
        total++; passed += check("AD (no cancel)",   solution("AD"),    "AD");
        total++; passed += check("BC (no cancel)",   solution("BC"),    "BC");
        total++; passed += check("AA (no cancel)",   solution("AA"),    "AA");

        // === Nested cancellations ===
        total++; passed += check("ABBA",  solution("ABBA"),  "");          // A[BB]A -> wait, BB doesn't cancel. AB cancel -> BA -> cancel -> ""
        total++; passed += check("ACDB",  solution("ACDB"),  "");          // A[CD]B -> AB -> ""
        total++; passed += check("ACDCDB", solution("ACDCDB"), "");        // peel CD pairs from middle
        total++; passed += check("CABD wraps AB", solution("CABD"), "");   // C[AB]D -> CD -> ""

        // === Long alternating that all cancels ===
        StringBuilder allCancel = new StringBuilder();
        for (int i = 0; i < 1000; i++) allCancel.append("AB");
        total++; passed += check("1000x AB", solution(allCancel.toString()), "");

        // === Long alternating that cancels nothing ===
        StringBuilder noCancel = new StringBuilder();
        for (int i = 0; i < 1000; i++) noCancel.append("AC");
        total++; passed += check("1000x AC", solution(noCancel.toString()), noCancel.toString());

        // === Partial cancellation ===
        total++; passed += check("AABB", solution("AABB"), "");           // A[AB]B -> AB -> ""
        total++; passed += check("CCDD", solution("CCDD"), "");           // C[CD]D -> CD -> ""
        total++; passed += check("AABBC", solution("AABBC"), "C");
        total++; passed += check("CABABABABD", solution("CABABABABD"), ""); // peel ABs then CD

        // === Mixed with no-cancel residue ===
        total++; passed += check("ACBD",  solution("ACBD"),  "ACBD");
        total++; passed += check("ABCDBA", solution("ABCDBA"), "");        // [AB][CD]BA -> BA -> ""

        // === Performance test (250,000 chars) ===
        StringBuilder large = new StringBuilder();
        java.util.Random rnd = new java.util.Random(42);
        String chars = "ABCD";
        for (int i = 0; i < 250_000; i++) large.append(chars.charAt(rnd.nextInt(4)));
        long start = System.currentTimeMillis();
        String res = solution(large.toString());
        long elapsed = System.currentTimeMillis() - start;
        System.out.printf("[PERF] 250k random -> residue length=%d, time=%dms%n", res.length(), elapsed);

        // === Worst case: 250,000 ABs, should fully cancel ===
        StringBuilder worst = new StringBuilder();
        for (int i = 0; i < 125_000; i++) worst.append("AB");
        start = System.currentTimeMillis();
        res = solution(worst.toString());
        elapsed = System.currentTimeMillis() - start;
        System.out.printf("[PERF] 250k all-cancel -> residue length=%d, time=%dms%n", res.length(), elapsed);
        total++; passed += res.isEmpty() ? 1 : 0;

        System.out.println("\nPassed: " + passed + " / " + total);
    }

    private static int check(String name, String actual, String expected) {
        boolean ok = actual.equals(expected);
        System.out.printf("[%s] %-25s got='%s' expected='%s'%n",
                ok ? "PASS" : "FAIL", name, actual, expected);
        return ok ? 1 : 0;
    }
    public static String solution(String S) {
        StringBuilder stack = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            int n = stack.length();
            if (n > 0 && cancels(stack.charAt(n - 1), c)) {
                stack.deleteCharAt(n - 1);
            } else {
                stack.append(c);
            }
        }
        return stack.toString();
    }

    private static boolean cancels(char a, char b) {
        return (a == 'A' && b == 'B') || (a == 'B' && b == 'A')
                || (a == 'C' && b == 'D') || (a == 'D' && b == 'C');
    }
}
