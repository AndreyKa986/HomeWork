package lesson08.task3;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static LinkedList<Student> listOfStudent = new LinkedList<>();
    static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        read();
        int key=1;
        while (key!=0){
            System.out.println("1 - добавить нового студента.\n2 - очистить список.\n3 - Сохранить и выйти.");
            key=scanner.nextInt();
            switch (key){
                case 1:
                    add(scanner);
                    break;
                case 2:
                    deleteAll();
                    break;
                case 3:
                    write();
                    key=0;
            }
        }
        scanner.close();
    }

    public static void read() throws IOException, ParseException {
        try (FileReader reader = new FileReader("Students.txt")){
            String temp = "";
            int i;
            while ((i = reader.read()) != -1) {
                temp += (char) i;
            }
            if (!temp.isEmpty()) {
                String[] arrayTemp = temp.split("\n");
                for (int j = 0; j < arrayTemp.length; j++) {
                    String[] tempTemp = arrayTemp[j].split(" ");
                    String firstName = tempTemp[1];
                    String secondName = tempTemp[0];
                    String thirdName = tempTemp[2];
                    Date dateOfBirth = format.parse(tempTemp[3]);
                    Student student = new Student(firstName, secondName, thirdName, dateOfBirth);
                    listOfStudent.add(student);
                }
                System.out.println("Список студентов успешно загружен.");
            } else System.out.println("Список пуст.");
        } catch (FileNotFoundException e) {
            File file = new File("Students.txt");
            file.createNewFile();
            System.out.println("Создан новый файл.");
        }
    }

    public static void write() throws IOException {
        FileWriter writer = new FileWriter("Students.txt", false);
        for (int i = 0; i < listOfStudent.size(); i++) {
            Student student = listOfStudent.get(i);
            writer.write(student.secondName + " ");
            writer.write(student.firstName + " ");
            writer.write(student.thirdName + " ");
            writer.write(format.format(student.dateOfBirth) + "\n");
        }
        writer.flush();
        writer.close();
        System.out.println("Список успешно сохранён.\nЗавершение работы.");
    }

    public static void deleteAll() {
        listOfStudent.clear();
    }

    public static void add(Scanner scanner) throws ParseException {
        System.out.println("Фамилия:");
        String secondName = scanner.next();
        System.out.println("Имя:");
        String firstName = scanner.next();
        System.out.println("Отчество:");
        String thirdName = scanner.next();
        System.out.println("Дата рождения (дд.мм.гггг):");
        Date dateOfBirth = format.parse(scanner.next());
        Student student = new Student(firstName, secondName, thirdName, dateOfBirth);
        listOfStudent.add(student);
        System.out.println("Операция прошла успешно.");
    }
}
