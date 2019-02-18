package lesson08.task2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = 1;
        double numberOne = 1;
        String sing = "?";
        double numberTwo = 1;
        calculator(scanner, numberOne, numberTwo, sing, k);
        scanner.close();
    }

    public static double addition(double one, double two) {
        return one + two;
    }

    public static double takeAway(double one, double two) {
        return one - two;
    }

    public static double multiply(double one, double two) {
        return one * two;
    }

    public static double divide(double one, double two, Scanner scanner) {
        try {
            if (two == 0)
                throw new NullException();
            else return one / two;
        } catch (NullException e) {
            e.getRussianMessage();
            System.out.println("Введите число отличное от 0.");
            return one / setNumber(scanner);
        }
    }

    public static void getResult(double number) {
        try {
            if (number % 13 == 0)
                throw new ThirteenException();
            else if (number % 1 == 0)
                System.out.println("Результат: " + (int) number);
            else
                System.out.println("Результат: " + number);
        } catch (ThirteenException e) {
            e.getRussianMessage();
        }
    }

    public static double setNumber(Scanner scanner) {
        double number;
        try {
            if (scanner.hasNextDouble())
                return number = scanner.nextDouble();
            else {
                throw new EnterException();
            }
        } catch (EnterException e) {
            e.getRussianMessage();
            System.out.println("Введите заново число:");
            scanner.nextLine();
            return setNumber(scanner);
        }
    }

    public static void calculator(Scanner scanner, double one, double two, String sing, int key) {
        while (key != 0) {
            System.out.println("Введите через пробел или ввод\nзнак_операции число_1 число_2\noff - для выхода\n" +
                    "Пример(+ 12 12):");
            sing = scanner.next();
            if (!sing.equalsIgnoreCase("off")) {
                one = setNumber(scanner);
                two = setNumber(scanner);
                switch (sing) {
                    case "+":
                        getResult(addition(one, two));
                        break;
                    case "-":
                        getResult(takeAway(one, two));
                        break;
                    case "*":
                        getResult(multiply(one, two));
                        break;
                    case "/":
                        getResult(divide(one, two, scanner));
                        break;
                    default:
                        System.out.println("Не правльно введён символ операции.\nПопробуйте снова(+, -, *, /)");
                        calculator(scanner, one, two, sing, key);
                }
            } else
                key = 0;
        }
    }
}
