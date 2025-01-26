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

        boolean result = validSudoku(board);
        System.out.println(result);

    }
}
