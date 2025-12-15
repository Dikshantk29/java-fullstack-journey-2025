package week4;

import java.util.Arrays;

public class Sorting {

    public static void main(String[] args) {

        int[] arr = {5, 4, 1, 3, 2};

        System.out.println("Bubble Sort     : " + Arrays.toString(bubbleSort(arr.clone())));
        System.out.println("Selection Sort  : " + Arrays.toString(selectionSort(arr.clone())));
        System.out.println("Insertion Sort  : " + Arrays.toString(insertionSort(arr.clone())));
        System.out.println("Counting Sort   : " + Arrays.toString(countingSort(arr.clone())));

        // Expected Output for all:
        // [1, 2, 3, 4, 5]
    }

    // ---------------- BUBBLE SORT ----------------
    static int[] bubbleSort(int[] arr) {
        int n = arr.length;

        // Correct implementation ✔
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    // ---------------- SELECTION SORT ----------------
    static int[] selectionSort(int[] arr) {
        int n = arr.length;

        // ❌ Earlier: loop ran till n
        // ✅ Should run till n-1 (last element already sorted)
        for (int i = 0; i < n - 1; i++) {

            int minPos = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[minPos] > arr[j]) {
                    minPos = j;
                }
            }

            // Swap
            int temp = arr[minPos];
            arr[minPos] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    // ---------------- INSERTION SORT ----------------
    static int[] insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int curr = arr[i];
            int prev = i - 1;

            // ❌ Earlier: arr[prev] > arr[curr]
            // ❌ WRONG because curr is a VALUE, not an index
            // ✅ Correct: compare with curr
            while (prev >= 0 && arr[prev] > curr) {
                arr[prev + 1] = arr[prev];
                prev--;
            }

            arr[prev + 1] = curr;
        }
        return arr;
    }

    // ---------------- COUNTING SORT ----------------
    static int[] countingSort(int[] arr) {
        int n = arr.length;

        int largest = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            largest = Math.max(arr[i], largest);
        }

        // ❌ Limitation: only works for non-negative numbers
        int[] count = new int[largest + 1];

        for (int i = 0; i < n; i++) {
            count[arr[i]]++;
        }

        int j = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr[j] = i;
                j++;
                count[i]--;
            }
        }
        return arr;
    }
}
