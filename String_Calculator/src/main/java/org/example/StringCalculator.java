package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String numbers) {
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
}
