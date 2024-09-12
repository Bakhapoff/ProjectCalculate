import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Stack;

public class ReversePolishNotationCalculator {
    private final String inputExpression;
    private final Stack<BigInteger> stackBigInteger = new Stack<>();
    private final Stack<BigDecimal> stackBigDecimal = new Stack<>();
    private StringBuilder numberBuffer = new StringBuilder();

    public ReversePolishNotationCalculator(String inputExpression) {
        this.inputExpression = inputExpression;
    }

    public BigInteger calculateRPNBigInteger() throws ArithmeticException {
        BigInteger firstNumber, secondNumber, result;
        for (int i = 0; i < inputExpression.length(); i++) {

            if (Character.isDigit(inputExpression.charAt(i)) || inputExpression.charAt(i) == ',' ||
                    ((inputExpression.charAt(i + 1) != ',' || inputExpression.charAt(i - 1) != ',') && inputExpression.charAt(i) == '-')) {
                if (Character.isDigit(inputExpression.charAt(i)) || inputExpression.charAt(i) == '-') {
                    numberBuffer.append(inputExpression.charAt(i));
                } else if (inputExpression.charAt(i) == ',') {
                    if (numberBuffer.isEmpty()) {
                        continue;
                    }
                    stackBigInteger.push(new BigInteger(numberBuffer.toString()));
                    numberBuffer = new StringBuilder();
                }

            } else {
                secondNumber = stackBigInteger.pop();
                firstNumber = stackBigInteger.pop();
                result = switch (inputExpression.charAt(i)) {
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

    public BigDecimal calculateRPNBigDecimal() throws ArithmeticException {
        BigDecimal firstNumber, secondNumber, result;

        for (int i = 0; i < inputExpression.length(); i++) {
            if (Character.isDigit(inputExpression.charAt(i)) || inputExpression.charAt(i) == ',' || inputExpression.charAt(i) == '.'
                    || ((inputExpression.charAt(i + 1) != ',' || inputExpression.charAt(i - 1) != ',') && inputExpression.charAt(i) == '-')) {
                if (Character.isDigit(inputExpression.charAt(i)) || inputExpression.charAt(i) == '.' || inputExpression.charAt(i) == '-') {
                    numberBuffer.append(inputExpression.charAt(i));
                } else if (inputExpression.charAt(i) == ',') {
                    if (numberBuffer.isEmpty()) {
                        continue;
                    }
                    stackBigDecimal.push(new BigDecimal(numberBuffer.toString()));
                    numberBuffer = new StringBuilder();
                }
            } else {
                secondNumber = stackBigDecimal.pop();
                firstNumber = stackBigDecimal.pop();
                result = switch (inputExpression.charAt(i)) {
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

