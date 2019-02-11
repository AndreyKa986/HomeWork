package lesson01;

public class Task3 {
    public static void main(String[] args) {
        int a = (int) (Math.random() * 100 + 1);
        int b = (int) (Math.random() * 100 + 1);
        int r = (int) (Math.random() * 75 + 1);
        System.out.println("сторона a = " + a + "\n" + "сторона b = " + b + "\n" + "радиус = " + r);
        System.out.println(a * a + b * b <= 4 * r * r ? "закроет" : "не закроет");
    }
}
