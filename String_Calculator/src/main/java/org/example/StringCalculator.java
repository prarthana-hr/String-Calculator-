package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private int callCount = 0;

    public int add(String numbers) {
        callCount++;
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",|\n"; // Default delimiters: comma and newline

        // Check for custom delimiters
        if (numbers.startsWith("//")) {
            int delimiterEndIndex = numbers.indexOf("\n");
            String delimiterPart = numbers.substring(2, delimiterEndIndex);

            // Handle multiple delimiters with brackets
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
                // Single custom delimiter
                delimiter = Pattern.quote(delimiterPart);
            }

            // Remove the delimiter declaration line
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
                if (num <= 1000) { // Ignore numbers greater than 1000
                    sum += num;
                }
            }
        }

        if (negativeNumbers.length() > 0) {
            throw new IllegalArgumentException("Negatives not allowed: " + negativeNumbers);
        }

        return sum;
    }

    // New method to return the count of Add() invocations
    public int getCalledCount() {
        return callCount;
    }

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
