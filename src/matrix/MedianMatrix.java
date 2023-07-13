package matrix;

import java.util.*;

public class MedianMatrix {
    // Brute force
    static double findMedian(List<List<Integer>> matrix) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(0).size(); j++) {
                list.add(matrix.get(i).get(j));
            }
        }

        Collections.sort(list);

        int mid = list.size()/2;
        double median;
        if (list.size() % 2 == 0) {
            median = (list.get(mid - 1) + list.get(mid)) / 2.0;
        } else {
            median = list.get(mid);
        }

        return median;
    }

    // WIth BInary Search
    static int printMedian(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        // each row is sorted find min by comparing each row first element
        // and max by comparing each row last element
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] < min) {
                min = matrix[0][i];
            }
            if (matrix[i][col-1] > max) {
                max = matrix[i][col-1];
            }
        }

        System.out.println(min + " " + max);
        // finding the count of number smaller than mid
        int midCount =  (row * col + 1) / 2;
        while (min < max) {
            int mid = min + (max - min) / 2;
            int get = 0;
            int count = 0; // number of elements before the mid element
            // find count of elements smaller than or equal to mid
            for (int i = 0; i < row; i++) {
                // performing binary search in every row
                get = Arrays.binarySearch(matrix[i], mid);
                // element is not found in the row
                if (get < 0) {
                    // will get the -ve value of matrix[i] length
                    get = Math.abs(get) - 1; // getting the index of the insertion point
                    System.out.println("N" + get);
                } else {
                    // the mid element is found. If duplicate elements are there
                    // we go to the last index of the element
                    System.out.println("Y" + get);
                    while (matrix[i][get] == mid && get < matrix[i].length) {
                        get+= 1;
                    }
                }
                System.out.println(get + " " + count + ": i" + i + ": mid:" + mid);
                count += get;
            }

            // if count of number is less than midCount then change min to mid to search in the upper limit
            // and if count is greater than midCount then max = mid to search in the lower limit
            if (count < midCount) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        return min;

    }

    static int printMedian2(int[][] matrix) {
        // Matrix using min-heap
        PriorityQueue<Node> minHeap = new PriorityQueue<>(new Compare());
        int rowLen = matrix.length, colLen = matrix[0].length;
        int count = 0; // keeping the track of nodes visited
        int median = -1;
        for (int i = 0; i < rowLen; i++) {
            Node temp = new Node(matrix[i][0], i, 0);
            minHeap.add(temp);
        }

        int medianIndex = (rowLen * colLen) / 2;
        while (count <= medianIndex) {
            Node top = minHeap.poll();
            int row = top.row;
            int col = top.col;
            median = top.data;
            count++; // minNode visited

            if (col + 1 < colLen) {
                Node temp = new Node(matrix[row][col+1], row, col+1);
                minHeap.add(temp);
            }
        }
        return median;
    }

    public static void main(String[] args) {
//        List<List<Integer>> matrix1 = new ArrayList<List<Integer>>();
//        matrix1.add(new ArrayList<>(){{ add(1); add(3); add(5);}});
//        matrix1.add(new ArrayList<>(){{ add(2); add(6); add(9);}});
//        matrix1.add(new ArrayList<>(){{ add(3); add(6); add(9);}});
//        double median = findMedian(matrix1);
//        System.out.println(median);

        int[][] mat = { { 1, 3, 7 }, { 2, 5, 9 }, { 3, 6, 9 } };
//        int res = printMedian(mat);
        int res = printMedian2(mat);
        System.out.println(res);
    }
}

class Node {
    int data;
    int row;
    int col;

    Node(int d, int r, int c) {
        data = d;
        row = r;
        col = c;
    }
}

class Compare implements Comparator<Node> {
    public int compare(Node a, Node b) {
        return a.data > b.data ? 1 : -1;
    }
}