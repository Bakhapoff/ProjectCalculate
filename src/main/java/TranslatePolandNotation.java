import java.util.Stack;

public class TranslatePolandNotation {
    private final String input;
    private final StringBuilder sb = new StringBuilder();
    private final Stack<Character> stack = new Stack<>();


    public TranslatePolandNotation(String input) {
        this.input = input;
    }

    public String getPolandNotationString() {
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            switch (currentChar) {
                case '+':
                    if (sb.charAt(sb.length() - 1) == ',') {
                        readOperation(currentChar, 1);
                    } else {
                        sb.append(',');
                        readOperation(currentChar, 1);
                    }
                    break;
                case '-':
                    if (i == 0 || input.charAt(i - 1) == '(' || "+-*/".indexOf(input.charAt(i - 1)) != -1) {
                        sb.append(currentChar);
                    } else {
                        checkComma();
                        readOperation(currentChar, 1);
                    }
                    break;
                case '*':
                case '/':
                    if (sb.charAt(sb.length() - 1) == ',') {
                        readOperation(currentChar, 2);
                    } else {
                        sb.append(',');
                        readOperation(currentChar, 2);
                    }
                    break;
                case '(':
                    stack.push(currentChar);
                    break;
                case ')':
                    sb.append(',');
                    readClose();
                    break;
                default:
                    if (!sb.isEmpty() && sb.charAt(sb.length() - 1) == '+') {
                        sb.append(',').append(currentChar);
                    } else {
                        sb.append(currentChar);
                    }
                    break;
            }
        }
        checkComma();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        checkComma();
        return sb.toString();
    }

    private void checkComma() {
        if (sb.charAt(sb.length() - 1) != ',') {
            sb.append(',');
        }
    }

    private void readOperation(char operation, int priority) {
        while (!stack.isEmpty()) {
            char prevOperation = stack.pop();
            if (prevOperation == '(') {
                stack.push(prevOperation);
                break;
            }

            int inPriority = 0;
            if (prevOperation == '+' || prevOperation == '-') {
                inPriority = 1;
            } else if (prevOperation == '*' || prevOperation == '/') {
                inPriority = 2;
            }
            if (inPriority < priority) {
                stack.push(prevOperation);
                break;
            }
            sb.append(prevOperation);
        }
        stack.push(operation);
    }

    private void readClose() {
        while (!stack.isEmpty()) {
            char checkPop = stack.pop();
            if (checkPop != '(') {
                sb.append(checkPop).append(',');
            }
        }
    }
}
