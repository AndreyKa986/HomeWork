package lesson06.task1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ATMofBelGazPromBank one=new ATMofBelGazPromBank(100,100,100);
        Scanner scanner=new Scanner(System.in);
        one.terminal(scanner);
        scanner.close();
    }
}
