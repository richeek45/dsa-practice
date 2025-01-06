package array;

public class MaxIndexDiff {

    static void findNextLargeValueIndexDiff(int[] arr) {
        // we want to find a value that is larger than current value and also farther in distance
        // ideal scenario is when both the index are at two ends of the array
        // I have an array 34, 8, 10, 3, 2, 80, 30, 33, 1, I am at 34 and I want 80
        // but I have to search through the numbers from the end to get to 80
        // we want to eliminate the steps traversing from the end because that is expensive or I am lazy
        // for 34 if we traverse from the end as soon as we find 80 we find the index diff and move to next iteration
        // for 8 we traverse again from the end to reach 33
        // for 10 we traverse again to get to 33 and so on for each traversal -> O(n^2)
        // is there any way to skip the traversal to find larger number greater than current number?

        // Monotoning decreasing array -> // 80, 80, 80, 80, 80, 80, 33, 33, 1
        // This tells us that every value before index i is smaller than maxFromEnd[i] value
        // perform a binary search on the new maxFromEnd array
        // how does this help
        // if I find a number larger than 34 during the search maxFromEnd[mid]
        // then we can conclude that our result is on the right side because maxFromEnd[mid] = max from end
        // so the actual max value could be on the left side or that exact value is the max value
        // else if I find a number smaller than 34 then we know the result is on the left side of the array
        // because that is the max value we have found so far from the end
        // Hence we use binary search to find the answer.

        // Here we create the array to store the max value from the end
        // the reason we want that is so that we don't need to search
        int len = arr.length;
        int[] maxFromEnd = new int[len + 1];
        for (int i = len-1; i >= 0; i--) {
            maxFromEnd[i] = Math.max(maxFromEnd[i+1], arr[i]);
        }

        int result = 0;
        for (int i = 0; i < len; i++) {
            int low = i+1, high = len - 1, ans = i;

            while (low <= high) {
                int mid = (low + high) / 2;

                if (maxFromEnd[mid] >= arr[i]) {
                    // max could be on the right side
                    // we still calculate because this could be the answer
                    ans = Math.max(ans, mid);
                    low = mid + 1;
                } else {
                    // definitely on the left cause max from end so far is smaller than current value
                    // no point searching beyond the value
                    high = mid - 1;
                }
            }
            result = Math.max(result, ans - i);
        }

        System.out.println(result);
    }


    public static void main(String[] args) {
        int[] arr = {34, 8, 10, 3, 2, 80, 30, 33, 1}; // 6  (j = 7, i = 1)
        findNextLargeValueIndexDiff(arr);

    }

}
