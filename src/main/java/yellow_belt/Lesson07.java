package main.java.yellow_belt;

import java.util.Stack;

public class Lesson07 {

    public static boolean brackets(String S) {
        Stack<Character> stack = new Stack<>();

        for (char ch : S.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) return false;

                char open = stack.pop();
                if ((ch == ')' && open != '(') ||
                        (ch == ']' && open != '[') ||
                        (ch == '}' && open != '{')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static int fishAlive(int[] A, int[] B) {

        return 0;
    }
}
