import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.EmptyStackException;
import java.util.Scanner;

public class Run {
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private final Scanner scan = new Scanner(System.in);

    public void run() {
        while (true) {
            try {
                System.out.print(ANSI_CYAN + "Введите выражение: " + ANSI_RESET);
                String input = new TranslatePolandNotation(staples(scan.nextLine().
                        replaceAll(" ", ""))).getPolandNotationString();
                result(input, checkIntDouble(input));
            } catch (ArithmeticException e) {
                System.out.println(ANSI_RED + "Ошибка! На ноль делить нельзя!" + ANSI_RESET);
            } catch (EmptyStackException | NumberFormatException e) {
                System.out.println(ANSI_RED + "Не валидное выражение!" + ANSI_RESET);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ANSI_RED + "Вы ничего не ввели!" + ANSI_RESET);
            }
        }
    }

    private static void result(String input, int checkIntDouble) {
        if (checkIntDouble == 1) {
            BigDecimal res = new CalculatePolandNotation(input).calculatePolandBigDecimal();
            System.out.println(ANSI_CYAN + res + ANSI_RESET);
        } else {
            BigInteger result = new CalculatePolandNotation(input).calculatePolandBigInteger();
            System.out.println(ANSI_CYAN + result + ANSI_RESET);
        }
    }

    private static int checkIntDouble(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '.') {
                return 1;
            }
        }
        return 2;
    }

    private static String staples(String input) {
        int staplesCounter = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(' || input.charAt(i) == ')') {
                staplesCounter++;
            }
        }
        if (staplesCounter % 2 != 0) {
            throw new EmptyStackException();
        } else return input;
    }
}


