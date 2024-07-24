package exploration;

public class MathsDSA {


    /**
     * This method counts the number of digits in an integer.
     *
     * @param  inputNumber The input integer.
     * @return The number of digits in the integer.
     * <p>
     * Example:
     * Input: 1234
     * Output: 4
     */
    public int countDigits(int inputNumber) {
        int count = 0;
        while (inputNumber != 0) {
            inputNumber = inputNumber / 10; // Keep dividing until it hits zero
            count++;
        }
        return count;
    }

    /**
     * This method reverses an integer.
     *
     * @param  inputNumber The input integer.
     * @return The reversed integer. If the reversed integer exceeds the range of 32-bit signed integer, returns 0.
     * <p>
     * Example:
     * Input: 1234
     * Output: 4321
     */
    public int ReverseNumber(int inputNumber) {
        if (inputNumber > 214748364 || inputNumber < -214748364) { // Do not send number > 2^31 or < 2^-31
            return 0;
        }

        int revNo = 0;
        while (inputNumber != 0) {
            int rem = inputNumber % 10; // Take remainder as the last digit of the given number
            inputNumber = inputNumber / 10; // This will reduce number like 1234 -> 123
            revNo = revNo * 10 + rem; // This will keep making number like 3 * 10 + 2 = 32 then 32 * 10 + 1 = 321
        }
        if (revNo > 214748364) { // Do not send number > 2^31 or < 2^-31
            return 0;
        }

        return revNo;
    }

    /**
     * This method checks if an integer is a palindrome.
     *
     * @param  inputNumber The input integer.
     * @return True if the integer is a palindrome, false otherwise.
     * <p>
     * Example:
     * Input: 121
     * Output: true
     */
    public boolean IsNumPalindrome(int inputNumber) {
        if (inputNumber < 0) {
            return false;
        }
        int revNo = ReverseNumber(inputNumber);
        if (revNo == inputNumber) {
            return true;
        }
        return false;
    }

    /**
     * This method calculates the greatest common divisor (GCD) of two integers.
     *
     * @param  inputNumber  The first input integer.
     * @param  inputNumber2 The second input integer.
     * @return The GCD of the two integers.
     * <p>
     * Example:
     * Input: 36, 24
     * Output: 12
     */
    public int GCD(int inputNumber, int inputNumber2) {
        int minNum = Math.min(inputNumber, inputNumber2);
        int ans = 1;

        for (int i = 1; i <= minNum; i++) {
            if (inputNumber % i == 0 && inputNumber2 % i == 0) {
                ans = i; // Keep tracking ans as we need the largest common number
            }
        }

        return ans;
    }

    /**
     * This method checks if an integer is an Armstrong number.
     *
     * @param  inputNumber The input integer.
     * @return True if the integer is an Armstrong number, false otherwise.
     * <p>
     * Example:
     * Input: 153
     * Output: true
     */
    public boolean ArmstrongNum(int inputNumber) {
        int powerDigit = 0;
        int temp = inputNumber;
        while (temp != 0) {
            powerDigit++;
            temp = temp / 10;
        }

        int orgNum = inputNumber;
        int armNum = 0;

        while (inputNumber != 0) {
            int lastDigit = inputNumber % 10;
            armNum += (int) Math.pow(lastDigit, powerDigit);
            inputNumber = inputNumber / 10;
        }
        if (orgNum == armNum) {
            return true;
        }
        return false;
    }

    /**
     * This method prints all the divisors of an integer.
     *
     * @param  inputNumber The input integer.
     *                    <p>
     *                    Example:
     *                    Input: 36
     *                    Output: 1, 2, 3, 4, 6, 9, 12, 18, 36
     */
    public void PrintDivisors(int inputNumber) {
        for (int x = 1; x <= Math.sqrt(inputNumber); x++) {
            if (inputNumber % x == 0) {
                System.out.println("Divisor is " + x + " ");

                if (inputNumber / x != x) {
                    System.out.println("Another Divisor is " + inputNumber / x + " ");
                }
            }
        }
    }

    /**
     * This method checks if an integer is a prime number.
     *
     * @param  inputNumber The input integer.
     * @return True if the integer is a prime number, false otherwise.
     * <p>
     * Example:
     * Input: 17
     * Output: true
     */
    public boolean IsPrimeNo(int inputNumber) {
        for (int x = 2; x < Math.sqrt(inputNumber); x++) {
            if (inputNumber % x == 0) {
                return false;
            }
        }
        return true;
    }
}
