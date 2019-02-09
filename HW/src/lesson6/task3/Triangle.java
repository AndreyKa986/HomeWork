package lesson6.task3;

import static java.lang.Math.sqrt;

public class Triangle extends Figure {
    public double sideA;
    public double sideB;
    public double sideC;
    public double semiPerimeter = (sideA + sideB + sideC) / 2;

    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public double calculationArea() {
        square = sqrt(semiPerimeter * (semiPerimeter - sideA) * (semiPerimeter - sideB) * (semiPerimeter - sideC));
        return square;
    }
}
