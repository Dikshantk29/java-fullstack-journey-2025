package week4;

public class RecursionAdv {

    public static void main(String[] args) {

        System.out.println(tilingProblem(4));
        // Output: 5

        String str = "appnnacollege";
        removeDuplicates(str, 0, new StringBuilder(), new boolean[26]);
        // Output: apncollege

        System.out.println(friendsParining(3));
        // Output: 4

        printBinaryString(3, 0, new StringBuilder());
        // Output:
        // 000
        // 001
        // 010
        // 100
        // 101
    }

    // ---------------- TILING PROBLEM ----------------
    // 2*n board and 2*1 tile
    static int tilingProblem(int n) {

        // Base case correct ✔
        if (n == 0 || n == 1) {
            return 1;
        }

        // Choice:
        // 1. Place tile vertically  → f(n-1)
        // 2. Place tiles horizontally → f(n-2)
        return tilingProblem(n - 1) + tilingProblem(n - 2);
    }

    // ---------------- REMOVE DUPLICATES ----------------
    static void removeDuplicates(String str, int idx,
                                 StringBuilder newStr, boolean[] map) {

        // ❌ Earlier: nothing printed
        if (idx == str.length()) {
            System.out.println(newStr.toString());
            return;
        }

        char c = str.charAt(idx);

        if (map[c - 'a']) {
            // duplicate character → skip
            removeDuplicates(str, idx + 1, newStr, map);
        } else {
            // first occurrence
            map[c - 'a'] = true;

            // ❌ Earlier: newStr.append(c) modifies same object
            // ✅ Safe here because we are not branching into two paths
            newStr.append(c);
            removeDuplicates(str, idx + 1, newStr, map);
        }
    }

    // ---------------- FRIENDS PAIRING ----------------
    static int friendsParining(int n) {

        // Base case correct ✔
        if (n == 1 || n == 2) {
            return n;
        }

        // Single friend stays alone
        int fnm1 = friendsParining(n - 1);

        // Friend pairs with (n-1) choices
        int fnm2 = friendsParining(n - 2);
        int pairWays = (n - 1) * fnm2;

        return fnm1 + pairWays;
    }

    // ---------------- BINARY STRING WITHOUT CONSECUTIVE 1s ----------------
    static void printBinaryString(int n, int lastPlace, StringBuilder str) {

        // ❌ Earlier: missing return → infinite recursion
        if (n == 0) {
            System.out.println(str.toString());
            return;
        }

        // ❌ Earlier: same StringBuilder reused → wrong output
        // ✅ Create NEW StringBuilder for each recursive branch

        if (lastPlace == 0) {
            printBinaryString(n - 1, 0,
                    new StringBuilder(str).append('0'));

            printBinaryString(n - 1, 1,
                    new StringBuilder(str).append('1'));
        } else {
            printBinaryString(n - 1, 0,
                    new StringBuilder(str).append('0'));
        }
    }
}
