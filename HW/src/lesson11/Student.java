package lesson11;

public class Student extends Man {
    public String course;
    public String specialization;

    public Student(String name, String course, String specialization) {
        this.name = name;
        this.course = course;
        this.specialization = specialization;
    }

    public String toString() {
        return "Голодный студент " + name + " " + course + "-го курса, обучающийся по специальности " + specialization;
    }
}
