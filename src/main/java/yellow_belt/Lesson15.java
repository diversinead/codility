package main.java.yellow_belt;

public class Lesson15 {

    public static boolean hasSubarrayWithSum(int[] A, int s) {
        int front = 0, total = 0;

        for (int back = 0; back < A.length; back++) {
            while (front < A.length && total + A[front] <= s) {
                total += A[front];
                front++;
            }
            if (total == s) return true;
            total -= A[back]; // shrink from the back
        }

        return false;
    }

}
