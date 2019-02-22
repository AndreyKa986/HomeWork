package lesson10;

import java.util.LinkedHashSet;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        Scanner scanner = new Scanner(System.in);
        String temp = "";
        System.out.println("Введите набор строк.\n\"end\" - завершает работу ввода" +
                " и выводит на экран строки без повторения.");
        for(;;) {
            temp = scanner.nextLine();
            if (!temp.equals("end"))
                linkedHashSet.add(temp);
            else break;
        }
        System.out.println("Вывод:");
        for (String i : linkedHashSet)
            System.out.println(i);
        scanner.close();
    }
}
