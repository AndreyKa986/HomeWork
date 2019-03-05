package lesson13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    public static void main(String[] args) {
        String string1="hello,,woooorlllllddd";
        String string2="aaabbaaacccbb";
        String string3="succes.sfully";
        System.out.println(stringWithoutDuplicateCharacter(string1));
        System.out.println(stringWithoutDuplicateCharacter(string2));
        System.out.println(stringWithoutDuplicateCharacter(string3));
    }
    public static String stringWithoutDuplicateCharacter (String string){
        Pattern pattern = Pattern.compile("(.)\\1+");
        Matcher matcher=pattern.matcher(string);
        char character;
        String replaceablePartOfString;
        String replacement;
        String newString=string;
        while (matcher.find()){
            character=string.charAt(matcher.start());
            replaceablePartOfString=string.substring(matcher.start(),matcher.end());
            replacement=String.valueOf(character)+(matcher.end()-matcher.start());
            newString=newString.replaceFirst(replaceablePartOfString,replacement);
        }
        return newString;
    }
}
