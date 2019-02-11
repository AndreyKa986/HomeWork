package lesson01;

public class Task4 {
    public static void main(String[] args) {
        int cash = (int) (Math.random() * 1000 + 1);
        if (cash / 10 % 10 == 1 || cash % 10 == 0 || cash % 10 > 4)
            System.out.print(cash + " рублей");
        else if (cash % 10 == 1)
            System.out.print(cash + " рубль");
        else if (cash % 10 > 1 && cash % 10 < 5)
            System.out.print(cash + " рубля");
    }
}
