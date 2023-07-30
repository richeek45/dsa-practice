package searchingAndSorting;

public class SquareRootInteger {
    // Given an integer X, find its square root. If X is not a perfect square, then return floor(√x).
    static void findSquareRoot(int val) {
        // Time Complexity: O(√X)
        int i = 1;
        while (i <= val) {
            if (i*i == val || (i*i < val && (i+1)*(i+1) > val)) {
                System.out.println(i);
                break;
            }
            i++;
        }
    }

    static void findSquareRootBinarySearch(int val) {
        int start = 0, end = val/2;
        int ans = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mid*mid == val) {
                ans = mid;
                break;
            }
            if (mid*mid < val) {
                start = mid + 1;
                ans = mid;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        int val = 12;
        findSquareRoot(val);
        findSquareRootBinarySearch(val);
    }
}
