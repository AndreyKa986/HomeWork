package lesson1;

public class Task2 {
    public static void main(String[] args) {
        int number = (int) (Math.random() * 10000 + 1);
        int i = number % 10;
        System.out.println(number + "\n" + (i == 7 ? "последнее число 7" : "последнее число не 7"));
    }
}
