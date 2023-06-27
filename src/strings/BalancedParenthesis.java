package strings;

import java.util.ArrayDeque;

public class BalancedParenthesis {
    static boolean checkParenthesisUsingStack(String expr) {

        ArrayDeque<Character> stack = new ArrayDeque<Character>();
        for(int i = 0; i < expr.length(); i++) {
            char x = expr.charAt(i);
            if (x == '[' || x == '{' || x == '(') {
                stack.push(x);
                continue;
            }

            if (stack.isEmpty()) {
                // the first paranthesis is closed parenthesis which indicates never getting balanced
                return false;
            }

            char check;
            switch(x) {
                case ')': {
                    check = stack.pop();
                    if (check == '[' || check =='{') {
                        return false;
                    }
                    break;
                }
                case ']': {
                    check = stack.pop();
                    if (check == '(' || check == '{') {
                        return false;
                    }
                    break;
                }
                case '}': {
                    check = stack.pop();
                    if (check == '(' || check == '[') {
                        return false;
                    }
                    break;
                }
            }
        }
        return stack.isEmpty();
    }

    static boolean checkParenthesisWithoutUsingStack(String expr) {
        char[] stack = new char[expr.length()];
        int i = -1;
        for (char ch: expr.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack[++i] = ch;
            } else {
                if (i >= 0 && (
                        (stack[i] == '(' && ch == ')') ||
                                (stack[i] == '{' && ch == '}') ||
                                (stack[i] == '[' && ch == ']'))) {
                    i--;
                } else {
                    return false;
                }
            }
        }
        System.out.println(i);
        return i == -1;
    }

    public static void main(String[] args) {
        String expr = "([{}])";
        String expr1 = "[()]{}{[()()]()}";
//        boolean isBalanced = checkParenthesisUsingStack(expr1);
        boolean isBalanced = checkParenthesisWithoutUsingStack(expr1);
        System.out.println(isBalanced);
    }
}
