package org.exampleTest;

import org.example.StringCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {
    @Test
    public void testAddEmptyString() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(0, stringCalculator.add("")); // Empty string should return 0
        assertEquals(1, stringCalculator.add("1"));// Single number should return the number itself
        assertEquals(3, stringCalculator.add("1,2"));// Two numbers should return their sum
        assertEquals(6, stringCalculator.add("1,2,3"));// Sum of 1, 2, 3 is 6
        assertEquals(15, stringCalculator.add("1,2,3,4,5"));// Sum of 1, 2, 3, 4, 5 is 15
        assertEquals(0, stringCalculator.add("0,0,0"));// Sum of 0, 0, 0 is 0
    }

    @Test
    public void testAddNewlineDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1\n2,3")); // Handles both commas and newlines
        assertEquals(10, calculator.add("1\n2\n3\n4")); // Handles multiple newlines
    }

    @Test
    public void testAddCombinationOfDelimiters() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(10, calculator.add("1,2\n3,4")); // Mixed delimiters should return the correct sum
    }
}