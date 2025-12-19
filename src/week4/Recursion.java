package week4;

public class Recursion {

    public static void main(String[] args) {

        System.out.println(factorial(5));
        // Output: 120

        printNumbers(5, 1);
        // Output: 1 2 3 4 5

        printNumberReverseOrder(5);
        // Output: 5 4 3 2 1

        System.out.println(printSum(5));
        // Output: 15

        System.out.println(fibo(6));
        // Output: 8

        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(checkSorted(arr, 0));
        // Output: true

        int[] arr2 = {5, 3, 7, 3, 9, 3};
        System.out.println(findFirstOcc(arr2, 3, 0));
        // Output: 1

        System.out.println(findLastOcc(arr2, 3, 0));
        // Output: 5

        System.out.println(powerOfX(2, 5));
        // Output: 32

        System.out.println(powerOfXOpti(2, 5));
        // Output: 32
    }

    // ---------------- FACTORIAL ----------------
    static int factorial(int n) {

        // ❌ Earlier: return n for n==0
        // 0! should be 1
        if (n == 0 || n == 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }

    // ---------------- PRINT 1 TO N ----------------
    static void printNumbers(int n, int i) {

        // ❌ Earlier: missing base condition → infinite recursion
        if (i > n) {
            return;
        }

        System.out.print(i + " ");
        printNumbers(n, i + 1);
    }

    // ---------------- PRINT N TO 1 ----------------
    static void printNumberReverseOrder(int n) {

        // ❌ Earlier: missing base condition → infinite recursion
        if (n == 0) {
            return;
        }

        System.out.print(n + " ");
        printNumberReverseOrder(n - 1);
    }

    // ---------------- SUM OF N NATURAL NUMBERS ----------------
    static int printSum(int n) {

        if (n == 1) {
            return 1;
        }

        return n + printSum(n - 1);
    }

    // ---------------- FIBONACCI ----------------
    static int fibo(int n) {

        if (n == 0 || n == 1) {
            return n;
        }

        return fibo(n - 1) + fibo(n - 2);
    }

    // ---------------- CHECK SORTED ARRAY ----------------
    static boolean checkSorted(int[] arr, int i) {

        if (i == arr.length - 1) {
            return true;
        }

        if (arr[i] > arr[i + 1]) {
            return false;
        }

        return checkSorted(arr, i + 1);
    }

    // ---------------- FIRST OCCURRENCE ----------------
    static int findFirstOcc(int[] arr, int key, int i) {

        if (i == arr.length) {
            return -1;
        }

        if (arr[i] == key) {
            return i;
        }

        return findFirstOcc(arr, key, i + 1);
    }

    // ---------------- LAST OCCURRENCE ----------------
    static int findLastOcc(int[] arr, int key, int i) {

        // ❌ Confusion: how to get last occurrence?
        // Trick: go till end first, then check while returning

        if (i == arr.length) {
            return -1;
        }

        int isFound = findLastOcc(arr, key, i + 1);

        if (isFound == -1 && arr[i] == key) {
            return i;
        }

        return isFound;
    }

    // ---------------- POWER x^n (SIMPLE) ----------------
    static int powerOfX(int x, int n) {

        if (n == 0) {
            return 1;
        }

        return x * powerOfX(x, n - 1);
    }

    // ---------------- POWER x^n (OPTIMIZED) ----------------
    static int powerOfXOpti(int x, int n) {

        if (n == 0) {
            return 1;
        }

        // ❌ Earlier: powerOfXOpti called twice (inefficient)
        int half = powerOfXOpti(x, n / 2);

        int halfPower = half * half;

        if (n % 2 != 0) {
            halfPower = x * halfPower;
        }

        return halfPower;
    }
}
