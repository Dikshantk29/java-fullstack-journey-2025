package code_practice.dsaCode;


public class Sudoku {
    public static void main(String[] args) {
        int[][] sudoku = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (sudokuSolver(sudoku, 0, 0)) {
            printSudoku(sudoku);
        } else {
            System.out.println("No solution exists");
        }

    }

    static boolean sudokuSolver(int[][] sudoku, int row, int col) {

        //base case
        if (row == 9) {
            return true;

        }

        //recursion

        int nextRow = row;
        int nextCol = col + 1;

        if (col + 1 == 9) {
            nextRow = row + 1;
            nextCol = 0;

        }

        if (sudoku[row][col] != 0) {
            return sudokuSolver(sudoku, nextRow, nextCol);
        }
        for (int digit = 1; digit <= 9; digit++) {
            if (isSafeValue(sudoku, row, col, digit)) {
                sudoku[row][col] = digit;
                if (sudokuSolver(sudoku, nextRow, nextCol)) {
                    //solution exits
                    return true;
                }
                sudoku[row][col] = 0;
            }

        }
        return false;

    }

    private static boolean isSafeValue(int[][] sudoku, int row, int col, int digit) {
        // column
        for (int i = 0; i < 9; i++)
            if (sudoku[i][col] == digit)
                return false;

        // row
        for (int j = 0; j < 9; j++)
            if (sudoku[row][j] == digit)
                return false;

        // 3x3 grid
        int gridRow = (row / 3) * 3;
        int gridCol = (col / 3) * 3;

        for (int i = gridRow; i < gridRow + 3; i++)
            for (int j = gridCol; j < gridCol + 3; j++)
                if (sudoku[i][j] == digit)
                    return false;

        return true;
    }
    public static void printSudoku(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }
}
