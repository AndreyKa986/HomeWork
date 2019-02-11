package lesson04;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество пациентов:");
        int numberOfPatients = scanner.nextInt();
        Patient[] listOfPatients = new Patient[numberOfPatients];
        for (int i = 0; i < listOfPatients.length; i++) {
            listOfPatients[i] = new Patient();
            System.out.println("Введите данные пациента номер " + (i + 1) + ":");
            dataInput(scanner, listOfPatients[i]);
        }
        System.out.println("Список пациентов:");
        for (Patient i : listOfPatients) {
            dataOutput(i);
        }
        System.out.println("Желаете произвести поиск по списку пациентов?");
        choice(scanner, listOfPatients);
        scanner.close();
    }

    public static void dataInput(Scanner scanner, Patient patient) {
        System.out.println("ФИО:");
        scanner.nextLine();
        patient.fsm = scanner.nextLine();
        System.out.println("Возраст:");
        patient.age = scanner.nextInt();
        System.out.println("Евляется ли граждининном РБ (да/нет):");
        if (scanner.next().equalsIgnoreCase("да"))
            patient.citizenshipOfRB = true;
    }

    public static void dataOutput(Patient patient) {
        System.out.println("Пациент \"" + patient.fsm + "\" - Возраст = \"" + patient.age + "\"");
    }

    public static void searchName(Patient[] patient, Scanner scanner) {
        System.out.println("Введите часть ФИО:");
        scanner.nextLine();
        String temp = scanner.nextLine();
        int j = 0;
        System.out.println("Результаты поиска:");
        for (int i = 0; i < patient.length; i++) {
            if (patient[i].fsm.toLowerCase().contains(temp.toLowerCase())) {
                dataOutput(patient[i]);
                j++;
            }
        }
        if (j == 0)
            System.out.println("Пациентов с такой частью имени не найдено.");
        newChance(scanner, patient);
    }

    public static void searchAge(Patient[] patient, Scanner scanner) {
        System.out.println("Введите искомый возраст:");
        int temp = scanner.nextInt();
        int j = 0;
        System.out.println("Результаты поиска:");
        for (int i = 0; i < patient.length; i++) {
            if (patient[i].age == temp) {
                dataOutput(patient[i]);
                j++;
            }
        }
        if (j == 0)
            System.out.println("Пациентов с таким возрастом не найдено.");
        newChance(scanner, patient);
    }

    public static void choice(Scanner scanner, Patient[] list) {
        System.out.println("1 - Поиск по части имени.\n2 - Поиск по возрасту.\n0 - Выход из поиска.");
        int temp2 = scanner.nextInt();
        switch (temp2) {
            case 1:
                searchName(list, scanner);
                break;
            case 2:
                searchAge(list, scanner);
                break;
            case 0:
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Введено не правильное значение.");
                choice(scanner, list);
        }
    }

    public static void newChance(Scanner scanner, Patient[] listOfPatients) {
        System.out.println("Хотите снова попробовать запустить поиск?(да/нет)");
        String text = scanner.next();
        if (text.equalsIgnoreCase("да"))
            choice(scanner, listOfPatients);
        else if (!text.equalsIgnoreCase("нет")) {
            System.out.println("Пожалуйста, только да или нет:)");
            newChance(scanner, listOfPatients);
        }
    }
}