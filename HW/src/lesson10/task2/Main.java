package lesson10.task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import static lesson10.task2.Person.printDataPerson;

public class Main {
    public static void main(String[] args) {
        ArrayList<Person> male = new ArrayList<>();
        ArrayList<Person> female = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int key = 1;
        while (key != 0) {
            System.out.println("1 - Добавить мужское ФИО\n2 - Добавить женское ФИО\n0 - Перейти к выводу");
            key = scanner.nextInt();
            scanner.nextLine();
            switch (key) {
                case 1:
                    male.add(setPerson(scanner));
                    break;
                case 2:
                    female.add(setPerson(scanner));
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Нет операции с таким методом.");
            }
        }
        Random random = new Random();
        int i;
        ArrayList<Person> s;
        HashMap<String, ArrayList> hashMap = new HashMap<>();
        hashMap.put("man", male);
        hashMap.put("woman", female);
        while (key != -1) {
            System.out.println("1 - Вывести мужское ФИО\n2 - Вывести женское ФИО\n0 - Выйти");
            key = scanner.nextInt();
            scanner.nextLine();
            switch (key) {
                case 1:
                    i = random.nextInt(hashMap.get("man").size());
                    s = hashMap.get("man");
                    printDataPerson(s.get(i));
                    break;
                case 2:
                    i = random.nextInt(hashMap.get("woman").size());
                    s = hashMap.get("woman");
                    printDataPerson(s.get(i));
                    break;
                case 0:
                    key = -1;
                    scanner.close();
                    break;
                default:
                    System.out.println("Нет операции с таким методом.");
            }
        }
    }

    public static Person setPerson(Scanner scanner) {
        System.out.println("Введите фамилию:");
        String secondName = scanner.nextLine();
        System.out.println("Введите имя:");
        String firstName = scanner.nextLine();
        System.out.println("Введите отчество:");
        String thirdName = scanner.nextLine();
        return new Person(secondName, firstName, thirdName);
    }
}
