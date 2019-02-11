package lesson04;

public class Patient {
    public String fistName;
    public String secondName;
    public String middleName;
    public String fsm;
    public int age;
    public String diagnosis = " - ";
    public boolean citizenshipOfRB;
    private String specialMarks;

    public void complaints(String complaints) {
        System.out.print(complaints);
    }

    public String getSpecialMarks() {
        return specialMarks;
    }

    public void setSpecialMarks(String specialMarks) {
        this.specialMarks = specialMarks;
    }
}
