package commonPatterns;
// 33. Search in Rotated Sorted Array
// nums = [4,5,6,7,0,1,2], target = 0, Output: 4
// nums = [4,5,6,7,0,1,2], target = 3, Output: -1
// nums = [1], target = 0, Output = -1

public class SearchRotatedArray {
    public static int searchRotatedSortedArray(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right - left)/2;

            if (nums[mid] == target) {
                return mid;
            }

            // left sorted portion
            if (nums[left] <= nums[mid]) {
                if ((target > nums[mid]) || (target < nums[left])) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            // right sorted portion
            if (nums[mid] < nums[left]) {
                if ((target > nums[right]) || target < nums[mid]) {
                    right = mid - 1;
                } else  {
                    left = mid + 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        int index = searchRotatedSortedArray(nums, target);
        System.out.println(index);
    }
}
