package org.example;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",";
        if (numbers.startsWith("//")) {
            int delimiterEndIndex = numbers.indexOf("\n");
            String delimiterSection = numbers.substring(2, delimiterEndIndex);

            if (delimiterSection.startsWith("[") && delimiterSection.endsWith("]")) {
                delimiter = java.util.regex.Pattern.quote(delimiterSection.substring(1, delimiterSection.length() - 1));
            } else {
                delimiter = java.util.regex.Pattern.quote(delimiterSection);
            }

            numbers = numbers.substring(delimiterEndIndex + 1);
        }

        numbers = numbers.replace("\n", delimiter);
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
}
