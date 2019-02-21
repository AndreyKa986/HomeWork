package lesson09;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        LinkedList <String> list=new LinkedList<>();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Заполните массив данными типа String.\nВведите \"off\" для завершения ввода.");
        int key=1;
        String temp;
        while (key!=0){
            temp=scanner.nextLine();
            if(!temp.equalsIgnoreCase("off"))
                list.add(temp);
            else key=0;
        }
        scanner.close();
        ListIterator<String>iterator=list.listIterator();
        while (iterator.hasNext())
            iterator.set(iterator.next().replace("a",""));
        for (String i: list)
            System.out.println(i);
    }
}
