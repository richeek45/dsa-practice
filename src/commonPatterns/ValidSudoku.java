package commonPatterns;

import java.util.*;

public class ValidSudoku {

    static boolean validSudoku(String[][] board) {
        Map<Integer, Set<String>> cols = new HashMap<Integer, Set<String>>();
        Map<Integer, Set<String>> rows = new HashMap<Integer, Set<String>>();
        Map<String, Set<String>> squares = new HashMap<String, Set<String>>();
        int r = board.length;
        int c = board[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (Objects.equals(board[i][j], ".")) continue;

                String squareKey = ((i/3) + "," + (j/3));

                if (rows.computeIfAbsent(i, k -> new HashSet<>()).contains(board[i][j])
                    || cols.computeIfAbsent(j, k -> new HashSet<>()).contains(board[i][j])
                    || squares.computeIfAbsent(squareKey, k -> new HashSet<>()).contains(board[i][j])
                ) {
                    return false;
                }

                rows.get(i).add(board[i][j]);
                cols.get(j).add(board[i][j]);
                squares.get(squareKey).add(board[i][j]);

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
