package lesson02;

public class Task4 {
    public static void main(String[] args) {
        int number = (int) (Math.random() * 900_000 + 100_000);
        String str = Integer.toString(number);
        int flag = 0;
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) >= str.charAt(i + 1)) {
                System.out.print(number + " - нет, не образуют");
                flag = 1;
                break;
            }
        }
        if(flag!=1)
            System.out.print(number+" - да, образуют");
    }
}
