package commonPatterns;
// 22. Generate Parentheses
// Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
// Input: n = 3, Output: ["((()))","(()())","(())()","()(())","()()()"]
//Input: n = 1, Output: ["()"]


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GenerateParenthesis {

    public static void backtrack(int n, int open, int closed, Stack<Character> stack, List<String> result) {
        if (n == open && open == closed) {
            StringBuilder sb = new StringBuilder();
            for (char c : stack) {
                sb.append(c);
            }
            result.add(sb.toString());
            return;
        }

        if (open < n) {
            stack.push('(');
            backtrack(n, open + 1, closed, stack, result);
            stack.pop();
        }

        if (open > closed) {
            stack.push(')');
            backtrack(n, open, closed + 1, stack, result);
            stack.pop();
        }
    }
    

    public static void generateParenthesis(int n) {
        Stack<Character> stack = new Stack<>();
        List<String> result = new ArrayList<>();
        backtrack(n, 0, 0, stack, result);
        System.out.println(result);
    }

    public static void main(String[] args) {
        int n = 3;
        generateParenthesis(n);
    }
}
