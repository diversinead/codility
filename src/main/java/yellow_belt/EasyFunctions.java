package main.java.yellow_belt;

import java.util.LinkedHashMap;
import java.util.Map;

public class EasyFunctions {

    // Given an array of N integers, find the largest difference between 2 numbers
    public static int findMaxDifference(int[] arr) {
        if (arr == null || arr.length < 2) return 0;

        int min = arr[0];
        int max = arr[0];

        for (int num : arr) {
            if (num < min) min = num;
            if (num > max) max = num;
        }

        return max - min;
    }

    public static void printFibonacciSequence(int n) {
        int a = 0, b = 1;
        for (int i = 0; i <= n; i++) {
            System.out.print(a + " ");
            int temp = a + b;
            a = b;
            b = temp;
        }
    }

    // infficient for high numbers
    public static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }


    public static int fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0, b = 1;
        for (int i = 1; i < n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }



    // factorial
    public static long factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

    // print a triangle made of *
    public static void printTriangle(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // print an inverted triangle with a space after each asterisk
    public static void printInvertedTriangle(int n) {
        for (int i = 1; i <= n; i++) {
            for (int x = 1; x <= i; x++) {
                System.out.print(" ");
            }
            for (int j = n; j >= i; j--) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    // print the fibonaci sequence
    public static void printFibonacci(int n) {
        int count = 0;
        int a = 0;
        int b = 1;

        while(count <= n){
            if(count == n){
                System.out.print(a);
                break;
            }
            System.out.print(a + ", ");

            int c = a + b;
            a = b;
            b = c;
            count++;
        }
    }

    // Given a string containing words, find the longest word that satisfies specific conditions.
    public static int findLongestValidWord(String S) {
        String[] words = S.split(" ");
        int maxLength = -1;

        for (String word : words) {
            if (!word.matches("[a-zA-Z0-9]+")) continue; // skip if not alphanumeric

            int letters = 0;
            int digits = 0;

            for (char c : word.toCharArray()) {
                if (Character.isLetter(c)) {
                    letters++;
                } else if (Character.isDigit(c)) {
                    digits++;
                }
            }

            if (letters % 2 == 0 && digits % 2 == 1) {
                maxLength = Math.max(maxLength, word.length());
            }
        }

        return maxLength;
    }

    //Find the first unique number in a given sequence - performance issues with this one due to the 2 for loops
    public static int firstUnique(int[] arr) {
        boolean found;

        for (int i = 0; i < arr.length; i++) {
            found = false;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] == arr[j] && i != j) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return arr[i];
            }
        }
        return -1;
    }


    public static int firstUnique_optimized(int[] arr) {
        Map<Integer, Integer> countMap = new LinkedHashMap<>();

        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return -1;
    }

    public static String reverseString(String str) {
        String reversed = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }

        /* Using a stack
        Stack<Character> stack = new Stack<>();
        for (char ch : "hello".toCharArray()) stack.push(ch);
        StringBuilder reversed = new StringBuilder();
        while (!stack.isEmpty()) reversed.append(stack.pop());
         */

        return reversed;
    }



}
