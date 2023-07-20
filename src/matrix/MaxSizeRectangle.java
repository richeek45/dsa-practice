package matrix;

import java.util.Stack;

public class MaxSizeRectangle {
    private static void printMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        // for checking the output of the final matrix
        for (int i = 0; i < row; i++) {
            for (int j  = 0; j < col; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static int histogramArea(int[] arr) {
        Stack<Integer> stack = new Stack<>();

        int i = 0;
        int N = arr.length;
        int maxArea = arr[0];
        while (i < N) {
            if (stack.isEmpty() || arr[stack.peek()] < arr[i]) {
                stack.push(i++);
            } else {
                // the element is the top of stack is greater than arr[i]
                int top = stack.pop();
                // Now we calculate the area of the top element with index i as smaller element in the right
                // and element currently in top of the stack as the left smaller element if present
                // area = top larger element * (rightSmaller element index - leftSmaller index - 1)
                int area = arr[top] * (stack.empty() ? i : (i - stack.peek() - 1));
                maxArea = Math.max(maxArea, area);
            }
        }

        // for the elements left in the stack
        while (!stack.isEmpty()) {
            int top = stack.pop();
            int area = arr[top] * (stack.empty() ? i : (i - stack.peek() - 1));
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
    static void findMaxSizeRectangle(int[][] matrix) {
        // O (R * C * C)
        // Applying the same principle for calculating the largest area in histogram
        // Run a loop to traverse through the rows.
        // Now If the current row is not the first row then update the row as follows,
        // if matrix[i][j] is not zero then matrix[i][j] = matrix[i-1][j] + matrix[i][j].
        // Find the maximum rectangular area under the histogram, consider the ith row as heights of bars of a histogram.
        // This can be calculated as given in this article Largest Rectangular Area in a Histogram
        // Do the previous two steps for all rows and print the maximum area of all the rows.
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int maxArea = Integer.MIN_VALUE;
        for (int i = 1; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (matrix[i][j] != 0) {
                    matrix[i][j] += matrix[i-1][j];
                }
            }
        }

        printMatrix(matrix);

        for (int i = 0; i < rowLen; i++) {
            int area = histogramArea(matrix[i]);
            maxArea = Math.max(maxArea, area);
        }
        System.out.println(maxArea);
    }

    static void findMaxSizeRectangle2(int[][] matrix) {
        // Using DP -> Only works for square matrix
        //  1) Construct a sum matrix S[R][C] for the given M[R][C].
        //     a)    Copy first row and first columns as it is from M[][] to S[][]
        //     b)    For other entries, use following expressions to construct S[][]
        //         If M[i][j] is 1 then
        //            S[i][j] = min(S[i][j-1], S[i-1][j], S[i-1][j-1]) + 1
        //         Else /*If M[i][j] is 0*/
        //            S[i][j] = 0
        //  2) Find the maximum entry in S[R][C]
        //  3) Using the value and coordinates of maximum entry in S[i], print
        //   sub-matrix of M[][]
        int row = matrix.length, col = matrix[0].length;
        int[][] sumMatrix = new int[row][col];

        // copying the values of first row
        for (int i = 0; i < col; i++) {
            sumMatrix[0][i] = matrix[0][i];
        }
        // copying the values of first col
        for (int i = 0; i < row; i++) {
            sumMatrix[i][0] = matrix[i][0];
        }
        // sum matrix is formed
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 1) {
                    // finding the minimum of all three corner of matrix[i][j] and adding 1 to it
                    // to check if all the four corner are 1
                    sumMatrix[i][j] = 1 + Math.min(sumMatrix[i-1][j-1], Math.min(sumMatrix[i-1][j], sumMatrix[i][j-1]));
                } else {
                    sumMatrix[i][j] = 0;
                }
            }
        }
        // finding the coordinates of maxEntry found with max value
        int max_i = -1;
        int max_j = -1;
        int maxEntry = sumMatrix[0][0];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (maxEntry < sumMatrix[i][j]) {
                    maxEntry = sumMatrix[i][j];
                    max_i = i;
                    max_j = j;
                }
            }
        }
        printMatrix(sumMatrix);
        System.out.println(max_i + "," + max_j + ":" + maxEntry);

        for (int i = max_i; i > max_i - maxEntry; i--) {
            for (int j = max_j; j > max_j - maxEntry; j--) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        int mat[][] = {
                { 0, 1, 1, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 1, 1, 0, 0 },
        };

//        findMaxSizeRectangle(mat);
        findMaxSizeRectangle2(mat);
    }
}
