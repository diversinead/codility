package main.java;

import java.util.Arrays;

import static main.java.yellow_belt.EasyFunctions.*;
import static main.java.yellow_belt.Lesson01.*;
import static main.java.yellow_belt.Lesson02.*;
import static main.java.yellow_belt.Lesson03.*;
import static main.java.yellow_belt.Lesson04.*;
import static main.java.yellow_belt.Lesson05.*;
import static main.java.yellow_belt.Lesson06.*;
import static main.java.yellow_belt.Lesson07.*;
import static main.java.yellow_belt.Lesson09.*;
import static main.java.yellow_belt.Lesson15.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n*** JAVA ***");

        switch (args[0]) {
            case "Lesson01": testLesson01();
                break;

            case "Lesson02": testLesson02();
                break;

            case "Lesson03": testLesson03();
                break;
                
            case "Lesson04": testLesson04();
                break;

            case "Lesson05": testLesson05();
                break;

            case "Lesson06": testLesson06();
                break;

            case "Lesson07": testLesson07();
                break;

            case "Lesson09": testLesson09();
                break;

            case "Lesson15": caterpillarMethod();
                break;

            case "Easy": easyOther();

            break;
        }
    }

    private static void caterpillarMethod() {

        int[] A = {2, 9, 3, 8, 10, 4, 6};
        int s = 18;
        System.out.println("Array A has a subarray that sums to "+ s + ": " + hasSubarrayWithSum(A, s));


    }

    private static void testLesson09() {
        System.out.println("\n*** LESSON 09 ***\n");

        int[] A = {5, -7, 3, 5, -2, 4, -1};
        System.out.println("Max slice: " + maximumSlice(A));
    }

    private static void testLesson07() {
        System.out.println("\n*** LESSON 07 ***\n");

        String brackets = "{[()()]}";
        System.out.println("brackets: " + brackets(brackets));
    }

    private static void testLesson06() {
        System.out.println("\n*** LESSON 06 ***\n");

        int[] A = {4, 1, 3, 2, 5, 4, 2, 3};
        System.out.println("Number of distinct values in array: " + distinct(A));

        int[] B = {0,1,2,3,4,5,6};
        System .out.println("Number of triplet combinations in B: " + countTriplets(B));

        int[] C = {-3, 1, 2, -2, 5, 6};
        System .out.println("Max val of a triplet combinations in C: " + tripletMaxValue(C));

        System .out.println("Triangle in C: " + triangle(C));
    }

    private static void testLesson05() {

        System.out.println("\n*** LESSON 05 ***\n");

        int[] A = {0, 1, 0, 1, 1, 0, 1, 1, 0, 0};
        System.out.println("Number of passing cars is: " + passingCars(A));
    }

    private static void testLesson04() {
        System.out.println("\n*** LESSON 04 ***\n");
        int X = 5;
        int[] A = {4, 1, 3, 2, 5, 4, 2, 3};

        System.out.println("Frog can jump at second: " + frogRiverOne(X, A));

        A = new int[]{1, 2, 3};
        if(isPermutation(A)==1){
            System.out.println("Is a permutation");
        }
        else
            System.out.println("Is not a permutation");

    }

    private static void testLesson03() {
        System.out.println("\n*** LESSON 03 ***\n");

        System.out.println("Jump count: " + frogJump(10, 85, 30));

        int[] A = {2,3,1,5,6,4,7,9};
        System.out.println("Missing element is: " + permMissingElem(A));
        System.out.println("Min diff is: " + tapeEquilibrium(A));

    }

    private static void testLesson02() {
        System.out.println("\n*** LESSON 02 ***\n");

        int[] A = {3,8,9,7,6};
        System.out.println("Array after rotations: " + Arrays.toString(cyclicRotation(A, 3)));

        int[] B = {9,3,9,3,9,7,9};
        System.out.println("Odd element: " + oddOccurrencesInArray(B));

    }

    private static void testLesson01() {
        System.out.println("longest binary gap: " + binaryGap(529));
    }

    private static void easyOther() {
        int[] numbers = {7, 2, 10, 1, 9};
        System.out.println("Max difference: " + findMaxDifference(numbers));

        int n = 2;
        System.out.println("factorial of: " + n  + " is: "  + factorial(n));

        n= 6;
        System.out.println("Print first " + n  + " numbers of the fibonacci series: ");
        printFibonacciSequence(n);

        System.out.println("\nNumber " + n  + " of the fibonacci series is " + fibonacciIterative(n));
        System.out.println("\nNumber " + n  + " of the fibonacci recursive " + fibonacciRecursive(n));

//        System.out.println("\nPrint a triangle: ");
//        printTriangle(5);
//        printInvertedTriangle(5);
//
//        printFibonacci(8);
//
//        System.out.println("\nlongest valid word is: " + findLongestValidWord("test 5 a0A pass007 ?xy1"));
//
//        int[] uniqueNumbers = {1, 2, 1, 3, 3, 4, 4, 9, 8};
//        System.out.println("\nFirst unique number is: " + firstUnique(uniqueNumbers));
//
//        System.out.println("\nFirst unique number is: " + firstUnique_optimized(uniqueNumbers));
    }
}