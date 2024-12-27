package org.example;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        numbers = numbers.replace("\n", ",");

        String[] parts = numbers.split(",");
        int sum = 0;

        for (String part : parts) {
            sum += Integer.parseInt(part.trim());
        }
        return sum;
    }
}
