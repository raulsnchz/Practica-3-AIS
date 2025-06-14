package es.codeurjc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
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

    @ParameterizedTest
    @CsvSource({ "5-3, 2", "1-2, -1", "7-2-1, 4", "9-5-3-1, 0" })
    public void testSubs(String expression, int expected) {
        assertEquals(expected, calculator.parse(expression));
    }

    @ParameterizedTest
    @CsvSource({ "7+1-5, 3", "9-5+4, 8", "9+1-6-2, 2", "-5+9, 4"})
    public void testMix(String expression, int expected) {
        assertEquals(expected, calculator.parse(expression));
    }
}
