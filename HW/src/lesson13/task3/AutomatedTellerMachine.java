package lesson13.task3;

import java.util.Scanner;

public class AutomatedTellerMachine {
    public int numberOfTwenty;
    public int numberOfFifty;
    public int numberOfHundred;
    public int amoun;
    public Cash cash = new Cash(0, 0, 0);

    public AutomatedTellerMachine(int numberOfTwenty, int numberOfFifty, int numberOfHundred) {
        this.numberOfTwenty = numberOfTwenty;
        this.numberOfFifty = numberOfFifty;
        this.numberOfHundred = numberOfHundred;
    }

    public boolean isPossible(int amoun) {
        if (amoun % 10 == 0 && amoun >= 40 || amoun == 20)
            return true;
        else
            return false;
    }

    public Cash operation(int amoun) {
        int twenty = 0;
        int fifty = 0;
        int hundred = 0;
        hundred += (amoun - 40) / 100;
        switch ((amoun - 40) % 100 + 40) {
            case 20:
                twenty++;
                break;
            case 40:
                twenty += 2;
                break;
            case 50:
                fifty++;
                break;
            case 60:
                twenty += 3;
                break;
            case 70:
                fifty++;
                twenty++;
                break;
            case 80:
                twenty += 4;
                break;
            case 90:
                fifty++;
                twenty += 2;
                break;
            case 100:
                hundred++;
                break;
            case 110:
                fifty++;
                twenty += 3;
                break;
            case 120:
                hundred++;
                twenty++;
                break;
            case 130:
                fifty++;
                twenty += 4;
                break;
        }
        Cash cash = new Cash(twenty, fifty, hundred);
        return cash;
    }

    public void withdrawal(Cash cash) {
        numberOfTwenty -= cash.twenty;
        numberOfFifty -= cash.fifty;
        numberOfHundred -= cash.hundred;
    }

    public boolean isPossibleToIssue(Cash cash) {
        if (numberOfTwenty >= cash.twenty && numberOfFifty >= cash.fifty && numberOfHundred >= cash.hundred)
            return true;
        else
            return false;
    }

    public void adding(Cash cash) {
        numberOfTwenty += cash.twenty;
        numberOfFifty += cash.fifty;
        numberOfHundred += cash.hundred;
    }

    public void withdrawalOfMoney(Cash cash) {
        if (cash.hundred > 0)
            System.out.println(cash.hundred + " купюр. наминалом 100");
        if (cash.fifty > 0)
            System.out.println(cash.fifty + " купюр. наминалом 50");
        if (cash.twenty > 0)
            System.out.println(cash.twenty + " купюр. наминалом 20");
    }

    public void terminal(Scanner scanner) {
        int x = -1;
        while (x != 0) {
            System.out.println("Выберети действие:\n   1 - Внести средства / Пополнить счёт.\n   " +
                    "2 - Снять наличные.\n   0 - Выход");
            x = scanner.nextInt();
            switch (x) {
                case 1:
                    System.out.println("Введите сумму (терминал принимает купюры - 20, 50, 100):");
                    amoun = scanner.nextInt();
                    if (isPossible(amoun)) {
                        cash = operation(amoun);
                        adding(cash);
                        System.out.println("Операция прошла успешно");
                    } else
                        System.out.println("Отмена операции. Не правильно введина сумма");
                    break;
                case 2:
                    System.out.println("Введите сумму (терминал выдаёт купюры - 20, 50, 100):");
                    amoun = scanner.nextInt();
                    if (isPossible(amoun)) {
                        cash = operation(amoun);
                        if (isPossibleToIssue(cash)) {
                            withdrawal(cash);
                            System.out.println("Заберите деньги:");
                            withdrawalOfMoney(cash);
                        } else
                            System.out.println("Просим прощения, но в терминале отсутствует необходимая сумма.");
                    } else
                        System.out.println("Отмена операции. Не правильно введина сумма");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Неправильно введина цифра.");
            }
            if (x != 0) {
                System.out.println("Желаете продолжить?\n   да - любое целое число\n   нет - 0");
                x = scanner.nextInt();
            }
        }
    }
}
