package main.java.brownbelt;

public class TwoProjects {

    public int solution(int[] V, int[] A, int[] B) {
        int N = V.length;
        int M = A.length;

        int[] numPrereqs = new int[N];
        int[] theOnlyPrereq = new int[N]; // valid only when numPrereqs[k] == 1

        for (int L = 0; L < M; L++) {
            int k = B[L];
            if (numPrereqs[k] == 0) {
                theOnlyPrereq[k] = A[L];
            }
            numPrereqs[k]++;
        }

        long best = 0; // baseline: do nothing

        // Top two values among "free" projects (no prerequisites)
        long top1 = Long.MIN_VALUE, top2 = Long.MIN_VALUE;
        for (int k = 0; k < N; k++) {
            if (numPrereqs[k] == 0) {
                long v = V[k];
                if (v > top1) { top2 = top1; top1 = v; }
                else if (v > top2) { top2 = v; }
            }
        }
        if (top1 != Long.MIN_VALUE) best = Math.max(best, top1);
        if (top2 != Long.MIN_VALUE) best = Math.max(best, top1 + top2);

        // For each free project p, find the best dependent project
        // whose ONLY prerequisite is p.
        long[] bestDep = new long[N];
        boolean[] hasDep = new boolean[N];
        for (int k = 0; k < N; k++) {
            if (numPrereqs[k] == 1) {
                int p = theOnlyPrereq[k];
                if (numPrereqs[p] == 0) {
                    if (!hasDep[p] || V[k] > bestDep[p]) {
                        bestDep[p] = V[k];
                        hasDep[p] = true;
                    }
                }
            }
        }
        for (int k = 0; k < N; k++) {
            if (numPrereqs[k] == 0 && hasDep[k]) {
                best = Math.max(best, (long) V[k] + bestDep[k]);
            }
        }

        return (int) best;
    }
}