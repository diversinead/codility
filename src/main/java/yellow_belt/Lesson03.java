package main.java.yellow_belt;

public class Lesson03 {
    public static int frogJump(int X, int Y, int D){ // X = start, Y = end, D = distance per jump

        int jumpCount = 0;

        while(X < Y) {
            X = X + D;
            jumpCount++;
        }

        //return jumpCount;

        // ONly got 44% for above - need to use this:More efficient
         return (int) Math.ceil((double) (Y - X) / D);
    }

    public static int permMissingElem(int[] A){
        int n = A.length + 1; // +1 for missing element
        if (n <= 1) return 1;

        //calculate expected sum
        int expectedSum = n * (n + 1) / 2;

        // Calculate actual sum
        int actualSum = 0;
        for(int num: A){
            actualSum += num;
        }

        return expectedSum - actualSum;
    }

    public static int tapeEquilibrium(int[] A) {
        //Calc total sum of array
        int n = A.length;
        int totalSum = 0;
        for (int num: A){
            totalSum = totalSum + num; // totalSum += num
        }

        int leftSum = 0;
        int rightSum;
        int minDiff = Integer.MAX_VALUE;;

        // Loop through array
        for(int i = 0; i < n-1; i++){
            leftSum = leftSum + A[i];
            rightSum = totalSum - leftSum;

            int diff = Math.abs(leftSum - rightSum);
            minDiff = Math.min(minDiff, diff);
        }

        return minDiff;
    }
}
