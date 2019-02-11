package lesson06.task1;

import java.util.Scanner;

public abstract class AutomatedTellerMachine {
    public int numberOfTwenty;
    public int numberOfFifty;
    public int numberOfHundred;
    public int amoun;
    public String bank;
    public Cash cash=new Cash(0,0,0);


    public abstract Cash operation(int amoun);

    public abstract void terminal(Scanner scanner);

    public abstract boolean isPossible(int amoun);

    public abstract boolean isPossibleToIssue(Cash cash);
}
