package lesson5.task2;

import java.util.Scanner;

public class AutomatedTellerMachine {
    private int twenty;
    private int fifty;
    private int hundred;

    public AutomatedTellerMachine(int twenty, int fifty, int hundred) {
        this.twenty = twenty;
        this.fifty = fifty;
        this.hundred = hundred;
    }

    public int getTwenty() {
        return twenty;
    }

    public void setTwenty(int twenty) {
        this.twenty = twenty;
    }

    public int getFifty() {
        return fifty;
    }

    public void setFifty(int fifty) {
        this.fifty = fifty;
    }

    public int getHundred() {
        return hundred;
    }

    public void setHundred(int hundred) {
        this.hundred = hundred;
    }

    public boolean adding(int sum) {
        if (sum % 10 == 0 && sum >= 40) {
            hundred += (sum - 40) / 100;
            switch ((sum - 40) % 100 + 40) {
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
            return true;
        }
        if (sum == 20) {
            twenty++;
            return true;
        } else
            return false;
    }

    public void withdrawal(int sum) {
        int h = 0;
        int f = 0;
        int t = 0;
        if (sum % 10 == 0 && sum >= 40) {
            hundred -= h = (sum - 40) / 100;
            switch ((sum - 40) % 100 + 40) {
                case 40:
                    twenty -= 2;
                    t += 2;
                    break;
                case 50:
                    fifty--;
                    f++;
                    break;
                case 60:
                    twenty -= 3;
                    t += 3;
                    break;
                case 70:
                    fifty--;
                    twenty--;
                    f++;
                    t++;
                    break;
                case 80:
                    twenty -= 4;
                    t += 4;
                    break;
                case 90:
                    fifty--;
                    twenty -= 2;
                    f++;
                    t += 2;
                    break;
                case 100:
                    hundred--;
                    h++;
                    break;
                case 110:
                    fifty--;
                    twenty -= 3;
                    f++;
                    t += 3;
                    break;
                case 120:
                    hundred--;
                    twenty--;
                    h++;
                    t++;
                    break;
                case 130:
                    fifty--;
                    twenty -= 4;
                    f++;
                    t += 4;
                    break;
            }
        } else if (sum == 20) {
            twenty--;
            t++;
        } else System.out.println("Неправильно выбрана сумма.");
        if (h > 0 || f > 0 || t > 0)
            System.out.println("   ***");
        if (h > 0)
            System.out.println("100 - " + h);
        if (f > 0)
            System.out.println(" 50 - " + f);
        if (t > 0)
            System.out.println(" 20 - " + t);
        if (h > 0 || f > 0 || t > 0)
            System.out.println("   ***");
    }

    public void terminal(Scanner scanner) {
        int x = -1;
        while (x != 0) {
            System.out.println("Выберети действие:\n   1 - Внести средства / Пополнить счёт.\n   " +
                    "2 - Снять наличные.\n   0 - Выход");
            x = scanner.nextInt();
            if (x == 1) {
                System.out.println("Введите сумму (терминал принимает купюры - 20, 50, 100):");
                int y = scanner.nextInt();
                boolean z = adding(y);
                if (z == true)
                    System.out.println("Операция прошла успешно");
                else
                    System.out.println("Отмена операции. Не правильно введина сумма");
            }
            if (x == 2) {
                System.out.println("Введите сумму (терминал выдаёт купюры - 20, 50, 100):");
                int y = scanner.nextInt();
                withdrawal(y);
            }
        }
    }
}
