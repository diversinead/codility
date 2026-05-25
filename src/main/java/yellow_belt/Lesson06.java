package main.java.yellow_belt;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Lesson06 {

    public static int distinct(int[] A) {
        Set<Integer> distinct = new HashSet<>();

        for (int num : A) {

            if(num<-1000000 || num > 1000000)
                return -1;
            distinct.add(num);
        }
        return distinct.size();
    }

    public static int countTriplets(int[] A) {
        int n = A.length;
        return (n * (n - 1) * (n - 2)) / 6; // n choose 3
    }

    public static int tripletMaxValue(int[] A) {
        Arrays.sort(A);
        int n = A.length;

        int optA = A[n-1] * A[n-2] * A[n-3];
        int optB = A[0] * A[1] * A[n-1];

        return Math.max(optA, optB);
    }
    
    public static int triangle(int[] A){
        Arrays.sort(A);
        int n = A.length;

        for(int i = 0; i < n-2; i++){
            if((long)A[i] + A[i+1] > A[i+2]){
                return 1;
            }
        }

        return 0;
    }

}
