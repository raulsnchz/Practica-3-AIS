package es.codeurjc.test;

public class CalculatorParser {

    public int parse(String expression) {
        String[] tokens = expression.split("\\+");
        int result = 0;
        for (String token : tokens) {
            result += Integer.parseInt(token.trim());
        }
        return result;
    }

}
