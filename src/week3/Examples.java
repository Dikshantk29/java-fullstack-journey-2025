package week3;

// revision
public class Examples {

    public static void main(String[] args) {

        System.out.println(checkPalindrome("madam"));
        // Output: true

        System.out.println(checkPalindrome("hello"));
        // Output: false

        System.out.println(shortestPathCalculate("WNEENESENNN"));
        // Output: 5

        NoteCounting("AAABBBCCDAA");
        // Output: A3B3C2D1A2
    }

    // ---------------- CHECK PALINDROME ----------------
    static boolean checkPalindrome(String s) {

        int n = s.length();

        // Logic is correct ✔
        // Compare start and end characters
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    // ---------------- SHORTEST PATH ----------------
    static int shortestPathCalculate(String str) {

        int x = 0;
        int y = 0;

        for (int i = 0; i < str.length(); i++) {
            char dir = str.charAt(i);

            if (dir == 'E') {
                x++;
            } else if (dir == 'W') {
                x--;
            } else if (dir == 'N') {
                y++;
            } else if (dir == 'S') {
                y--;
            }
        }

        // ❌ Earlier confusion: why sqrt?
        // Because shortest path = straight-line distance from origin
        // Formula: √(x² + y²)

        int ans = (int) Math.sqrt(x * x + y * y);
        return ans;
    }

    // ---------------- NOTE COUNTING (STRING COMPRESSION) ----------------
    // Input : AAABBBCCDAA
    // Output: A3B3C2D1A2
    static void NoteCounting(String str) {

        // ❌ Doubt here: how to count consecutive characters
        // Approach:
        // 1. Start count = 1
        // 2. Compare current char with next
        // 3. If same → increment count
        // 4. If different → print char + count and reset

        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int i = 0; i < str.length() - 1; i++) {

            if (str.charAt(i) == str.charAt(i + 1)) {
                count++;
            } else {
                sb.append(str.charAt(i));
                sb.append(count);
                count = 1; // ❌ Earlier missing reset logic
            }
        }

        // ❌ Important:
        // Last character group is NOT printed inside loop
        sb.append(str.charAt(str.length() - 1));
        sb.append(count);

        System.out.println(sb.toString());
    }
}
