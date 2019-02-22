package lesson10.task2;

public class Person {
    public String firstName;
    public String secondName;
    public String thirdName;

    public Person(String secondName, String firstName, String thirdName) {
        this.secondName = secondName;
        this.firstName = firstName;
        this.thirdName = thirdName;
    }

    public static void printDataPerson(Person person) {
        System.out.printf("%n%s %s %s%n%n", person.secondName, person.firstName, person.thirdName);
    }
}
