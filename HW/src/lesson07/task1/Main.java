package lesson07.task1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import static java.util.Calendar.*;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество студентов:");
        Student[] array = new Student[scanner.nextInt()];
        scanner.nextLine();
        inputStudent(array, scanner);
        scanner.close();
        printMiddleAge(searchMiddleAge(array));
    }

    public static void inputStudent(Student[] array, Scanner scanner) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        for (int i = 0; i < array.length; i++) {
            array[i] = new Student();
            System.out.println("Введите имя студента №" + (i + 1) + ":");
            array[i].fistName = scanner.nextLine();
            System.out.println("Введите фамилию студента №" + (i + 1) + ":");
            array[i].secondName = scanner.nextLine();
            System.out.println("Введите дату рождения студента №" + (i + 1) + ":");
            System.out.println("(Дату вводить в виде - 21.02.1969)");
            array[i].dateOfBirth = format.parse(scanner.nextLine());
        }
    }

    public static Date searchMiddleAge(Student[] array) {
        double middle = 0;
        for (Student i : array)
            middle += 1.0 * i.dateOfBirth.getTime() / array.length;
        Date mid = new Date((long) middle);
        return mid;
    }

    public static void printMiddleAge(Date mid) {
        Calendar middleAge = new GregorianCalendar();
        middleAge.setTime(mid);
        Calendar today = Calendar.getInstance();
        int day = 0;
        if (today.get(DAY_OF_MONTH) >= middleAge.get(DAY_OF_MONTH))
            day = today.get(DAY_OF_MONTH) - middleAge.get(DAY_OF_MONTH);
        else {
            today.add(DAY_OF_MONTH, -middleAge.get(DAY_OF_MONTH));
            day = today.get(DAY_OF_MONTH);
        }
        int month=0;
        if(today.get(MONTH)>=middleAge.get(MONTH))
            month=today.get(MONTH)-middleAge.get(MONTH);
        else{
            today.add(MONTH, -middleAge.get(MONTH));
            month=today.get(MONTH);
        }
        int year=today.get(YEAR)-middleAge.get(YEAR);
        System.out.println("Средний возраст:");
        System.out.println(year+" лет "+month+" мес. "+day+" дн.");
    }
}
