package es.codeurjc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculatorParserTest {
    private CalculatorParser calculator;

    @BeforeEach
    public void setUp() {
        this.calculator = new CalculatorParser();
    }

    @ParameterizedTest
    @ValueSource(strings = { "1", "2", "3" })
    public void testSingleNumbers(String input) {
        assertEquals(Integer.parseInt(input), calculator.parse(input));
    }

    @ParameterizedTest
    @CsvSource({ "1+1, 2", "2+3, 5", "2+3+4, 9", "1+2+3+4, 10" })
    public void testSums(String expression, int expected) {
        assertEquals(expected, calculator.parse(expression));
    }

    @ParameterizedTest
    @ValueSource(strings = { "A", "B", "k", "HoLa", "1 + A" })
    public void testInvalidExpressions(String expression) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> calculator.parse(expression));
        assertEquals("Invalid expression", exception.getMessage());
    }

}
