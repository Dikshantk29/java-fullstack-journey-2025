package week5;

import java.util.Arrays;

class Main {

    public static void main(String[] args) {

        // ---------- MERGE SORT TEST CASES ----------
        int[] arr1 = {5, 3, 8, 4, 2};
        mergeSort(arr1, 0, arr1.length - 1);
        System.out.println("Merge Sort TC1: " + Arrays.toString(arr1));

        // ---------- QUICK SORT TEST CASES ----------

        // Test Case 1: Normal unsorted
        int[] q1 = {5, 3, 8, 4, 2};
        quickSort(q1, 0, q1.length - 1);
        System.out.println("Quick Sort TC1: " + Arrays.toString(q1));

        // Test Case 2: Already sorted
        int[] q2 = {1, 2, 3, 4, 5};
        quickSort(q2, 0, q2.length - 1);
        System.out.println("Quick Sort TC2: " + Arrays.toString(q2));

        // Test Case 3: Reverse sorted
        int[] q3 = {9, 7, 5, 3, 1};
        quickSort(q3, 0, q3.length - 1);
        System.out.println("Quick Sort TC3: " + Arrays.toString(q3));

        // Test Case 4: Duplicates
        int[] q4 = {4, 2, 2, 8, 4};
        quickSort(q4, 0, q4.length - 1);
        System.out.println("Quick Sort TC4: " + Arrays.toString(q4));

        // Test Case 5: Single element
        int[] q5 = {10};
        quickSort(q5, 0, q5.length - 1);
        System.out.println("Quick Sort TC5: " + Arrays.toString(q5));

        // Test Case 6: Negative numbers
        int[] q6 = {-3, -1, -7, -4};
        quickSort(q6, 0, q6.length - 1);
        System.out.println("Quick Sort TC6: " + Arrays.toString(q6));
    }

    // ---------------- MERGE SORT ----------------
    static void mergeSort(int arr[], int si, int ei) {
        if (si >= ei) return;

        int mid = si + (ei - si) / 2;
        mergeSort(arr, si, mid);
        mergeSort(arr, mid + 1, ei);
        merge(arr, si, mid, ei);
    }

    static void merge(int arr[], int si, int mid, int ei) {
        int[] temp = new int[ei - si + 1];
        int i = si, j = mid + 1, k = 0;

        while (i <= mid && j <= ei) {
            if (arr[i] < arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= ei) temp[k++] = arr[j++];

        for (int x = 0; x < temp.length; x++) {
            arr[si + x] = temp[x];
        }
    }

    // ---------------- QUICK SORT ----------------
    static void quickSort(int[] arr, int si, int ei) {
        if (si >= ei) return;

        int pivotIndex = quick(arr, si, ei);
        quickSort(arr, si, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, ei);
    }

    static int quick(int[] arr, int si, int ei) {
        int pivot = arr[ei];
        int i = si - 1;

        for (int j = si; j < ei; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }

        i++;
        int temp = arr[ei];
        arr[ei] = arr[i];
        arr[i] = temp;

        return i;
    }
}
