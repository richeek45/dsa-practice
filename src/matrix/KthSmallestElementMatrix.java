package matrix;

import java.util.PriorityQueue;

public class KthSmallestElementMatrix {
    // Kth smallest element in a row-wise and column-wise sorted 2D array
    static final int INF = Integer.MAX_VALUE;
    private static void youngify(int[][] matrix, int i, int j) {
        int N = matrix.length;
        int downVal = (i + 1) < N ? matrix[i+1][j] : INF;
        int rightVal = (j + 1) < N ? matrix[i][j+1] : INF;
        if (downVal == INF && rightVal == INF) {
            return;
        }

        if (downVal < rightVal) {
            matrix[i][j] = downVal;
            matrix[i+1][j] = INF;
            youngify(matrix, i+1, j);
        } else {
            matrix[i][j] = rightVal;
            matrix[i][j+1] = INF;
            youngify(matrix, i, j+1);
        }
    }
    private static int extractMin(int[][] matrix) {
        int res = matrix[0][0];
        youngify(matrix, 0, 0);
        return res;
    }
    static void kthSmallest(int[][] matrix, int k) {
        // Kth smallest element in a sorted 1D array of m*n elements
        // meaning k-1 index in the sorted array
        // we can use youngify method
        int row = matrix.length;
        for (int i = 0; i < k; i++) {
            int res = extractMin(matrix);
            System.out.print(res + ",");
        }
    }

    static void kthSmallest2(int[][] matrix, int k) {
        // O(N^2* log(N^2))
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int M = matrix.length, N = matrix[0].length;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                pq.add(matrix[i][j]);
            }
        }

        int count = 0;
        while (count < k && !pq.isEmpty()) {
            int temp = pq.poll();
            count++;
            if (count == k) {
                System.out.println(temp);
                break;
            }

        }
    }

    private static class HeapNode {
        int val;
        int row;
        int col;
        HeapNode(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
    }

    private static void minHeapify(HeapNode[] harr, int heapSize, int i) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int min = i;
        if (l < heapSize && harr[l].val < harr[i].val) {
            min = l;
        }
        if (r < heapSize && harr[r].val < harr[l].val) {
            min = r;
        }

        if (min != i) {
            HeapNode temp = harr[i];
            harr[i] = harr[min];
            harr[min] = temp;
            minHeapify(harr, heapSize, min);
        }

    }

    static void kthSmallest3(int[][] matrix, int k) {
        int N = matrix[0].length;
        if (k > N*N || k < 0) {
            return;
        }
        // Using Min-Heap
        HeapNode[] harr = new HeapNode[N];
        // First adding all the nodes of the first row using heap array
        for (int i = 0; i < N; i++) {
            harr[i] = new HeapNode(matrix[0][i], 0, i);
        }
        // Initialize a empty node
        HeapNode hr = new HeapNode(0,0,0);
        // running the loop k times to get the kth minimum values
        for(int i = 1; i <= k; i++) {
            // taking the first element in the array which is root
            hr = harr[0];

            int nextVal = hr.row < N - 1 ? matrix[hr.row + 1][hr.col] : Integer.MAX_VALUE;
            // modifying harr[0] -> root element with the next element
            harr[0] = new HeapNode(nextVal, hr.row + 1, hr.col);
            // minHeapify the root element after changing the root
            minHeapify(harr, N, 0);
        }

        System.out.println(hr.val);
    }

    private static int getGreaterThanOrEqualElement(int num, int[][] matrix) {
        int N = matrix.length;
        int ans = 0; // counting the elements lesser than or equal to the num
        // looping through the rows
        for (int i = 0; i < N; i++) {
            // if the num is less than the first element of the col of i row then send ans count till now
            if (num < matrix[i][0]) {
                return ans;
            }
            // if num is greater than the last element of the row then add number of elements in the row to the ans
            if (num > matrix[i][N-1]) {
                ans += N;
            }

            // case where the num is between first and last element in the row
            int greaterThan = 0; // count of the col elements
            // this loop does logN complexity
            for (int jump = N/2; jump >= 1; jump /= 2) {
                while ((greaterThan + jump) < N && matrix[i][greaterThan + jump] <= num) {
                    greaterThan += jump;
                }
            }
            // col * rows tranversed before + greaterThan has index of element close to num + 1 for element number
            ans += greaterThan + 1;
        }

        return ans;
    }

    static void kthSmallest4(int[][] matrix, int k) {
        int N = matrix.length;
        int low = matrix[0][0];
        int high = matrix[N-1][N-1];

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int greaterThanEqualMid = getGreaterThanOrEqualElement(mid, matrix);
            if (greaterThanEqualMid < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println(low);
    }

    public static void main(String[] args) {
        int mat[][] = { { 10, 20, 30, 40 },
                        { 15, 25, 35, 45 },
                        { 25, 29, 37, 48 },
                        { 32, 33, 39, 50 } };
//        kthSmallest(mat, 7);
//        kthSmallest2(mat, 7);
//        kthSmallest3(mat, 7);
        kthSmallest4(mat, 7);
    }
}
