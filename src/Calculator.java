import java.util.Objects;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Converter converter = new Converter();
        String[] sing = {"+", "-", "/", "*" };
        String[] regexSing = {"\\+", "-", "/", "\\*" };
        Scanner scn = new Scanner(System.in);

        System.out.print("Введите выражение: ");
        String input = scn.nextLine();

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
                String[] expression = input.split(regexSing[actionIndex]);
                if (expression.length > expressionLength) {
                    try {
                        throw new Exception();
                    } catch (Exception m) {
                        System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор3");
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
                        int result = switch (sing[actionIndex]) {
                            case "+" -> numberOne + numberTwo;
                            case "-" -> numberOne - numberTwo;
                            case "*" -> numberOne * numberTwo;
                            default -> numberOne / numberTwo;
                        };

                        if (isRomanDigits) {
                            if (Objects.equals(converter.arabicToRoman(result), "0")) {
                                try {
                                    throw new Exception();
                                } catch (Exception d) {
                                    System.out.println("Результатом работы с римскими числами могут быть только положительные числа");
                                }
                            } else {
                                System.out.println("Результат вашего выражения: " + converter.arabicToRoman(result));
                            }
                        } else {
                            System.out.println("Результат вашего выражения: " + result);
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
    }
}