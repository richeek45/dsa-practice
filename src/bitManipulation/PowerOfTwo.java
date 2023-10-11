package bitManipulation;

public class PowerOfTwo {
    static boolean powerOfTwo(int value) {
        // O(1)
        if (value == 0) {
            return false;
        }

        double a = Math.log(value) / Math.log(2);
        return (int)Math.ceil(a) == (int)Math.floor(a);
    }

    static boolean powerOfTwo2(int value) {
        while(value != 1) {
            if((value & 1) == 1) {
                return false;
            }
            value = value >> 1;
        }

        return true;
    }

    static boolean powerOfTwo3(int value) {
        int countBits = 0;
        while(value > 0) {

            if ((value & 1) == 1) {
                countBits++;
            }
            value = value >> 1;
        }

        if (countBits > 1) {
            return false;
        }
        return true;
    }

    static boolean powerOfTwo4(int value) {
        // x & (x-1) will only be 0 for power of two values
        return ((value != 0) & (value & (value-1)) == 0);
    }

    static boolean powerOfTwo5(int value) {
        return (value & -value) == value;
    }


    public static void main(String[] args) {
        int value = 32;
        boolean check = powerOfTwo5(value);
        System.out.println(check);

    }
}
