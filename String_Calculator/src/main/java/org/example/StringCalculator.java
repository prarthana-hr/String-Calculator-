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
                sum += num;
            }
        }

        if (negativeNumbers.length() > 0) {
            throw new IllegalArgumentException("Negatives not allowed: " + negativeNumbers);
        }

        return sum;
    }
}
