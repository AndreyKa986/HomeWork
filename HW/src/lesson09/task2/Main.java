package lesson09.task2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IlluminanceTooMuchException,SpaceUsageTooMuchException {
        System.out.println("Введите название здания:");
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();
        Building building = new Building(temp);
        int key = 1;
        String name;
        String name2;
        double area;
        int number;
        while (key != 0) {
            System.out.println("1 - Добавить комноту.\n2 - Добавить лампочку в комнату.\n3 - Добавить мебель в " +
                    "комнату.\n0 - Вывести информацию по зданию и выйти.");
            key=scanner.nextInt();
            scanner.nextLine();
            switch (key) {
                case 1:
                    System.out.println("Введите название комнаты:");
                    name=scanner.nextLine();
                    System.out.println("Введите площадь комнаты:");
                    area=scanner.nextDouble();
                    System.out.println("Введите количество окон в комнате:");
                    number=scanner.nextInt();
                    building.addRoom(new Room(name,area,number));
                    break;
                case 2:
                    System.out.println("Введите название комнаты:");
                    name=scanner.nextLine();
                    System.out.println("Введите мощность лампочки:");
                    number=scanner.nextInt();
                    building.addLightBulb(name,number);
                    break;
                case 3:
                    System.out.println("Введите название комнаты:");
                    name=scanner.nextLine();
                    System.out.println("Введите название мебели:");
                    name2=scanner.nextLine();
                    System.out.println("Введите площадь мебели:");
                    area=scanner.nextDouble();
                    building.addFurniture(name,name2,area);
                    break;
                case 0:
                    building.describe();
                    key=0;
                    break;
            }
        }
        scanner.close();
    }
}
