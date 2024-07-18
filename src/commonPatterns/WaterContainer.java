package commonPatterns;
// You are given an integer array height of length n.
// There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
// Find two lines that together with the x-axis form a container, such that the container contains the most water.
//Return the maximum amount of water a container can store.
//Notice that you may not slant the container.
//Input: height = [1,8,6,2,5,4,8,3,7], Output: 49


public class WaterContainer {

    public static int maxWaterContainer(int[] height) {
        int left = 0, right = height.length - 1;
        int area = 0;

        while(left < right) {
            int length = right - left;

            area = Math.max(area, Math.min(height[left], height[right]) * length);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return area;

    }

    public static  void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        int area = maxWaterContainer(height);
        System.out.println(area);

    }
}
