package branch_and_bound;

public class NQueenProblem {

    private static void printBoard(int[][] board) {
        int N = board.length;
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                System.out.printf("%2d ", board[row][col]);
            }
            System.out.println();
        }
    }


    private static boolean isSafe(
            int row,
            int col,
            int[][] leftDiagonal,
            int[][] rightDiagonal,
            boolean[] rowLookup,
            boolean[] leftDiagonalLookup,
            boolean[] rightDiagonalLookup) {

        if (rowLookup[row] || leftDiagonalLookup[leftDiagonal[row][col]] || rightDiagonalLookup[rightDiagonal[row][col]]) {
            return false;
        }

        return true;
    }


    private static boolean solveNQueenUtil(
            int[][] board,
            int col, int N,
            int[][] leftDiagonal,
            int[][] rightDiagonal,
            boolean[] rowLookup,
            boolean[] leftDiagonalLookup,
            boolean[] rightDiagonalLookup
            ) {

        // base case when col+1 > N
        if (col >= N) {
            return true;
        }

        // for a col, trying to place on every row until a queen is placed
        for (int row = 0; row < N; row++) {
            // checking if queen can be positioned
            if (isSafe(row, col, leftDiagonal, rightDiagonal, rowLookup, leftDiagonalLookup, rightDiagonalLookup)) {
                board[row][col] = 1;
                rowLookup[row] = true;
                leftDiagonalLookup[leftDiagonal[row][col]] = true;
                rightDiagonalLookup[rightDiagonal[row][col]] = true;


                // recur to the next col for placing the next queens
                if (solveNQueenUtil(board, col + 1, N, leftDiagonal, rightDiagonal, rowLookup, leftDiagonalLookup, rightDiagonalLookup)) {
                    return true;
                }

                // if placing queen in board[row][col] is not leading to a solution, then remove the queen
                board[row][col] = 0;
                rowLookup[row] = false;
                leftDiagonalLookup[leftDiagonal[row][col]] = false;
                rightDiagonalLookup[rightDiagonal[row][col]] = false;

            }
        }

        // Queen cannot be placed in the col
        return false;
    }


    public static void solveNQueen(int N) {
        int[][] board = new int[N][N];
        int[][] leftDiagonal = new int[N][N];
        int[][] rightDiagonal = new int[N][N];
        boolean[] rowLookup = new boolean[N];
        boolean[] leftDiagonalLookup = new boolean[2*N-1];
        boolean[] rightDiagonalLookup = new boolean[2*N-1];

        // initialize the diagonal values
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                leftDiagonal[row][col] = row + col;
                rightDiagonal[row][col] = row - col + N - 1;
            }
        }

        boolean found = solveNQueenUtil(board, 0, N, leftDiagonal, rightDiagonal, rowLookup, leftDiagonalLookup, rightDiagonalLookup);
        printBoard(board);
        System.out.println(found);

    }


    public static void main(String[] args) {
        int N = 8; // N * N matrix row and col
        solveNQueen(N);
    }
}
