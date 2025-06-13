package es.codeurjc.test;

public class CalculatorParser {

    public int parse(String expression) {
        if (expression.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Invalid expression");
        }

        String[] tokens = expression.split("\\+");
        int result = 0;
        for (String token : tokens) {
            String trimmed = token.trim();
            if (!trimmed.matches("\\d+")) {
                throw new IllegalArgumentException("Invalid expression");
            }
            result += Integer.parseInt(trimmed);
        }
        return result;
    }

}
