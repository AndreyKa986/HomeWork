package lesson03;

public class Task2 {
    public static int numberOfPages(int n) {
        int x = (int) Math.ceil(n*1.0 / 10);
        return x;
    }
}
