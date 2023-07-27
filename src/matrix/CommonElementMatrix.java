package matrix;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CommonElementMatrix {
    // Given an m x n matrix, find all common elements present in all rows in O(mn) time and one traversal of matrix.
    static void findCommonInAllRows(int[][] matrix) {
        ArrayList<Integer> elements = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        int M = matrix[0].length;
        int N = matrix.length;
        for (int i = 0; i < M; i++) {
            map.put(matrix[0][i], 1);
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map.containsKey(matrix[i][j]) && map.get(matrix[i][j]) == i) {
                    map.put(matrix[i][j], i + 1);

                    if (i == N - 1) {
                        System.out.print(matrix[i][j] + " ");
                    }

                }


            }
        }

    }


    public static void main(String[] args) {
        int[][] mat = { {1, 2, 1, 4, 8},
                        {3, 7, 8, 5, 1},
                        {8, 7, 7, 3, 1},
                        {8, 1, 2, 7, 9},
                     };
        findCommonInAllRows(mat);
    }
}
