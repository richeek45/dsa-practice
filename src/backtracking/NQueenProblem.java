package backtracking;

import java.util.ArrayList;
import java.util.List;

public class NQueenProblem {
    // The N Queen is the problem of placing N chess queens on an N×N chessboard so that no two queens attack each other
    static boolean[] leftDiagonal;
    static boolean[] rightDiagonal;
    static boolean[] cols;

    private static void solveNQueen(int N, int row, List<Integer> ans, List<List<Integer>> results) {
        if (row == N) {
            results.add(new ArrayList<>(ans));
            return;
        }

        // for cols
        for(int col = 0; col < N; col++) {
            if (cols[col] || leftDiagonal[row+col] || rightDiagonal[row-col+N]) {
                continue;
            }
            cols[col] = leftDiagonal[row+col] = rightDiagonal[row-col+N] = true;
            ans.set(col, row+1);
            solveNQueen(N, row+1, ans, results);
            cols[col] = leftDiagonal[row+col] = rightDiagonal[row-col+N] = false;
        }
    }

    public static void nQueen(int N) {
        // A modification is that we can find whether we have a previously placed queen in a column
        // or in left diagonal or in right diagonal in O(1) time. We can observe that
        // 1. For all cells in a particular left diagonal , their row + col  = constant.
        // 2. For all cells in a particular right diagonal, their row – col + n – 1 = constant.
        // Diagonals of row = N is 2N-1 for left and right diagonals
        leftDiagonal = new boolean[2*N];
        rightDiagonal = new boolean[2*N];
        cols = new boolean[N]; // to store the queens in respectively cols of a row
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            temp.add(0);
        }
        solveNQueen(N, 0, temp, results);
        System.out.println(results);
        System.out.println(results.size());
    }


    public static void main(String[] args) {
        int len = 8; // row, col of the n*n board
        nQueen(len);
    }
}
