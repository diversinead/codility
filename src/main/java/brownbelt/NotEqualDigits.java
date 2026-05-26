package main.java.brownbelt;

public class NotEqualDigits {

    public static void main(String[] args) {
        int passed = 0, total = 0;

        // === Examples from the problem ===
        total++; passed += check("Example 1: 55",   solution(55),   56);
        total++; passed += check("Example 2: 1765", solution(1765), 1767);
        total++; passed += check("Example 3: 98",   solution(98),   101);
        total++; passed += check("Example 4: 44432", solution(44432), 45010);
        total++; passed += check("Example 5: 3298", solution(3298), 3401);

        // === Edge cases ===
        total++; passed += check("N = 1",   solution(1),   2);
        total++; passed += check("N = 9",   solution(9),   10);
        total++; passed += check("N = 10",  solution(10),  12);
        total++; passed += check("N = 99",  solution(99),  101);
        total++; passed += check("N = 100", solution(100), 101);
        total++; passed += check("Already valid: N = 12", solution(12), 13);
        total++; passed += check("Wraps trailing dup: N = 199", solution(199), 201);
        total++; passed += check("Big carry: N = 8999", solution(8999), 9010);
        total++; passed += check("Max-ish: N = 999999999", solution(999_999_999), 1_010_101_010);
        total++; passed += check("Limit: N = 1000000000", solution(1_000_000_000), 1_010_101_010);

        System.out.println("\nPassed: " + passed + " / " + total);
    }

    private static int check(String name, int actual, int expected) {
        boolean ok = actual == expected;
        System.out.printf("[%s] %-40s got=%d expected=%d%n",
                ok ? "PASS" : "FAIL", name, actual, expected);
        return ok ? 1 : 0;
    }

    public static int solution(int N) {
        char[] d = Long.toString((long) N + 1).toCharArray();

        while (true) {
            int n = d.length;
            int dup = -1;
            for (int i = 0; i + 1 < n; i++) {
                if (d[i] == d[i + 1]) { dup = i; break; }
            }
            if (dup == -1) break;

            char[] prefix = new char[dup + 2];
            System.arraycopy(d, 0, prefix, 0, dup + 2);
            prefix = incrementByOne(prefix);

            int suffixLen = n - (dup + 2);
            char[] next = new char[prefix.length + suffixLen];
            System.arraycopy(prefix, 0, next, 0, prefix.length);

            char prev = next[prefix.length - 1];
            for (int j = prefix.length; j < next.length; j++) {
                char c = (prev == '0') ? '1' : '0';
                next[j] = c;
                prev = c;
            }
            d = next;
        }

        return Integer.parseInt(new String(d));
    }

    private static char[] incrementByOne(char[] arr) {
        int i = arr.length - 1;
        int carry = 1;
        while (i >= 0 && carry > 0) {
            int v = (arr[i] - '0') + carry;
            arr[i] = (char) ('0' + (v % 10));
            carry = v / 10;
            i--;
        }
        if (carry > 0) {
            char[] bigger = new char[arr.length + 1];
            bigger[0] = '1';
            System.arraycopy(arr, 0, bigger, 1, arr.length);
            return bigger;
        }
        return arr;
    }
}
