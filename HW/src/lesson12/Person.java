package lesson12;

import java.util.Arrays;

public class Person {
    public String name;
    public int age;
    public boolean isStudent;
    public Wife wife;
    public String[] pet;

    public Person() {
    }

    public Person(String name, int age, boolean isStudent, Wife wife, String[] pet) {
        this.name = name;
        this.age = age;
        this.isStudent = isStudent;
        this.wife = wife;
        this.pet = pet;
    }

    public void print() {
        System.out.println(name + " " + age + " " + isStudent);
        if (wife != null)
            System.out.println(wife.name + " " + wife.age);
        if (pet != null)
            System.out.println(Arrays.toString(pet));
    }
}
