package lesson06.task3;

public class Square extends Figure {
    public double sideA;

    public Square(double sideA) {
        this.sideA = sideA;
    }

    public double calculationArea() {
        square = sideA * sideA;
        return square;
    }
}
