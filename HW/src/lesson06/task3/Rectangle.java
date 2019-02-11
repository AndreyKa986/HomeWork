package lesson06.task3;

public class Rectangle extends Figure {
    public double sideA;
    public double sideB;

    public Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    public double calculationArea() {
        square = sideA * sideB;
        return square;
    }
}
