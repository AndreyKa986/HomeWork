package lesson1;

public class Task1 {
    public static void main(String[] Args) {
        String text = "произвольная тестовая строка";
        int textLength = text.length();
        System.out.println("Количество символов в строке - " + textLength);
        String textBegan = text.substring(0, textLength / 2);
        String textEnd = text.substring(textLength / 2);
        System.out.println("начало - " + textBegan + "\n" + "конец - " + textEnd);
    }
}
