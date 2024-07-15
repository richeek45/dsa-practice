package commonPatterns;

// You are given an m x n integer matrix: Each row is sorted in non-decreasing order.
//The first integer of each row is greater than the last integer of the previous row.
//Given an integer target, return true if target is in matrix or false otherwise.
//
//You must write a solution in O(log(m * n)) time complexity.
// Input: matrix = [
// [1,3,5,7],
// [10,11,16,20],
// [23,30,34,60]], target = 3, Output: true

//Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//Output: false

public class Search2DMatrix {

    // O(logm + logn)
    public static boolean searchMatrix2(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int i = 0, j = row - 1;
        int midRow = -1;

        // finding the row with binary search
        while(i <= j) {
            midRow = i + (j - i) / 2;

            if (matrix[midRow][col-1] < target) {
                i = midRow + 1;
            } else if (matrix[midRow][0] > target) {
                j = midRow - 1;
            }
            else {
                // target is in this row
                break;
            }
        }

        if (!(i <= j)) {
            return false;
        }

        // finding the col with binary search - 1D
        i = 0;
        j = col - 1;
        int midCol = -1;
        while(i <= j) {
            midCol = i + (j - i) / 2;
            int dest = matrix[midRow][midCol];
            if (dest == target) {
                return true;
            }
            if (dest < target) {
                i = midCol + 1;
            } else {
                j = midCol - 1;
            }
        }

        return false;
    }

    // O(m + n)
    public static boolean searchMatrix(int[][] matrix, int target) {
        int i = 0, j = 0;
        while(i < matrix.length && j < matrix[0].length) {
            int value = matrix[i][j];
            if (value == target) {
                return true;
            }

            if (i == matrix.length - 1) {
                j++;
                continue;
            }

            if (j == matrix[0].length - 1) {
                i++;
                continue;
            }

            if (Math.max(matrix[i+1][j], matrix[i][j+1]) <= target) {
                i++;
            } else {
                j++;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] matrix1 = new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target1 = 30;

        int[][] matrix2 = {{1,3,5,7}, {10,11,16,20}, {23,30,34,60}};
        int target2 = 13;

        int[][] matrix3 = {{1,3,5,7}, {10,11,16,20}, {23,30,34,50}};
        int target3 = 10;

        boolean isTargetFound = searchMatrix2(matrix1, target1);
        System.out.println(isTargetFound);
    }
}
