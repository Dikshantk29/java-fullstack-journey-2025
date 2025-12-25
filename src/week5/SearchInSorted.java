package week5;


public class SearchInSorted {

    public static void main(String[] args) {

        // Test Case 1: Target present in right half
        int[] arr1 = {4, 5, 6, 7, 0, 1, 2, 3};
        int res1 = search(arr1, 0, 0, arr1.length - 1);
        System.out.println("Test Case 1 (target = 0): index = " + res1);

        // Test Case 2: Target present in left half
        int[] arr2 = {4, 5, 6, 7, 0, 1, 2, 3};
        int res2 = search(arr2, 6, 0, arr2.length - 1);
        System.out.println("Test Case 2 (target = 6): index = " + res2);

        // Test Case 3: Target is first element
        int[] arr3 = {4, 5, 6, 7, 0, 1, 2, 3};
        int res3 = search(arr3, 4, 0, arr3.length - 1);
        System.out.println("Test Case 3 (target = 4): index = " + res3);

        // Test Case 4: Target is last element
        int[] arr4 = {4, 5, 6, 7, 0, 1, 2, 3};
        int res4 = search(arr4, 3, 0, arr4.length - 1);
        System.out.println("Test Case 4 (target = 3): index = " + res4);

        // Test Case 5: Target not present
        int[] arr5 = {4, 5, 6, 7, 0, 1, 2, 3};
        int res5 = search(arr5, 10, 0, arr5.length - 1);
        System.out.println("Test Case 5 (target = 10): index = " + res5);

        // Test Case 6: Single element array
        int[] arr6 = {1};
        int res6 = search(arr6, 1, 0, arr6.length - 1);
        System.out.println("Test Case 6 (target = 1): index = " + res6);
    }

    static int search(int[] arr, int target, int si, int ei) {

        if (si > ei) {
            return -1;
        }
        int mid = si + (ei - si) / 2;


        if (arr[mid] == target) {
            return mid;
        }
        //left sorted
        if (arr[si] <= arr[mid]) {

            if (arr[si] <= target && target <= arr[mid]) {
                return search(arr, target, si, mid - 1);
            } else {

                return search(arr, target, mid + 1, ei);
            }
        } else {
            //case right
            if (arr[mid] <= target && target <= arr[ei]) {
                return search(arr, target, mid + 1, ei);
            } else {
                return search(arr, target, si, mid - 1);
            }
        }

    }


}
