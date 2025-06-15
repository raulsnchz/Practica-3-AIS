package es.codeurjc.test;

public class CalculatorParser {

    public int parse(String expression) {
        if (expression.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Invalid expression");
        }

        String[] tokens = expression.split("(?<=[-+])|(?=[-+])");
        int result = 0;
        String currentOperator = "+";

        for (String token : tokens) {
            String trimmedToken = token.trim();
            if (trimmedToken.isEmpty())
                continue;

            if (trimmedToken.equals("+") || trimmedToken.equals("-")) {
                currentOperator = trimmedToken;
            } else {
                if (!trimmedToken.matches("\\d+")) {
                    throw new IllegalArgumentException("Invalid expression");
                }

                int value = Integer.parseInt(trimmedToken);
                if (currentOperator.equals("+")) {
                    result += value;
                } else if (currentOperator.equals("-")) {
                    result -= value;
                }
            }
        }
        return result;
    }

}
