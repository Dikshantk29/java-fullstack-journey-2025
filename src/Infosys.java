import java.util.*;

class Infosys {
    public static void main(String[] args) {

    }

    //1.kadens algorithm
    public static int kadensAlgo(int[] nums) {
        int n = nums.length;


        //edeg case
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int curr = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < n; i++) {
            curr = Math.max(nums[i], nums[i] + curr);
            maxSum = Math.max(maxSum, curr);
        }

        return maxSum;
    }

    //2.subarray with give sum
    public static int[] subArrayWithGivenSum(int[] nums, int target) {
        //brute force with n^2 approach

        //optimal
        int left = 0;
        int sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            // Shrink window if sum exceeds target🌟🌟
            while (sum > target && left <= right) {
                sum -= nums[left];
                left++;

            }
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            }


        }
        return new int[]{-1};


    }

    //3.Count Frequencies in an Array
    public static List<List<Integer>> CountFreq(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        //count freq
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

        }
        //sort the key
        List<Integer> keys = new ArrayList<>(freqMap.keySet());
        Collections.sort(keys);

        //store in result
        List<List<Integer>> result = new ArrayList<>();

        for (int key : keys) {
            result.add(Arrays.asList(key, freqMap.get(key)));
        }


        return result;
    }

    //4.Merge Two Sorted Arrays (Without Extra Space)
    public static void merge(int[] a, int[] b, int n, int m) {
        int lp = n - 1;//last index of a[]
        int rp = 0;//first index ofb[]

        while (lp >= 0 && rp < m) {
            if (a[lp] > b[rp]) {
                int temp = a[lp];
                a[lp] = b[rp];
                b[rp] = temp;
            }
            lp--;
            rp++;
        }
        //sort
        Arrays.sort(a);
        Arrays.sort(b);
    }

    //5.Longest Palindromic Substring📍📍📍
    public static String LPS(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }

        String lps = "";

        for (int i = 0; i < str.length(); i++) {

            // Odd length palindrome
            int low = i, high = i;
            while (low >= 0 && high < str.length() &&
                    str.charAt(low) == str.charAt(high)) {

                String palindrome = str.substring(low, high + 1);
                if (palindrome.length() > lps.length()) {
                    lps = palindrome;
                }
                low--;
                high++;
            }

            // Even length palindrome
            low = i - 1;
            high = i;
            while (low >= 0 && high < str.length() &&
                    str.charAt(low) == str.charAt(high)) {

                String palindrome = str.substring(low, high + 1);
                if (palindrome.length() > lps.length()) {
                    lps = palindrome;
                }
                low--;
                high++;
            }
        }
        return lps;
    }


    //6.Allocate minimum nuber of space



}