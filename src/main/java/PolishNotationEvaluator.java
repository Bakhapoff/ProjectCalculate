import java.util.Stack;

public class PolishNotationEvaluator {
    private final String inputExpression;
    private final StringBuilder numberBuffer = new StringBuilder();
    private final Stack<Character> operatorStack = new Stack<>();


    public PolishNotationEvaluator(String inputExpression) {
        this.inputExpression = inputExpression;
    }

    public String convertToRPN() {
        for (int i = 0; i < inputExpression.length(); i++) {
            char currentChar = inputExpression.charAt(i);
            switch (currentChar) {
                case '+':
                    if (numberBuffer.charAt(numberBuffer.length() - 1) == ',') {
                        readOperation(currentChar, 1);
                    } else {
                        numberBuffer.append(',');
                        readOperation(currentChar, 1);
                    }
                    break;
                case '-':
                    if (i == 0 || inputExpression.charAt(i - 1) == '(' || "+-*/".indexOf(inputExpression.charAt(i - 1)) != -1) {
                        numberBuffer.append(currentChar);
                    } else {
                        checkComma();
                        readOperation(currentChar, 1);
                    }
                    break;
                case '*':
                case '/':
                    if (numberBuffer.charAt(numberBuffer.length() - 1) == ',') {
                        readOperation(currentChar, 2);
                    } else {
                        numberBuffer.append(',');
                        readOperation(currentChar, 2);
                    }
                    break;
                case '(':
                    operatorStack.push(currentChar);
                    break;
                case ')':
                    numberBuffer.append(',');
                    readClose();
                    break;
                default:
                    if (!numberBuffer.isEmpty() && numberBuffer.charAt(numberBuffer.length() - 1) == '+') {
                        numberBuffer.append(',').append(currentChar);
                    } else {
                        numberBuffer.append(currentChar);
                    }
                    break;
            }
        }
        checkComma();
        while (!operatorStack.isEmpty()) {
            numberBuffer.append(operatorStack.pop());
        }
        checkComma();
        return numberBuffer.toString();
    }

    private void checkComma() {
        if (numberBuffer.charAt(numberBuffer.length() - 1) != ',') {
            numberBuffer.append(',');
        }
    }

    private void readOperation(char operation, int priority) {
        while (!operatorStack.isEmpty()) {
            char prevOperation = operatorStack.pop();
            if (prevOperation == '(') {
                operatorStack.push(prevOperation);
                break;
            }

            int inPriority = 0;
            if (prevOperation == '+' || prevOperation == '-') {
                inPriority = 1;
            } else if (prevOperation == '*' || prevOperation == '/') {
                inPriority = 2;
            }
            if (inPriority < priority) {
                operatorStack.push(prevOperation);
                break;
            }
            numberBuffer.append(prevOperation);
        }
        operatorStack.push(operation);
    }

    private void readClose() {
        while (!operatorStack.isEmpty()) {
            char checkPop = operatorStack.pop();
            if (checkPop != '(') {
                numberBuffer.append(checkPop).append(',');
            }
        }
    }
}
