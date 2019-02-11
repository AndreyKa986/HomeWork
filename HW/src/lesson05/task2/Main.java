package lesson05.task2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AutomatedTellerMachine one = new AutomatedTellerMachine(100, 300, 500);
        Scanner scanner = new Scanner(System.in);
        one.terminal(scanner);
    }
}
