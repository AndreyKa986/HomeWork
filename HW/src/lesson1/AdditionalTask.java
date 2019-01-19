package lesson1;

public class AdditionalTask {
    public static void main(String[] args) {
        double x = 10;
        double y;
        double t = 5;
        double s = 3;
        y = Math.sqrt(Math.sin(Math.pow(x, t))) / Math.pow((1 + Math.pow(x, s)), 1 / 2);
        System.out.print(y);
    }
}
