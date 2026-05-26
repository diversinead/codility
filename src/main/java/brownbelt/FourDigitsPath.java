package main.java.brownbelt;

public class FourDigitsPath {

    private static int N, M;
    private static int[][] board;
    private static boolean[][] visited;
    private static int best;

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    public static void main(String[] args) {
        int passed = 0, total = 0;

        // Example 1
        int[][] b1 = {
                {9, 1, 1, 0, 7},
                {1, 0, 2, 1, 0},
                {1, 9, 1, 1, 0}
        };
        total++; passed += check("Example 1 (3x5)", solution(b1), 9121);

        // Example 2
        int[][] b2 = {
                {1, 1, 1},
                {1, 3, 4},
                {1, 4, 3}
        };
        total++; passed += check("Example 2 (3x3)", solution(b2), 4343);

        // Example 3
        int[][] b3 = {{0, 1, 5, 0, 0}};
        total++; passed += check("Example 3 (1x5)", solution(b3), 1500);

        // 1x4 straight
        int[][] b4 = {{1, 2, 3, 4}};
        total++; passed += check("1x4 straight", solution(b4), 4321);

        // 1x4 all 9s
        int[][] b5 = {{9, 9, 9, 9}};
        total++; passed += check("1x4 all 9s", solution(b5), 9999);

        // 2x2 with 9 -- correct best is 9867 (not 9876)
        int[][] b6 = {
                {9, 8},
                {7, 6}
        };
        total++; passed += check("2x2 with 9", solution(b6), 9867);

        // 2x2 all 1s
        int[][] b7 = {
                {1, 1},
                {1, 1}
        };
        total++; passed += check("2x2 all 1s", solution(b7), 1111);

        // 1x5 starts with 0 -- best is 9876
        int[][] b8 = {{0, 9, 8, 7, 6}};
        total++; passed += check("1x5 starts with 0", solution(b8), 9876);

        // 3x5 all 9s
        int[][] b9 = {
                {9, 9, 9, 9, 9},
                {9, 9, 9, 9, 9},
                {9, 9, 9, 9, 9}
        };
        total++; passed += check("3x5 all 9s", solution(b9), 9999);

        // Stress test: 100x100
        int[][] big = new int[100][100];
        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++)
                big[i][j] = (i * 7 + j * 13) % 10;
        big[0][0] = 9; // ensure non-zero start exists
        long start = System.currentTimeMillis();
        int res = solution(big);
        long elapsed = System.currentTimeMillis() - start;
        System.out.printf("[PERF] 100x100 -> result=%d, time=%dms%n", res, elapsed);

        System.out.println("\nPassed: " + passed + " / " + total);
    }

    private static int check(String name, int actual, int expected) {
        boolean ok = actual == expected;
        System.out.printf("[%s] %-30s got=%d expected=%d%n",
                ok ? "PASS" : "FAIL", name, actual, expected);
        return ok ? 1 : 0;
    }

    public static int solution(int[][] Board) {
        board   = Board;
        N       = Board.length;
        M       = Board[0].length;
        visited = new boolean[N][M];
        best    = -1;

        // Try every cell as start, in order of digit value (descending) for early pruning
        for (int startDigit = 9; startDigit >= 1; startDigit--) {
            // If we already have a result whose leading digit > startDigit, stop
            if (best >= 0 && best / 1000 > startDigit) break;

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (board[r][c] != startDigit) continue;
                    visited[r][c] = true;
                    dfs(r, c, 1, startDigit);
                    visited[r][c] = false;
                }
            }
        }

        return best;
    }

    private static void dfs(int r, int c, int depth, int current) {
        if (depth == 4) {
            if (current > best) best = current;
            return;
        }
        for (int k = 0; k < 4; k++) {
            int nr = r + DR[k];
            int nc = c + DC[k];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
            if (visited[nr][nc]) continue;
            visited[nr][nc] = true;
            dfs(nr, nc, depth + 1, current * 10 + board[nr][nc]);
            visited[nr][nc] = false;
        }
    }
}