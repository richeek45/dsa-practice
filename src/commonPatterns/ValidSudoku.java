package commonPatterns;

import java.util.*;

public class ValidSudoku {

    static boolean validSudoku(String[][] board) {
        Map<Integer, Set<String>> rows = new HashMap<Integer, Set<String>>();
        Map<Integer, Set<String>> cols = new HashMap<Integer, Set<String>>();
        Map<String, Set<String>> squares = new HashMap<String, Set<String>>();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (Objects.equals(board[r][c], ".")) continue;

                String squareKey =  (r/3) + "," + (c/3);
                if (
                        rows.computeIfAbsent(r, k -> new HashSet<>()).contains(board[r][c])
                        ||
                        cols.computeIfAbsent(c, k -> new HashSet<>()).contains(board[r][c])
                        ||
                        squares.computeIfAbsent(squareKey, k -> new HashSet<>()).contains(board[r][c])
                )  {
                    return false;
                }

                rows.get(r).add(board[r][c]);
                cols.get(c).add(board[r][c]);
                squares.get(squareKey).add(board[r][c]);
            }
        }

        return true;
    }

    static boolean validSudoku1(int[][] board) {
        int r = board.length;
        int c = board[0].length;
        int[][] rows =  new int[r+1][c+1];
        int[][] cols = new int[r+1][c+1];
        int[][] squares = new int[r+1][c+1];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (Objects.equals(board[i][j],0)) continue;

                // store the value of board[i][j] in the array index
                // while iterating in the row if  the same  value is present the same index will be 1.
                int val =  board[i][j];

                if (rows[i][val] == 1) return false;
                rows[i][val] = 1;

                if (cols[j][val] == 1) return false;
                cols[j][val] = 1;

                int squareKey = (i/3) * 3 + (j/3);
                if (squares[squareKey][val] == 1) return false;
                squares[squareKey][val] = 1;

            }
        }
        return true;
    }

    static boolean validSudoku2(int[][] board) {
        // using bitwise shift operator to find duplicates
        int r = board.length;
        int c = board[0].length;
        int[] rows = new int[r];
        int[] cols = new int[r];
        int[] squares = new int[r];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 0) continue;
                // each pos is multiple of 2 of board[i][j]
                int pos = 1 << board[i][j];
                // each number in rows should be unique multiple of 2
                // AND(&) operator between two numbers where both the numbers are multiple of 2 gives 0
                if ((rows[i] & pos) > 0) return false;
                rows[i] |= pos;

                if ((cols[j] & pos) > 0) return false;
                cols[j] |= pos;

                int squareKey = (i/3) * 3 + (j/3);
                if ((squares[squareKey] & pos) > 0) return false;
                squares[squareKey] |= pos;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[][] board = {
                {"1","2",".",".","3",".",".",".","."},
                {"4",".",".","5",".",".",".",".","."},
                {".","9","8",".",".",".",".",".","3"},
                {"5",".",".",".","6",".",".",".","4"},
                {".",".",".","8",".","3",".",".","5"},
                {"7",".",".",".","2",".",".",".","6"},
                {".",".",".",".",".",".","2",".","."},
                {".",".",".","4","1","9",".",".","8"},
                {".",".",".",".","8",".",".","7","9"}
        };

        int[][] board1 = {{7, 9, 2, 1, 5, 4, 3, 8, 6},
                {6, 4, 3, 8, 2, 7, 1, 5, 9},
                {8, 5, 1, 3, 9, 6, 7, 2, 4},
                {2, 6, 5, 9, 7, 3, 8, 4, 1},
                {4, 8, 9, 5, 6, 1, 2, 7, 3},
                {3, 1, 7, 4, 8, 2, 9, 6, 5},
                {1, 3, 6, 7, 4, 8, 5, 9, 2},
                {9, 7, 4, 2, 1, 5, 6, 3, 8},
                {5, 2, 8, 6, 3, 9, 4, 1, 7}};

        boolean result = validSudoku2(board1);
        System.out.println(result);

    }
}
