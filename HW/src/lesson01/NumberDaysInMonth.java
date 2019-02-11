package lesson01;

public class NumberDaysInMonth {
    public static void main(String[] args) {
        int month = (int) (Math.random() * 12 + 1);
        int year = (int) (Math.random() * 50 + 1969);
        int days = month == 2 ? 28 + (year % 4 == 0 ? 1 : 0) - (year % 100 == 0 ? (year % 400 == 0 ? 0 : 1) : 0) : 31 - (month - 1) % 7 % 2;
        System.out.print("дней в месяце " + month + " и года " + year + " - " + days);
    }
}
