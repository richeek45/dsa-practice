package matrix;

public class PrintSortedMatrix {
    // Given an n x n matrix, where every row and column is sorted in non-decreasing order.
    // Print all elements of the matrix in sorted order.

    static final int INF = Integer.MAX_VALUE;
    private static void youngify(int[][] matrix, int i, int j) {
        int N = matrix.length;
        int downValue = (i + 1) < N ? matrix[i+1][j] : INF;
        int rightValue = (j + 1) < N ? matrix[i][j+1] : INF;
        // if matrix[i][j] is at the down right corner
        if (downValue == INF && rightValue == INF) {
            return;
        }

        if (downValue < rightValue) {
            matrix[i][j] = downValue;
            matrix[i+1][j] = INF;
            youngify(matrix, i+1, j);
        } else {
            matrix[i][j] = rightValue;
            matrix[i][j+1] = INF;
            youngify(matrix, i, j+1);
        }
    }

    private static int extractMin(int[][] matrix) {
        int ret = matrix[0][0];
        youngify(matrix, 0, 0);
        return ret;
    }
    static void printSortedMatrix(int[][] matrix) {
        // Time complexity of extract minimum is O(N) and it is called O(N2) times.
        int N = matrix.length;
        for (int i = 0; i < N*N; i++) {
            int val = extractMin(matrix);
            System.out.print(val + " ");
        }
    }


    static void printSortedMatrix2(int[][] matrix) {
        // Using MinHeap O(N2LogN)
        int N = matrix.length;
        // Creating an array of elements of first col of every row for minHeap
        MinHeapNode[] harr = new MinHeapNode[N];
        for (int i = 0; i < N; i++) {
            harr[i] = new MinHeapNode(matrix[i][0], i, 1);
        }
        MinHeap heap = new MinHeap(harr, N);

        for (int count = 0; count < N * N; count++) {
            MinHeapNode root = heap.getMin();
            // add the next element of column, increase the column index and replace root
            if (root.j < N) {
                root.element = matrix[root.i][root.j];
                root.j = root.j + 1;
            } else {
                root.element = Integer.MAX_VALUE;
            }
            heap.replaceMin(root);
        }
    }

    public static void main(String[] args) {
        int mat[][] = { {10, 20, 30, 40},
                {15, 25, 35, 45},
                {27, 29, 37, 48},
                {32, 33, 39, 50},
        };
//        printSortedMatrix(mat);
        printSortedMatrix2(mat);
    }
}

// Creating a node with element and i, j indices of row and col
class MinHeapNode {
    int element;
    int i;
    int j;
    MinHeapNode(int element, int i, int j) {
        this.element = element;
        this.i = i;
        this.j = j;
    }
}

class MinHeap {
    MinHeapNode[] harr;
    int heapSize;
    MinHeap(MinHeapNode[] arr, int size) {
        harr = arr;
        heapSize = size;
        int i = (heapSize - 1) / 2;
        while (i >= 0) {
            minHeapify(i);
            i--;
        }
    }
    void minHeapify(int i) {
        int l = getLeftChild(i);
        int r = getRightChild(i);
        int smallest = i;
        if (l < heapSize && harr[l].element < harr[i].element) {
            smallest = l;
        }
        if (r < heapSize && harr[r].element < harr[smallest].element) {
            smallest = r;
        }

        if (smallest != i) {
            // swap the values
            MinHeapNode temp = harr[i];
            harr[i] = harr[smallest];
            harr[smallest] = temp;
            minHeapify(smallest);
        }

    }
    int getLeftChild(int i) { return 2 * i + 1; }
    int getRightChild(int i) { return 2 * i + 2; }
    MinHeapNode getMin() { return harr[0]; }
    void replaceMin(MinHeapNode val) {
        harr[0] = val;
        minHeapify(0);
    }
}
