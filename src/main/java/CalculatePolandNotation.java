import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Stack;

public class CalculatePolandNotation {
    private final String input;
    private final Stack<BigInteger> stackBigInteger = new Stack<>();
    private final Stack<BigDecimal> stackBigDecimal = new Stack<>();
    private StringBuilder sb = new StringBuilder();

    public CalculatePolandNotation(String input) {
        this.input = input;
    }

    public BigInteger calculatePolandBigInteger() throws ArithmeticException {
        BigInteger firstNumber, secondNumber, result;
        for (int i = 0; i < input.length(); i++) {

            if (Character.isDigit(input.charAt(i)) || input.charAt(i) == ',' ||
                    ((input.charAt(i + 1) != ',' || input.charAt(i - 1) != ',') && input.charAt(i) == '-')) {
                if (Character.isDigit(input.charAt(i)) || input.charAt(i) == '-') {
                    sb.append(input.charAt(i));
                } else if (input.charAt(i) == ',') {
                    if (sb.isEmpty()) {
                        continue;
                    }
                    stackBigInteger.push(new BigInteger(sb.toString()));
                    sb = new StringBuilder();
                }

            } else {
                secondNumber = stackBigInteger.pop();
                firstNumber = stackBigInteger.pop();
                result = switch (input.charAt(i)) {
                    case '+' -> firstNumber.add(secondNumber);
                    case '-' -> firstNumber.subtract(secondNumber);
                    case '*' -> firstNumber.multiply(secondNumber);
                    case '/' -> firstNumber.divide(secondNumber);
                    default -> BigInteger.ZERO;
                };
                stackBigInteger.push(result);
            }
        }
        result = stackBigInteger.pop();
        return result;
    }

    public BigDecimal calculatePolandBigDecimal() throws ArithmeticException {
        BigDecimal firstNumber, secondNumber, result;

        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i)) || input.charAt(i) == ',' || input.charAt(i) == '.'
                    || ((input.charAt(i + 1) != ',' || input.charAt(i - 1) != ',') && input.charAt(i) == '-')) {
                if (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.' || input.charAt(i) == '-') {
                    sb.append(input.charAt(i));
                } else if (input.charAt(i) == ',') {
                    if (sb.isEmpty()) {
                        continue;
                    }
                    stackBigDecimal.push(new BigDecimal(sb.toString()));
                    sb = new StringBuilder();
                }
            } else {
                secondNumber = stackBigDecimal.pop();
                firstNumber = stackBigDecimal.pop();
                result = switch (input.charAt(i)) {
                    case '+' -> firstNumber.add(secondNumber);
                    case '-' -> firstNumber.subtract(secondNumber);
                    case '*' -> firstNumber.multiply(secondNumber);
                    case '/' -> firstNumber.divide(secondNumber, RoundingMode.HALF_UP);
                    default -> BigDecimal.ZERO;
                };
                stackBigDecimal.push(result);
            }
        }
        result = stackBigDecimal.pop();
        return result;
    }
}

