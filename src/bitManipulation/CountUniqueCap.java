package bitManipulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Vector;

@SuppressWarnings("unchecked")
public class CountUniqueCap {
    // There are 100 different types of caps each having a unique id from 1 to 100.
    // Also, there are ‘n’ persons each having a collection of a variable number of caps.
    // One day all of these persons decide to go in a party wearing a cap but to look unique
    // they decided that none of them will wear the same type of cap.
    // So, count the total number of arrangements or ways such that none of them is wearing the same type of cap.
    // Constraints: 1 <= n <= 10
    static final int MOD = 1000000007;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // for 100 caps for each ith cap in the array contains vector of people having the same cap.
    static Vector<Integer>[] capList = new Vector[101];
    // for max 10 person 2^10 possible variations of bits and 100 for the unique caps
    static int[][] dp = new int[1024][101];
    static int allBitsSet;

    public static int countWaysUtil(int mask, int cap) {
        // if all persons are wearing a cap then we are done hence return 1
        if(allBitsSet == mask) {
            return 1;
        }

        // No more caps left to find variations
        if(cap > 100) return 0;

        // if we already have solved the sub-problem return the solution
        if(dp[mask][cap] != -1) return dp[mask][cap];

        int ways = countWaysUtil(mask, cap+1);

        int len = capList[cap].size();
        for(int j = 0; j < len; j++) {
            // check if person is already wearing a cap from the mask set bit of index person
            int person = capList[cap].get(j);
            if ((mask & (1 << person)) == 1) {
                continue;
            }
            // make the person wear the cap
            // Else assign him this cap and recur for remaining caps with
            // new updated mask vector
            ways += countWaysUtil((mask | (1 << person)), cap+1);

        }

        // save the result and return it
        return dp[mask][cap] = (int) ways;
    }


    public static void countWays(int N) throws IOException {
        String str;
        String[] split;

        for(int person = 0; person < N; person++) {
            str = br.readLine();
            split = str.split(" ");

            // for each cap number in split[] adding the person to the caplist array of index j
            for(int j = 0; j < split.length; j++) {
                int cap = Integer.parseInt(split[j]);
                capList[cap].add(person);
            }
        }

        allBitsSet = (1 << N) - 1;
        for(int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int mask = 0; // storing the set bits for combination of people wearing a cap
        int cap = 0; // cap index
        int ways = countWaysUtil(mask, cap);
        System.out.println(ways);
    }

    public static void main(String[] args) throws IOException {
        int N; // number of people

        for(int i = 0; i < capList.length; i++) {
            capList[i] = new Vector<>();
        }

        System.out.print("Number of people = ");
        N = Integer.parseInt(br.readLine());
        countWays(N);
    }
}
