package lesson11;

public class Professor extends Man {
    public String experience;
    public String discipline;

    public Professor(String name, String experience, String discipline) {
        this.name = name;
        this.experience = experience;
        this.discipline = discipline;
    }

    public String toString() {
        return "Профессор " + name + ", обладающий опытом: \"" + experience + "\", преподаёт дисциплину " + discipline;
    }
}
