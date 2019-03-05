package lesson13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {
    public static void main(String[] args) {
        String string1 = "music.mp3";
        String string2 = "Hello.worlD.docx";
        String string3 = "temp.$$$";
        String string4 = "TadaM.";
        System.out.println(getFileExtension(string1));
        System.out.println(getFileExtension(string2));
        System.out.println(getFileExtension(string3));
        System.out.println(getFileExtension(string4));
    }

    public static String getFileExtension(String string) {
        Pattern pattern = Pattern.compile("\\.([^\\.])+$");
        Matcher matcher = pattern.matcher(string);
        int i = 0;
        while (matcher.find())
            i = matcher.start();
        if (i != 0)
            return string.substring(i + 1);
        else return "Расширение файла отсутствует.";
    }
}
