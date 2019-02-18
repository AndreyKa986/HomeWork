package lesson08;

import java.util.Random;
import java.util.Scanner;

public class Task1 {
    static int sing;
    static int key = 1;
    static int unluck;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        calculator(scanner);
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
        if (two == 0) {
            System.out.println("Айяяй, на 0 делить нельзя.");
            return divide(one,enterTwo(scanner),scanner);
        } else
            return one / two;
    }

    public static void getResult(double number) {
        unluck = new Random().nextInt(20);
        if (number > 100000 || number < -100000)
            System.out.println("Ой, очень длинное число, не могу вывести.)");
        else if(number%unluck==0){
            System.out.println("Упсс. Что-то пошло не так. Попробуйте снова.)\nunluck " + unluck);
        }
        else {
            if (number % 1 == 0)
                System.out.println("Результат: " + (int) number);
            else
                System.out.println("Результат: " + number);
        }
    }

    public static double enterOne(Scanner scanner) {
        System.out.println("Введите первое число:");
        double one = scanner.nextDouble();
        if (1000 < one || one < -1000) {
            System.out.println("Слишком большоё или маленькое число.");
            return enterOne(scanner);
        } else return one;
    }

    public static double enterTwo(Scanner scanner) {
        System.out.println("Введите второе число:");
        double two = scanner.nextDouble();
        if (1000 < two || two < -1000) {
            System.out.println("Слишком большоё или маленькое число.");
            return enterTwo(scanner);
        } else return two;
    }

    public static void calculator(Scanner scanner) {
        while (key != 0) {
            System.out.println("Введите номер операции:\n   1 - сложение.\n   2 - вычетание.\n   3 - умножение\n" +
                    "   4 - деление.\n   0 - завершение работы");
            sing = scanner.nextInt();
            switch (sing) {
                case 0:
                    key=0;
                    break;
                case 1:
                    getResult(addition(enterOne(scanner), enterTwo(scanner)));
                    break;
                case 2:
                    getResult(takeAway(enterOne(scanner), enterTwo(scanner)));
                    break;
                case 3:
                    getResult(multiply(enterOne(scanner), enterTwo(scanner)));
                    break;
                case 4:
                    getResult(divide(enterOne(scanner), enterTwo(scanner), scanner));
                    break;
                default:
                    System.out.println("Операции с таким номером нет.");
            }
            if (key != 0) {
                System.out.println("Желаете продолжить:\n   1 - да.\n   0 - нет.");
                key = scanner.nextInt();
            }
        }
    }
}