package week2;
//revision
public class Examples {

    public static void main(String[] args) {

        System.out.println(checkEvenOrOdd(12));
        // Output: even

        System.out.println(setIthBit(10, 1));
        // Input: 10 (1010), i = 1
        // Output: 10 (bit already set)

        System.out.println(clearIthBit(10, 1));
        // Input: 10 (1010), i = 1
        // Output: 8 (1000)

        System.out.println(updateIthBit(10, 2, 1));
        // Input: 10 (1010), i = 2, bit = 1
        // Output: 14 (1110)

        System.out.println(clearLastIthBit(15, 2));
        // Input: 15 (1111), i = 2
        // Output: 12 (1100)

        System.out.println(clearRangeOfBit(15, 1, 2));
        // Input: 15 (1111), i = 1, j = 2
        // Output: 9 (1001)

        checkNumberIsPowerOfTwoOrNot(16);
        // Output: Power of two
    }

    // ---------------- EVEN / ODD ----------------
    static String checkEvenOrOdd(int num) {
        // Correct logic ✔
        if ((num & 1) == 1) {
            return "odd";
        } else {
            return "even";
        }
    }

    // ---------------- SET i-th BIT ----------------
    static int setIthBit(int num, int i) {

        // ❌ Earlier: num & (1<<i)
        // AND does NOT set a bit, it checks a bit

        // ✅ Correct: OR sets the bit
        int ans = num | (1 << i);
        return ans;
    }

    // ---------------- CLEAR i-th BIT ----------------
    static int clearIthBit(int num, int i) {

        // ❌ Earlier: XOR toggles the bit (0→1, 1→0)
        // But we want to ALWAYS clear

        // ✅ Correct: AND with NOT mask
        int ans = num & ~(1 << i);
        return ans;
    }

    // ---------------- UPDATE i-th BIT ----------------
    static int updateIthBit(int num, int i, int bit) {

        // ❌ Earlier:
        // - no bit value parameter
        // - function always returned 0
        // - set/clear result not stored

        // Step 1: clear ith bit
        num = num & ~(1 << i);

        // Step 2: set ith bit if bit == 1
        num = num | (bit << i);

        return num;
    }

    // ---------------- CLEAR LAST i BITS ----------------
    static int clearLastIthBit(int num, int i) {

        // ❌ Doubt: how to implement
        // Example: num = 15 (1111), i = 2
        // Mask = ~((1<<i)-1) → ~0011 = 1100

        int mask = ~((1 << i) - 1);
        return num & mask;
    }

    // ---------------- CLEAR RANGE OF BITS ----------------
    static int clearRangeOfBit(int num, int i, int j) {

        // ❌ Doubt here
        // Example: num = 15 (1111), i=1, j=2
        // Left mask  = (~0) << (j+1) → 111000
        // Right mask = (1<<i)-1      → 000001
        // OR both → 111001

        int leftMask = (~0) << (j + 1);
        int rightMask = (1 << i) - 1;
        int mask = leftMask | rightMask;

        return num & mask;
    }

    // ---------------- POWER OF TWO ----------------
    static void checkNumberIsPowerOfTwoOrNot(int num) {

        // ❌ Doubt here
        // Logic:
        // Power of 2 → only ONE set bit
        // n & (n-1) == 0

        if (num > 0 && (num & (num - 1)) == 0) {
            System.out.println("Power of two");
        } else {
            System.out.println("Not power of two");
        }
    }
}
