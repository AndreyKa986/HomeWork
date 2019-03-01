package lesson11;

public class Member extends Man {
    public String position;

    public Member(String name, String position) {
        this.name = name;
        this.position = position;
    }
    public String toString(){
        return "Сотрудник обслуживающего персонала "+name+", должность: "+position;
    }
}
