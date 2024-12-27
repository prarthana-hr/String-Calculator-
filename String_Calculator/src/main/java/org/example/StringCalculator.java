package org.example;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",";

        if (numbers.startsWith("//")) {
            int delimiterEndIndex = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterEndIndex);
            numbers = numbers.substring(delimiterEndIndex + 1);
        }

        numbers = numbers.replace("\n", delimiter);
        delimiter = java.util.regex.Pattern.quote(delimiter);
        String[] parts = numbers.split(delimiter);
        int sum = 0;

        for (String part : parts) {
            if (!part.trim().isEmpty()) {
                sum += Integer.parseInt(part.trim());
            }
        }

        return sum;
    }
}
