package lesson01;

public class Task5 {
    public static void main(String[] args) {
        String text = "?про#изв?о#льная? #тестов#?ая с#тро?ка";
        text = text.replace("?", "Hello");
        text = text.replace("#", "");
        System.out.print(text);
    }
}
