package commonPatterns;

// Input:153, Yes
//1*1*1 + 5*5*5 + 3*3*3 = 153
//Input: 120, No
//1*1*1 + 2*2*2 + 0*0*0 = 9
//Input: 1253, No
//1*1*1*1 + 2*2*2*2 + 5*5*5*5 + 3*3*3*3 = 723
//Input: 1634, Yes
//1*1*1*1 + 6*6*6*6 + 3*3*3*3 + 4*4*4*4 = 1634

public class ArmStrongNumber {
    static int order(int num) {
        int temp = num, n = 0;
        while(temp != 0) {
            temp /= 10;
            n += 1;
        }
        return n;
    }

    static int power(int num, int x) {
        if (x == 0) {
            return 1;
        }

        if (x % 2 == 0) {
            return power(num, x / 2) * power(num, x / 2);
        }
        return num * power(num, x / 2) * power(num, x / 2);
    }

    public static void armStrong(int num) {
        int n = order(num);
        int temp = num, sum = 0;
        while(temp != 0) {
            int digit = (temp % 10);
            sum += power(digit, n);
            temp = temp / 10;
        }

        boolean isArmStrong = num == sum;
        System.out.println(isArmStrong);
    }

    public static void main(String[] args) {
        int num1 = 1634;
        armStrong(num1);
    }
}
