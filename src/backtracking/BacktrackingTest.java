package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BacktrackingTest {
    private static boolean[] cols; // to store the col number where the Queen is placed
    private static boolean[] leftDiagonal; // row + col = constant
    private static boolean[] rightDiagonal; // row - col + N -1 = constant

    private static void solveNQueen(List<List<Integer>> results, int N, int row, List<Integer> sol) {
        // all the Queens have been placed
        if(row == N) {
            results.add(new ArrayList<>(sol));
            return;
        }

        for(int col = 0; col < N; col++) {
            if(cols[col] || leftDiagonal[row+col] || rightDiagonal[row-col+N]) {
                continue;
            }

            cols[col] = leftDiagonal[row+col] = rightDiagonal[row-col+N] = true;
            sol.set(col, row+1);
            solveNQueen(results, N, row+1, sol);
            // Making it false after the solveNQueen function failed to find the solution for backtracking
            cols[col] = leftDiagonal[row+col] = rightDiagonal[row-col+N] = false;
        }

    }

    public static void NQueen(int N) {
        List<List<Integer>> results = new ArrayList<>(); // storing all the results in an arraylist of list
        cols = new boolean[N];
        leftDiagonal = new boolean[2*N]; // for N*N board 2N-1 number of diagonals
        rightDiagonal = new boolean[2*N];
        List<Integer> temp = new ArrayList<>();
        for(int i = 0; i < N; i++) temp.add(0);
        solveNQueen(results, N, 0, temp);
        System.out.println(results);
    }

    public static void main(String[] args) {
        int N = 4; // size of N*N chess board
        NQueen(N);
    }
}
