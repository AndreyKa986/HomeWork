package lesson08.task3;

import java.util.Date;

public class Student {
    public String firstName;
    public String secondName;
    public String thirdName;
    Date dateOfBirth;

    public Student(String firstName, String secondName, String thirdName, Date dateOfBirth) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.dateOfBirth = dateOfBirth;
    }
}
