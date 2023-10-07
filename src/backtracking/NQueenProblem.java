package backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NQueenProblem {
    // The N Queen is the problem of placing N chess queens on an N×N chessboard so that no two queens attack each other
    static List<List<Integer>> results = new ArrayList<List<Integer>>();
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
        List<Integer> temp = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            temp.add(0);
        }
        solveNQueen(N, 0, temp, results);
        System.out.println(results);
        System.out.println(results.size());
    }


    public static void solveNQueenBitMask(char[][] board, int N, int row, int rowMask, int ldMask, int rdMask) {

        // left shift by N returns (N+1) digits with trailing zeros and subtracting 1 will give all set bits of N digits
        int allSetBits = (1 << N) - 1;

        if (allSetBits == rowMask) {
            List<Integer> v = new ArrayList<>();
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(board[i][j] == 'Q') {
                        v.add(j+1);
                    }
                }
            }
//            printBoard(board);
            results.add(v);
        }

        // OR operation on the rowMask, ldMask and rdMask gives us set bits which is unsafe for positioning queen
        // complementing the result and (AND) operation with allSetBits gives us set bits indicating available
        // position for Queen hence checking for safeBit > 0
        int safeBit = allSetBits & ~(rowMask | ldMask | rdMask);
        while(safeBit > 0) {
            // getting the rightMost set bit which is safe
            // here the set bit is safe because we complemented the unsafe set bits
            int p = safeBit & (-safeBit);
            // log function and diving by log2 to get the col position from the set bit
            int col = (int)(Math.log(p) / Math.log(2));
            board[row][col] = 'Q';

            // now we need to set the mask bits
            // OR operation in rowMask to set the pth bit and leftShift in ldMask to block the left diagonal
            // and right shift to block the right diagonal
            solveNQueenBitMask(board, N, row+1, rowMask | p, (ldMask | p) << 1, (rdMask | p) >> 1);
            // unsetting the rightmost safe bit for backtracking
            safeBit = safeBit & (safeBit - 1);
            board[row][col] = ' ';
        }
    }

    // Using bit-mask to find the queen position
    public static void nQueen2(int N) {
        char[][] board = new char[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                board[i][j] = ' ';
            }
        }

        // set bits of rowMask indicates the Queen present in the set col
        // set bits of ldMask indicates the left diagonal unsafe position for set bits in rowMask
        // set bits of rdMask indicates the right diagonal unsafe position for set bits in rowMask
        int row = 0, rowMask = 0, ldMask = 0, rdMask = 0;
        solveNQueenBitMask(board, N, row, rowMask, ldMask, rdMask);
        System.out.println(results);
        System.out.println(results.size());
    }

    public static boolean validatePos(int[][] board, int i, int j) {
        // validate rows
        for(int c = 1; c <= j; c++) {
            if(board[i-1][c-1] == 1) {
                return false;
            }
        }

        // validate cols
        for(int r = 1; r <= i; r++) {
            if(board[r-1][j-1] == 1) {
                return false;
            }
        }

        int r = i;
        int c = j;
        // validate left diagonal for the rows above i -> climbing upwards
        while(c != board.length+1 && r != 0) {
            if (board[r-1][c-1] == 1) {
                return false;
            }
            r--;
            c++;
        }

        r = i;
        c = j;
        // validating right diagonal
        while(r != 0 && c != 0) {
            if(board[r-1][c-1] == 1) {
                return false;
            }
            r--;
            c--;
        }

        return true;
    }

    public static void nQueen3(int N) {
        int[][] board = new int[N][N];
        Stack<int[]> queenPositions = new Stack<>();

        int j = 1;
        for(int i = 1; i <= board.length; i++) {
            for(; j <= board.length; j++) {
                if(validatePos(board, i, j)) {
//                    System.out.print("work" + (i-1) + " " + (j-1) + ", ");
                    board[i-1][j-1] = 1;
                    queenPositions.push(new int[]{ i, j });
                    break;
                }
            }
            // if all the queen positions are not filled in all the rows
            j = 1;
            if(queenPositions.size() != i) {
//                System.out.println(i + " size = " + queenPositions.size());
                if(!queenPositions.isEmpty()) {
                    // do backtracking by popping the last queen from the stack
                    int[] queenPos = queenPositions.pop();
                    board[queenPos[0]-1][queenPos[1]-1] = 0; // unsetting the pos where the queen was positioned
                    // re-setting (i, j) to the previous queen position
                    i = queenPos[0]-1;
                    j = queenPos[1]+1;
                }
            }

            // if all queen positions are filled in all the rows
            if (i == board.length) {
                printBoard(board);
                // backtracking to find the other solutions
                if(!queenPositions.isEmpty()) {
                    // do backtracking by popping the last queen from the stack
                    int[] queenPos = queenPositions.pop();
                    board[queenPos[0]-1][queenPos[1]-1] = 0; // unsetting the pos where the queen was positioned
                    // re-setting (i, j) to the previous queen position
                    i = queenPos[0]-1;
                    j = queenPos[1]+1;
                }
            }
        }
    }

    public static void printBoard(int[][] board) {
        int count = 0;
        for (int[] row : board) {
            for (int el : row) {
                if (el == 1) {
                    count++;
                }
            }
        }
        // Not valid solution
        if (count != board.length) {
            return;
        }

        for(int i = 0; i < board.length; i++) {
            System.out.print("|");
            for(int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printBoard(char[][] board) {
        for(int i = 0; i < board.length; i++) {
            System.out.print("|");
            for(int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 4; // row, col of the n*n board
//        nQueen(len);
//        nQueen2(len);
        nQueen3(len);
    }
}
