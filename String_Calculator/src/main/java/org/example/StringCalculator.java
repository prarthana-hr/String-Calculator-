package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private int callCount = 0;

    /**
     * Adds numbers provided in a delimited string format.
     * <p>
     * This method supports a variety of input formats:
     * <ul>
     *   <li>Empty string returns 0.</li>
     *   <li>Single numbers are parsed and returned directly.</li>
     *   <li>Default delimiters: comma (`,`) and newline (`\n`).</li>
     *   <li>Custom single-character delimiters specified in the format: <code>//[delimiter]\n</code>.</li>
     *   <li>Multiple custom delimiters specified in the format: <code>//[delim1][delim2]...\n</code>.</li>
     *   <li>Delimiters of any length specified in brackets.</li>
     *   <li>Ignores numbers greater than 1000.</li>
     *   <li>Throws an exception if negative numbers are provided, with a detailed message listing them.</li>
     * </ul>
     *
     * @param numbers the delimited string of numbers to add. Delimiters can be default, custom, or multiple custom.
     * @return the sum of all numbers in the input string, excluding those greater than 1000.
     * @throws IllegalArgumentException if negative numbers are present in the input string.
     */

    public int add(String numbers) {
        callCount++;
        if (numbers.isEmpty()) {
            return 0;
        }
        String delimiter = ",|\n";

        if (numbers.startsWith("//")) {
            int delimiterEndIndex = numbers.indexOf("\n");
            String delimiterPart = numbers.substring(2, delimiterEndIndex);
            if (delimiterPart.startsWith("[") && delimiterPart.endsWith("]")) {
                StringBuilder delimiterRegex = new StringBuilder();
                Matcher matcher = Pattern.compile("\\[(.*?)]").matcher(delimiterPart);

                while (matcher.find()) {
                    if (delimiterRegex.length() > 0) {
                        delimiterRegex.append("|");
                    }
                    delimiterRegex.append(Pattern.quote(matcher.group(1)));
                }
                delimiter = delimiterRegex.toString();
            } else {
                delimiter = Pattern.quote(delimiterPart);
            }
            numbers = numbers.substring(delimiterEndIndex + 1);
        }

        String[] parts = numbers.split(delimiter);
        int sum = 0;
        StringBuilder negativeNumbers = new StringBuilder();

        for (String part : parts) {
            if (!part.trim().isEmpty()) {
                int num = Integer.parseInt(part.trim());
                if (num < 0) {
                    if (negativeNumbers.length() > 0) {
                        negativeNumbers.append(", ");
                    }
                    negativeNumbers.append(num);
                }
                if (num <= 1000) {
                    sum += num;
                }
            }
        }
        if (negativeNumbers.length() > 0) {
            throw new IllegalArgumentException("Negatives not allowed: " + negativeNumbers);
        }
        return sum;
    }


    /**
     * Returns the number of times the {@code add()} method has been invoked.
     *
     * @return the count of {@code add()} method calls.
     */
    public int getCalledCount() {
        return callCount;
    }

    /**
     * The main method demonstrates the usage and functionality of the {@code StringCalculator} class.
     * It performs a series of tests to validate the implementation of the {@code add()} method and
     * the {@code getCalledCount()} method. Each test case represents a specific feature or scenario.
     * <p>
     * Tests performed:
     * <ul>
     *   <li>Test 1: Empty string as input.</li>
     *   <li>Test 2: Single number as input.</li>
     *   <li>Test 3: Two numbers as input.</li>
     *   <li>Test 4: Multiple numbers as input.</li>
     *   <li>Test 5: Handling new line as a delimiter.</li>
     *   <li>Test 6: Custom delimiter provided in the input.</li>
     *   <li>Test 7: Handling negative numbers by throwing an exception.</li>
     *   <li>Test 8: Ignoring numbers greater than 1000.</li>
     *   <li>Test 9: Allowing multiple custom delimiters.</li>
     *   <li>Test 10: Allowing multiple custom delimiters with length greater than one character.</li>
     * </ul>
     * <p>
     * It also prints the number of times the {@code add()} method was called.
     *
     * @param args command-line arguments (not used in this application).
     */

    public static void main(String args[]) {
        StringCalculator stringCalculatorRef = new StringCalculator();

        System.out.println("Test 1: Empty string -> Result: " + stringCalculatorRef.add(""));
        System.out.println("Test 2: Single number -> Result: " + stringCalculatorRef.add("5"));
        System.out.println("Test 3: Two numbers -> Result: " + stringCalculatorRef.add("1,2"));
        System.out.println("Test 4: Multiple numbers -> Result: " + stringCalculatorRef.add("1,2,3,4,5"));
        System.out.println("Test 5: New line as delimiter -> Result: " + stringCalculatorRef.add("1\n2,3"));
        System.out.println("Test 6: Custom delimiter -> Result: " + stringCalculatorRef.add("//;\n1;2;3"));
        try {
            System.out.println("Test 7: Negative numbers exception -> ");
            stringCalculatorRef.add("1,-2,-3");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Test 8: Numbers > 1000 ignored -> Result: " + stringCalculatorRef.add("2,1001"));
        System.out.println("Test 9: Multiple delimiters -> Result: " + stringCalculatorRef.add("//[*][%]\n1*2%3"));
        System.out.println("Test 10: Multiple delimiters > 1 char -> Result: " + stringCalculatorRef.add("//[**][%%]\n1**2%%3"));
        System.out.println("Add method call count: " + stringCalculatorRef.getCalledCount());
    }
}
