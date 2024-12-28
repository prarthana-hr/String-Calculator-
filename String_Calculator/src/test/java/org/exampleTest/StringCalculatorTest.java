package org.exampleTest;

import org.example.StringCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link StringCalculator} class.
 * <p>
 * This test suite ensures the correctness of the {@code add} method
 * in handling various input formats, edge cases, and exceptions.
 */
public class StringCalculatorTest {
    /**
     * Tests that an empty string input returns 0.
     */
    @Test
    public void testAddEmptyString() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(0, calculator.add(""));

    }

    /**
     * Tests that a single number string input is parsed correctly.
     */
    @Test
    public void testAddSingleNumberString() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(1, calculator.add("1"));
    }

    /**
     * Tests that two numbers in the input string are added correctly.
     */
    @Test
    public void testAddTwoNumberString() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(3, calculator.add("1,2"));
    }

    /**
     * Tests that adding multiple zeros in the input returns 0.
     */
    @Test
    public void testAddZeroString() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(0, calculator.add("0,0,0"));
    }

    /**
     * Tests that multiple numbers in the input string are summed correctly.
     */
    @Test
    public void testAddMultipleNumberString() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1,2,3"));
        assertEquals(15, calculator.add("1,2,3,4,5"));
    }

    /**
     * Tests that newline characters are correctly treated as delimiters.
     */
    @Test
    public void testAddNewlineDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1\n2,3"));
        assertEquals(10, calculator.add("1\n2\n3\n4"));
    }

    /**
     * Tests that custom single-character delimiters are parsed correctly.
     */
    @Test
    public void testAddDifferentDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(3, calculator.add("//;\n1;2"));
        assertEquals(10, calculator.add("//|\n1|2|3|4"));
        assertEquals(6, calculator.add("//#\n1#2#3"));
    }

    /**
     * Tests that a negative number in the input throws an exception with a descriptive message.
     */
    @Test
    public void testNegativeNumbers() {
        StringCalculator calculator = new StringCalculator();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("1,-2,3")
        );

        assertEquals("Negatives not allowed: -2", exception.getMessage());
    }

    /**
     * Tests that multiple negative numbers in the input throw an exception
     * with a descriptive message listing all negatives.
     */
    @Test
    public void testMultipleNegativeNumbers() {
        StringCalculator calculator = new StringCalculator();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("1,-2,-8,3")
        );

        assertEquals("Negatives not allowed: -2, -8", exception.getMessage());
    }

    /**
     * Tests that numbers greater than 1000 are ignored in the summation.
     */
    @Test
    public void testNumbersGreaterThan1000Ignored() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(2, calculator.add("2,1001"));
        assertEquals(1002, calculator.add("1000,2"));
        assertEquals(0, calculator.add("1001,1002"));
    }

    /**
     * Tests that custom delimiters of any length are parsed correctly.
     */
    @Test
    public void testCustomDelimiterOfAnyLength() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("//[***]\n1***2***3"));
        assertEquals(10, calculator.add("//[#####]\n2#####3#####5"));
        assertEquals(15, calculator.add("//[%%%]\n4%%%5%%%6"));
        assertEquals(5, calculator.add("//[!!!]\n5"));
        assertEquals(2, calculator.add("//[===]\n2===1001"));
        assertEquals(0, calculator.add("//[###]\n"));
    }

    /**
     * Tests that multiple delimiters are parsed and used correctly.
     */
    @Test
    void testMultipleDelimiters() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("//[*][%]\n1*2%3"));

    }

    /**
     * Tests that multiple custom delimiters of varying lengths are parsed correctly.
     */
    @Test
    public void testMultipleDelimitersWithLongerLength() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("//[**][%%]\n1**2%%3"));
        assertEquals(10, calculator.add("//[###][%%%]\n1###2%%%7"));
    }

    /**
     * Tests that the call count for the {@code add} method is incremented correctly.
     */
    @Test
    public void testGetCalledCount() {
        StringCalculator calculator = new StringCalculator();
        calculator.add("1,2");
        calculator.add("3");
        calculator.add("5,6,7");
        assertEquals(3, calculator.getCalledCount());
    }
}