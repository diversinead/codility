package main.java.yellow_belt;

public class Lesson09 {

    public static int maximumSlice(int[] A) {
        int maxEndingHere = 0;
        int maxSoFar = 0;

        for (int num : A) {
            maxEndingHere = Math.max(0, maxEndingHere + num); //If maxEndingHere goes below 0, reset it to 0.
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

}
