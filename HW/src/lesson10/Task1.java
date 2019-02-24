package lesson10;

import java.util.*;

public class Task1 {
    public static void main(String[] args) {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        Scanner scanner = new Scanner(System.in);
        String temp = "";
        System.out.println("Введите набор строк.\n\"end\" - завершает работу ввода" +
                " и выводит на экран строки без повторения.\nИ без букв - a.");
        for (; ; ) {
            temp = scanner.nextLine();
            if (!temp.equals("end"))
                linkedHashSet.add(temp);
            else {
                scanner.close();
                break;
            }
        }
        LinkedList<String> list=new LinkedList<>();
        list.addAll(linkedHashSet);
        ListIterator<String> iterator=list.listIterator();
        while (iterator.hasNext())
            iterator.set(iterator.next().replace("a",""));
        System.out.println("Вывод:");
        for (String i: list)
            System.out.println(i);
    }
}
