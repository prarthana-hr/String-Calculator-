package org.exampleTest;

import org.example.StringCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {
    @Test
    public void testAddEmptyString(){
        StringCalculator stringCalculator=new StringCalculator();
        assertEquals(3, stringCalculator.add("1,2"));
        assertEquals(10, stringCalculator.add("7,3"));
    }


}
