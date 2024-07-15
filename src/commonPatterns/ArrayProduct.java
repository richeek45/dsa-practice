package commonPatterns;
// 238. Product of Array Except Self
// Input: nums = [1,2,3,4], Output: [24,12,8,6]
// Input: nums = [-1,1,0,-3,3], Output: [0,0,9,0,0]

import java.util.ArrayList;
import java.util.List;
//[1, 1, 2, 6]

public class ArrayProduct {

    public static int[] arrayProduct(int[] nums) {
        int[] res = new int[nums.length];
        int prefix = 1, postfix = 1;

        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = prefix * nums[i-1];
            prefix *= res[i];
        }

        for (int i = nums.length - 1; i >=  1; i--) {
            res[i] = postfix * res[i-1];
            postfix *= nums[i];
        }

        for (int val : res) {
            System.out.print(val + " ");
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4};
        arrayProduct(nums);
    }

}
