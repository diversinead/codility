package main.java.yellow_belt;

import java.util.HashSet;
import java.util.Set;

public class Lesson04 {

    public static int frogRiverOne(int X, int[] A) {
        Set<Integer> positions = new HashSet<>();

        for (int i = 0; i < A.length; i++) {
            positions.add(A[i]);

            if (positions.size() == X) {
                return i;
            }
        }

        return -1; // The frog can never cross
    }

    public static int isPermutation(int[] A) {
        int n = A.length;
        boolean[] seen = new boolean[n+1];

        for (int num : A) {
            if (num < 1 || num > n || seen[num]) {
                return 0;  // Not in range or duplicate
            }
            seen[num] = true;
        }

        return 1;  // All numbers from 1 to N seen exactly once
    }

}
