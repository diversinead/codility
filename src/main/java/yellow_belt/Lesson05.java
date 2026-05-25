package main.java.yellow_belt;

public class Lesson05 {

    public static int passingCars(int[] A) {
        int eastCars = 0;
        int passingPairs = 0;

        for (int car : A) {
            if (car == 0) {
                eastCars++; // count east-bound cars
            } else {
                passingPairs += eastCars; // each west car passes all previous east cars
                if (passingPairs > 1000000000) {
                    return -1;
                }
            }
        }

        return passingPairs;
    }
}
