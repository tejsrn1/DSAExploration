package a.expo;


public class PatternsDSA {


    /**
     * This method prints a 4x4 matrix of asterisks.
     */
    public void printMatrix1() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * This method prints a right-angled triangle of asterisks.
     */
    public void printMatrix2() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * This method prints a right-angled triangle of numbers.
     */
    public void printMatrix3() {
        for (int i = 0; i < 4; i++) {
            int k = 1;
            for (int j = 0; j <= i; j++) {
                System.out.print(k++);
            }
            System.out.println();
        }
    }

    /**
     * This method prints a right-angled triangle of repeated numbers.
     */
    public void printMatrix4() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    /**
     * This method prints a right-angled triangle of asterisks in reverse.
     */
    public void printMatrix5() {
        int totalrows = 4;
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= totalrows - i + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * This method prints a right-angled triangle of numbers in reverse.
     */
    public void printMatrix6() {
        int totalrows = 4;
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= totalrows - i + 1; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    /**
     * This method prints a pyramid of asterisks.
     */
    public void printMatrix7() {
        int totalrows = 4;
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= totalrows - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i + (i - 1); j++) {
                System.out.print("*");
            }
            for (int j = 1; j <= totalrows - i; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * This method prints a pyramid of asterisks in reverse.
     */
    public void printMatrix8() {
        int totalrows = 4;
        for (int i = 4; i >= 1; i--) {
            for (int j = 1; j <= totalrows - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i + (i - 1); j++) {
                System.out.print("*");
            }
            for (int j = 1; j <= totalrows - i; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }


    /**
     * This method prints a diamond shape pattern using asterisks.
     */
    public void printMatrix9() {
        int totalRows = 4;

        // Print the upper half of the diamond
        for (int i = 1; i <= totalRows; i++) {
            // Print leading spaces
            for (int j = 1; j <= totalRows - i + 1; j++) {
                System.out.print(" ");
            }

            // Print asterisks
            for (int j = 1; j <= i + (i - 1); j++) {
                System.out.print("*");
            }

            // Print trailing spaces
            for (int j = 1; j <= totalRows - i + 1; j++) {
                System.out.print(" ");
            }

            System.out.println();
        }

        // Print the lower half of the diamond
        for (int i = 1; i <= totalRows; i++) {
            // Print leading spaces
            for (int j = 1; j <= i; j++) {
                System.out.print(" ");
            }

            // Print asterisks
            for (int j = 1; j <= totalRows - i + (totalRows - (i - 1)); j++) {
                System.out.print("*");
            }

            // Print trailing spaces
            for (int j = 1; j <= i; j++) {
                System.out.print(" ");
            }

            System.out.println();
        }
    }

    /**
     * This method prints a symmetric pattern using asterisks.
     */
    public void printMatrix10() {
        int totalRows = 4;
        int n = totalRows * 2 - 1;

        // Print the pattern
        for (int i = 0; i < n; i++) {
            int stars = i;

            // Flip the pattern after crossing the symmetry
            if (i >= totalRows) {
                stars = n - (i + 1);
            }

            // Print asterisks
            for (int j = 0; j <= stars; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    /**
     * This method prints a pattern of alternating 1s and 0s.
     */
    public void printMatrix11() {
        for (int i = 0; i < 4; i++) {
            int start = 0;
            if (i % 2 == 0) { // For even rows it starts with 1 else 0.
                start = 1;
            }

            // Print the pattern
            for (int j = 0; j <= i; j++) {
                System.out.print(start);
                start = 1 - start; // This will keep flipping at each column.
            }

            System.out.println();
        }
    }

    /**
     * This method prints a pattern of numbers in a pyramid shape.
     */
    public void printMatrix12() {
        int N = 3;
        int L = 2 * N; //6

        // Print the pattern
        for (int i = 1; i <= N; i++) {
            int count = 1;

            // Print the numbers
            for (int j = 1; j <= i; j++) {
                System.out.print(count++);
            }

            // Print the spaces
            for (int j = 1; j <= L - 2 * i; j++) {
                System.out.print(" ");
            }

            // Print the numbers in reverse order
            for (int j = i; j > 0; j--) {
                System.out.print(j);
            }

            System.out.println();
        }
    }

    /**
     * This method prints a pattern of increasing numbers.
     */
    public void printMatrix13() {
        int count = 1;
        int N = 6;

        // Print the pattern
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(count++ + " ");
            }
            System.out.println();
        }
    }

    /**
     * This method prints a pattern of increasing letters.
     */
    public void printMatrix14() {
        int N = 6;

        // Print the pattern
        for (int i = 0; i < N; i++) {
            char letter = 'A';
            for (int j = 0; j <= i; j++) {
                System.out.print(letter + " ");
                letter++;
            }
            System.out.println();
        }
    }

    /**
     * This method prints a pattern of decreasing letters.
     */
    public void printMatrix15() {
        int N = 6;

        // Print the pattern
        for (int i = N - 1; i >= 0; i--) {
            char letter = 'A';
            for (int j = 0; j <= i; j++) {
                System.out.print(letter + " ");
                letter++;
            }
            System.out.println();
        }
    }

    /**
     * This method prints a pattern of letters where each row contains the same letter.
     */
    public void printMatrix16() {
        int N = 6;
        char letter = 'A';

        // Print the pattern
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(letter + " ");
            }
            letter++;
            System.out.println();
        }
    }


    /**
     * This method prints a pyramid pattern of letters.
     * <p>
     * Example for N = 3:
     * A
     * ABA
     * ABCBA
     *
     */
    public void printMatrix17() {
        int N = 3;

        for (int i = 1; i <= N; i++) {
            int L = 2 * i - 1;

            for (int s = 0; s < N - i; s++) {
                System.out.print(" ");
            }

            char letter = 'A';
            for (int j = 1; j <= L; j++) {
                System.out.print(letter);
                if (j >= i) {
                    letter--;
                } else {
                    letter++;
                }
            }

            for (int s = 0; s < N - i; s++) {
                System.out.print(" ");
            }

            System.out.println();
        }
    }

    /**
     * This method prints a right-angled triangle pattern of letters in reverse order.
     * <p>
     * Example for N = 3:
     * C
     * BC
     * ABC
     *
     */
    public void printMatrix18() {
        int N = 3;
        for (int i = 1; i <= N; i++) {
            char letter = (char) ('A' + N - i);
            for (int j = 1; j <= i; j++) {
                System.out.print(letter + " ");
                letter++;
            }
            System.out.println();
        }
    }

    /**
     * This method prints a pattern of asterisks that forms a diamond shape.
     * <p>
     * Example for N = 3:
     * ******
     * **  **
     * *    *
     * *    *
     * **  **
     * ******
     *
     */
    public void printMatrix19() {
        int N = 3;

        for (int i = N; i > 0; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            for (int j = 1; j <= (N - i) * 2; j++) {
                System.out.print(" ");
            }

            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            for (int j = 1; j <= (N - i) * 2; j++) {
                System.out.print(" ");
            }

            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    /**
     * This method prints a pattern of asterisks that forms an hourglass shape.
     * <p>
     * Example for N = 3:
     * *    *
     * **  **
     * ******
     * **  **
     * *    *
     *
     */
    public void printMatrix20() {
        int N = 6;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            for (int j = 1; j <= (N - i) * 2; j++) {
                System.out.print(" ");
            }

            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }

        for (int i = N - 1; i > 0; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            for (int j = 1; j <= (N - i) * 2; j++) {
                System.out.print(" ");
            }

            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    /**
     * This method prints a pattern of asterisks that forms a square with a hollow center.
     * <p>
     * Example for N = 6:
     * ******
     * *    *
     * *    *
     * *    *
     * *    *
     * ******
     *
     */
    public void printMatrix21() {
        int N = 6;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == 1 || i == N || j == 1 || j == N) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }
    }
}
