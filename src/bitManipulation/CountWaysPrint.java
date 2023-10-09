package bitManipulation;

public class CountWaysPrint {
    // Count number of unique ways to paint a N x 3 grid
    // Given an integer N, the task is to paint a grid of size N x 3 using colors Red, Yellow, or Green while making
    // such that no pair of adjacent cells has the same color. Print the number of distinct ways in which it is possible
    // Count of ways to color current row having ends of same color SN+1 = 3 * SN + 2DN
    //Count of ways to color current row having ends of different colors DN+1 = 2 * SN + 2DN
    //Total number of ways to paint all N rows is equal to the sum of SN and DN.
    // 1. Red, Yellow, Red,  2. Yellow, Red, Yellow, 3.Green, Red, Yellow,     4.Red, Yellow, Green
    // 5.Yellow, Red, Green, 6.Green, Red, Green,    7.Red, Green, Red,        8.Yellow, Green, Red
    // 9.Green, Yellow, Red, 10.Red, Green, Yellow,  11.Yellow, Green, Yellow, 12.Green, Yellow, Green

    // Explanation: https://medium.com/algorithm-and-datastructure/number-of-ways-to-paint-n-3-grid-170b04213d46
    // https://www.geeksforgeeks.org/count-number-of-unique-ways-to-paint-a-n-x-3-grid/

    public static void countWays(int N) {
        // Count for row 0
        long same = 6;
        long diff = 6;

        // for row 1 - N
        for(int i = 1; i < N; i++) {
            same = 3 * same + 2 * diff;
            diff = 2 * same + 2 * diff;
        }
        System.out.println(same + diff);
    }

    public static void main(String[] args){
        int N = 2;
        countWays(N);
    }
}
