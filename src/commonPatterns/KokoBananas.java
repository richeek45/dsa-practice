package commonPatterns;
// 875. Koko Eating Bananas
// Return the minimum integer k such that she can eat all the bananas within h hours.

// h hours -> total, speed -> k bananas / hour
// 3 6 7 11

import java.util.Arrays;

public class KokoBananas {
    public static int bananas(int[] piles, int h) {
        Arrays.sort(piles);
        int left = 1, right = Arrays.stream(piles).max().getAsInt();
        int minSpeed = 0;
        while(left <= right) {
            int mid = (left + right) / 2;

            int totalTime = 0;
            for (int pile: piles) {
                totalTime += Math.ceil((double) pile / mid);
            }
            if (totalTime <= h) {
                right = mid - 1;
                minSpeed = mid;
            } else {
                left = mid + 1;
            }
        }
        return minSpeed;
    }

    public static void main(String[] args) {
        int[] piles1 = {3,6,7,11};
        int h1 = 8;
        int[] piles2 = {30,11,23,4,20};
        int h2 = 5;
        int[] piles3 = {30,11,23,4,20};
        int h3 = 6;
        int speed = bananas(piles3, h3);
        System.out.println(speed);
    }
}
