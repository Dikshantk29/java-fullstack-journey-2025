package week1;

public class Examples {

    public static void main(String[] args) {

        // ---------- TEST CASES ----------
        int[] arr = {1, 3, 4, 5, 4};
        linearSearch(arr, 4);
        // Expected Output:
        // Found at index: 2
        // Found at index: 4

        int[] sortedArr = {1, 3, 5, 7, 9};
        binarySearch(sortedArr, 7);
        // Expected Output:
        // Found at index: 3

        int[] subArr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        maxSubArraySum(subArr);
        // Expected Output:
        // Max sub array sum is 6

        prefixWaymaxSubArraySum(subArr);
        // Expected Output:
        // Max sub array sum is 6

        kadens(subArr);
        // Expected Output:
        // Max sum is: 6

        int[] height = {4, 2, 0, 6, 3, 2, 5};
        trappedWater(height);
        // Expected Output:
        // Trapped water level: 9

        int[] prices = {7, 1, 5, 3, 6, 4};
        buySellStocks(prices);
        // Expected Output:
        // Max profit is 5
    }

    // ---------------- LINEAR SEARCH ----------------
    static void linearSearch(int arr[], int target) {
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                System.out.println("Found at index: " + i);
                found = true;
            }
        }

        // ❌ Earlier: no message when element not found
        if (!found) {
            System.out.println("Element not found");
        }
    }

    // ---------------- BINARY SEARCH ----------------
    static void binarySearch(int arr[], int target) {
        int start = 0;
        int end = arr.length - 1;

        // ❌ Earlier: while(start < end) → misses last index
        while (start <= end) {

            // ❌ Earlier: mid = start + end (wrong, can overflow)
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                System.out.println("Found at index: " + mid);
                return; // ❌ Earlier: no return, loop continued
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        System.out.println("Element not found");
    }

    // ---------------- MAX SUBARRAY (BRUTE FORCE) ----------------
    static void maxSubArraySum(int arr[]) {
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {

                // ❌ Earlier: currSum was not reset for each subarray
                int currSum = 0;

                for (int k = i; k <= j; k++) {
                    // ❌ Earlier: k loop had j++ instead of k++
                    currSum += arr[k];
                }

                maxSum = Math.max(maxSum, currSum);
            }
        }

        System.out.println("Max sub array sum is " + maxSum);
    }

    // ---------------- PREFIX SUM MAX SUBARRAY ----------------
    static void prefixWaymaxSubArraySum(int arr[]) {
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;

        int[] prefix = new int[n];
        prefix[0] = arr[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                // ❌ Earlier: doubt here
                // ✅ Correct logic using prefix array
                int currSum = (i == 0)
                        ? prefix[j]
                        : prefix[j] - prefix[i - 1];

                maxSum = Math.max(maxSum, currSum);
            }
        }

        System.out.println("Max sub array sum is " + maxSum);
    }

    // ---------------- KADANE'S ALGORITHM ----------------
    static void kadens(int arr[]) {
        int curr = arr[0];
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {

            // ❌ Earlier: Math.max(curr, arr[i])
            // ✅ Correct: include current element in sum
            curr = Math.max(arr[i], curr + arr[i]);

            max = Math.max(max, curr);
        }

        System.out.println("Max sum is: " + max);
    }

    // ---------------- TRAPPED RAIN WATER ----------------
    static void trappedWater(int[] height) {
        int n = height.length;

        if (n <= 2) {
            System.out.println("Not possible");
            return;
        }

        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = height[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }

        right[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }

        int trappedWater = 0;
        for (int i = 0; i < n; i++) {

            // ❌ Earlier: trappedWater = (waterLevel - height[i])
            // ❌ This overwrote value every time
            trappedWater += Math.min(left[i], right[i]) - height[i];
        }

        System.out.println("Trapped water level: " + trappedWater);
    }

    // ---------------- BUY & SELL STOCKS ----------------
    static void buySellStocks(int prices[]) {
        int buyPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {

            if (prices[i] > buyPrice) {
                maxProfit = Math.max(maxProfit, prices[i] - buyPrice);
            } else {                // ❌ Earlier: buyPrice updated every time
                // ✅ Update only when lower price found
                buyPrice = prices[i];
            }
        }

        System.out.println("Max profit is " + maxProfit);
    }
}
