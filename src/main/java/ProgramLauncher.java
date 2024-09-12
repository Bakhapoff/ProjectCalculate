import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.EmptyStackException;
import java.util.Scanner;

public class ProgramLauncher {
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private final Scanner scanner = new Scanner(System.in);

    public void startCalculator() {
        while (true) {
            try {
                System.out.print(ANSI_CYAN + "Введите выражение: " + ANSI_RESET);
                String inputExpression = new PolishNotationEvaluator(checkTheStaples(scanner.nextLine().
                        replaceAll(" ", "").replace(",","."))).convertToRPN();
                result(inputExpression, checkThePoint(inputExpression));
            } catch (ArithmeticException e) {
                System.out.println(ANSI_RED + "Ошибка! На ноль делить нельзя!" + ANSI_RESET);
            } catch (EmptyStackException | NumberFormatException e) {
                System.out.println(ANSI_RED + "Не валидное выражение!" + ANSI_RESET);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ANSI_RED + "Вы ничего не ввели!" + ANSI_RESET);
            }
        }
    }

    private static void result(String inputExpression, boolean checkThePoint) {
        if (checkThePoint) {
            BigDecimal decimalResult = new ReversePolishNotationCalculator(inputExpression).calculateRPNBigDecimal();
            System.out.println(ANSI_CYAN + decimalResult + ANSI_RESET);
        } else {
            BigInteger intResult = new ReversePolishNotationCalculator(inputExpression).calculateRPNBigInteger();
            System.out.println(ANSI_CYAN + intResult + ANSI_RESET);
        }
    }

    private static boolean checkThePoint(String inputExpression) {
        for (int i = 0; i < inputExpression.length(); i++) {
            if (inputExpression.charAt(i) == '.') {
                return true;
            }
        }
        return false;
    }

    private static String checkTheStaples(String inputExpression) {
        int staplesCounter = 0;
        for (int i = 0; i < inputExpression.length(); i++) {
            if (inputExpression.charAt(i) == '(' || inputExpression.charAt(i) == ')') {
                staplesCounter++;
            }
        }
        if (staplesCounter % 2 != 0) {
            throw new EmptyStackException();
        } else return inputExpression;
    }
}


