package lesson15;

import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {
        StringMaker thread1 = new StringMaker();
        StringMaker thread2 = new StringMaker();
        thread1.setName("Thread-1");
        thread2.setName("Thread-2");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для запуска приложения введите - start");
        String string = scanner.nextLine();
        while (true) {
            if (!string.equalsIgnoreCase("start")) {
                System.out.println("Вы ввели - " + string);
                System.out.println("А надо - start. Введите заново:");
                string = scanner.nextLine();
            } else {
                thread1.start();
                thread2.start();
                break;
            }
        }
    }

    private synchronized static void print10(int[] array) {
        System.out.print(Thread.currentThread().getName());
        for (int i : array) {
            System.out.printf("%4d", i);
        }
        System.out.println();
    }

    public static class StringMaker extends Thread {
        @Override
        public void run() {
            int number = 0;
            for (int i = 0; i < 10; i++) {
                int[] array = new int[10];
                for (int j = 0; j < 10; j++) {
                    array[j] = ++number;
                }
                print10(array);
            }
        }
    }

}
