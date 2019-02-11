package lesson01;

public class Task2 {
    public static void main(String[] args) {
        int number = (int) (Math.random() * 20000 + 1) - 10000;
        int i = number % 10;
        System.out.println(number + "\n" + (i == 7 || i == -7 ? "последнее число 7" : "последнее число не 7"));
    }
}
