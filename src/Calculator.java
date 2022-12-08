import java.util.Objects;
import java.util.Scanner;

public class Calculator {
    public static String calc(String input) {
        int result = 0;

        String[] sing = {"+", "-", "/", "*"};
        String[] regexSing = {"\\+", "-", "/", "\\*"};
        int expressionLength = 2;

        if (input.length() <= expressionLength) {
            try {
                throw new Exception();
            } catch (Exception k) {
                System.out.println("Строка не является математической операцией");
            }
        } else {

            int actionIndex = -1;

            for (int i = 0; i < sing.length; i++) {
                if (input.contains(sing[i])) {
                    actionIndex = i;
                    break;
                }
            }

            if (actionIndex == -1) {
                try {
                    throw new Exception();
                } catch (Exception d) {
                    System.out.println("Неверено задан оператор");
                }
            } else {
                Converter converter = new Converter();
                String[] expression = input.split(regexSing[actionIndex]);

                if (expression.length > expressionLength) {
                    try {
                        throw new Exception();
                    } catch (Exception m) {
                        System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор");
                    }
                } else if (converter.isNumberSystem(expression[0]) == converter.isNumberSystem(expression[1])) {
                    int numberOne, numberTwo;
                    boolean isRomanDigits = converter.isNumberSystem(expression[0]);

                    if (isRomanDigits) {
                        numberOne = converter.romanToArabic(expression[0]);
                        numberTwo = converter.romanToArabic(expression[1]);
                    } else {
                        numberOne = Integer.parseInt(expression[0]);
                        numberTwo = Integer.parseInt(expression[1]);
                    }

                    if (numberOne > 10 || numberTwo > 10) {
                        try {
                            throw new Exception();
                        } catch (Exception c) {
                            System.out.println("Размер вводимых чисел не удовлетворяет заданию");
                        }
                    } else {
                        result = switch (sing[actionIndex]) {
                            case "+" -> numberOne + numberTwo;
                            case "-" -> numberOne - numberTwo;
                            case "*" -> numberOne * numberTwo;
                            default -> numberOne / numberTwo;
                        };

                        if (isRomanDigits && result <= 0) {
                            try {
                                throw new Exception();
                            } catch (Exception d) {
                                System.out.println("Результатом работы с римскими числами могут быть только положительные числа");
                            }
                        }

                        if (isRomanDigits) {
                            return converter.arabicToRoman(result);
                        } else {
                            return String.valueOf(result);
                        }
                    }
                } else {
                    try {
                        throw new Exception();
                    } catch (Exception var19) {
                        System.out.println("Используются одновременно разные системы счисления");
                    }
                }
            }
        }
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.print("Введите выражение: ");
        String input = scn.nextLine();

        if (!Objects.equals(calc(input), "0")) System.out.println("Резултат вашего выражения: " + calc(input));
    }
}