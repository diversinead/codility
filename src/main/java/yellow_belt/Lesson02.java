package main.java.yellow_belt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lesson02 {

    /*******************************************************************************
     *
     *  GOAL: Rotate array to the right by K steps
     *
     *  STEPS
     *  1. If A.length = 0, return A
     *  2. Loop K times
     *  3. Create a new int array (B) each time
     *  4. Set the first element of B to the last element in A
     *  5. Shift elements right in A
     *  6. Use rotated result for next iteration
     *
     *  KEY NOTES
     *  > If A.length = 0, return A
     *
     *  > Rotating by K is the same as by K % N
     *    Why? – Because doing N full rotations brings you back to the start,
     *    and any extra multiples of N do nothing. Only the remainder r = K % N matters.
     *
     */

    public static int[] cyclicRotation(int[] A, int K) {
        int n = A.length;
        if (n == 0) return A;

        for (int j = 0; j < K % n; j++) {
            int[] B = new int[n];
            B[0] = A[n - 1];       // Last element becomes first

            for (int i = 1; i < n; i++) {
                B[i] = A[i - 1];   // Shift elements right
            }

            A = B;  // Use rotated result for next iteration
        }

        return A;
    }

    /*******************************************************************************
     *  GOAL: Return the odd value in the array (doesn't have a pair) the
     *
     *  STEPS
     *  1. Sort the array
     *  2. Check if number next to it matches, jump ahead 2 (pair)
     *  3. If nothing found in loop - return last element as thats the odd one
     *
     */

    public static int oddOccurrencesInArray(int[] A) {
        if (A.length == 0) return -1;
        Arrays.sort(A);

        // Count occurrences of each number
        for (int i = 0; i < A.length - 1; i += 2)  {
            if (A[i] != A[i + 1]) return A[i];
        }

        return A[A.length - 1]; // Return the last element
    }

    /*******************************************************************************
     *  GOAL: Return the odd value in the array (doesn't have a pair) the
     *
     *  STEPS
     *  1. Count the occurrences of each number
     *  2. Find the number with an odd count
     *
     *  KEY NOTES
     *  > if (% 2 != 0): this means its odd (not even)
     *
     */
    public static int oddOccurrencesInArray_optB(int[] A) {
        Map<Integer, Integer> countMap = new HashMap<>();

        // Count occurrences of each number
        for (int num: A) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // Find the number with an odd count
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                return entry.getKey();
            }
        }

        return -1;
    }



}
