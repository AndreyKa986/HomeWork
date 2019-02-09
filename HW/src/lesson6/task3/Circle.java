package lesson6.task3;

public class Circle extends Figure {
    public double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double calculationArea() {
        square = 3.14 * radius * radius;
        return square;
    }
}
