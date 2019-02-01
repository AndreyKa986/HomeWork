package lesson4;

public class Patient {
    public String fistName;
    public String secondName;
    public String middleName;
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
