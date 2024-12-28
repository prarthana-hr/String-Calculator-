String Calculator

Overview
The String Calculator is a Java application designed to add numbers from a delimited string. It supports default and custom delimiters, handles negative numbers, and ignores numbers greater than 1000. The project is developed following Test-Driven Development (TDD) principles.

Features
Handles multiple delimiters: Default delimiters (comma , and newline \n), custom delimiters, and delimiters of any length.
Throws exception for negative numbers with a detailed message.
Ignores numbers greater than 1000.
Tracks method call count for the add() method.
Installation
Clone the repository:

bash
Copy code
git clone https://github.com/prarthana-hr/String-Calculator-.git
cd String-Calculator-
Compile and run the program using your preferred Java IDE or the terminal.

Usage
Example: Adding Numbers
java
Copy code
StringCalculator calculator = new StringCalculator();
int result = calculator.add("1,2,3");
System.out.println(result); // Output: 6
Example: Using Custom Delimiters
java
Copy code
int customResult = calculator.add("//;\n1;2;3");
System.out.println(customResult); // Output: 6
Example: Handling Negative Numbers
java
Copy code
try {
    calculator.add("1,-2,-3");
} catch (IllegalArgumentException e) {
    System.out.println(e.getMessage()); // Output: "Negatives not allowed: -2, -3"
}
Tests
The main method demonstrates the functionality and runs a series of tests, including:

Empty string as input
Single number input
Multiple numbers with various delimiters
Handling negative numbers and numbers greater than 1000
