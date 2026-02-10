

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Entry point
    }

    // sub array sum  with given sum
    static int[] subarraySumSlidingWindow(int[] arr, int target) {
        if (arr == null || arr.length == 0) return new int[]{-1};

        int start = 0, sum = 0;

        for (int end = 0; end < arr.length; end++) {
            sum += arr[end];

            while (sum > target && start <= end) {
                sum -= arr[start++];
            }

            if (sum == target) {
                return new int[]{start, end};
            }
        }
        return new int[]{-1};
    }



    //check array is sorted or not
    static boolean isSorted(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    //medium - kadens alogorithms
    static int kadensAlgo(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0; // Or throw an IllegalArgumentException
        }

        int maxSoFar = nums[0];
        int currentMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            maxSoFar = Math.max(maxSoFar, currentMax);
        }
        return maxSoFar;
    }
    // ===========================================

    //check array is sorted or not
    static boolean Alog(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true; // An empty or single-element array is considered sorted
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return false;
            }
        }
        return true;
    }


    //check array is sorted or not
    static boolean isSortedAlgo(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return false;
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    // binary search
    static int binarySearchAlog(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return -1;
        }


        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

        }
        return -1;

    }


    // remove duplicates from array
    static int[] removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];

        }
        HashSet<Integer> seen = new LinkedHashSet<>();
        for (int num : nums) {
            seen.add(num);
        }

        int[] result = new int[seen.size()];
        int i = 0;
        for (int num : seen) {
            result[i++] = num;
        }
        return result;
    }

    /**
     * Checks whether two numbers in the array sum up to the target.
     */
    public static boolean twoSum(int[] nums, int target) {

        if (nums == null || nums.length < 2) {
            return false;
        }

        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            int required = target - num;

            if (seen.contains(required)) {
                return true;
            }
            seen.add(num);
        }

        return false;
    }

    /**
     * Performs linear search and prints index if found.
     *
     * Time Complexity: O(n)
     */
    public static void linearSearch(int[] nums, int target) {

        if (nums == null) {
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                System.out.println("Found at index: " + i);
            }
        }
    }

    /**
     * Binary Search (array must be sorted).
     *
     * Time Complexity: O(log n)
     */
    public static int binarySearch(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    /**
     * Merge Sort implementation.
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     */
    public static void mergeSort(int[] nums, int startIndex, int endIndex) {

        if (startIndex >= endIndex) {
            return;
        }

        int mid = startIndex + (endIndex - startIndex) / 2;

        mergeSort(nums, startIndex, mid);
        mergeSort(nums, mid + 1, endIndex);

        merge(nums, startIndex, mid, endIndex);
    }

    private static void merge(int[] nums, int startIndex, int mid, int endIndex) {

        int[] temp = new int[endIndex - startIndex + 1];

        int left = startIndex;
        int right = mid + 1;
        int index = 0;

        while (left <= mid && right <= endIndex) {
            if (nums[left] <= nums[right]) {
                temp[index++] = nums[left++];
            } else {
                temp[index++] = nums[right++];
            }
        }

        while (left <= mid) {
            temp[index++] = nums[left++];
        }

        while (right <= endIndex) {
            temp[index++] = nums[right++];
        }

        for (int i = 0; i < temp.length; i++) {
            nums[startIndex + i] = temp[i];
        }
        

        public static sum(){

        }
    }
}
