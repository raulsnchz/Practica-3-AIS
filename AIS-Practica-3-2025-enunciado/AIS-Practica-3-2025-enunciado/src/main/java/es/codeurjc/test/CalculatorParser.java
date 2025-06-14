package es.codeurjc.test;

public class CalculatorParser {

    public int parse(String expression) {
        if (expression.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Invalid expression");
        }

        if (expression.contains("-")) {
            String[] tokens = expression.split("-");
            int result = Integer.parseInt(tokens[0].trim());
            for (int i = 1; i < tokens.length; i++) {
                result -= Integer.parseInt(tokens[i].trim());
            }
            return result;
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
