package edu.byu.cs203.junit.advanced;

public class Simple {

    public int add(int number1, int number2) {
        return number1 + number2;
    }

    public int subtract(int number1, int number2) {
        return number1 - number2;
    }

    public int multiply(int number1, int number2) {
        return number1 * number2;
    }

    public int divide(int number1, int number2) throws ArithmeticException {
        return number1 / number2;
    }

    /**
     * Calculates the integer power. That means we do not care about decimals in this method.
     * Note that when exponent is negative, the result will normally be between 0 and 1. 
     * Since this method calculates only integers, when exponent is negative, 1 should be returned.
     * 
     * @param base The base of the exponent calculation.
     * @param exp The exponent (number of times to multiply the base). 
     * @return The base variable multiplied exp times. If exp <= 0, then 1 is returned.
     */
    public int power(int base, int exp) {
        for (int i = 1; i < exp; i++) { //Here I changed "<=" to "<" and deleted the unused variable, ans, in the previous line
            base *= base; //Here I replaced "ans" with "base" on the right hand side
        }
        return base;
    }

}
