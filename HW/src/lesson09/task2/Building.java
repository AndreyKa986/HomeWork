package lesson09.task2;

import java.util.ArrayList;

public class Building {
    public String name;
    public ArrayList<Room> rooms = new ArrayList<>();

    public Building(String name) {
        this.name = name;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addLightBulb(String str, int light) throws IlluminanceTooMuchException {
        for (Room i : rooms) {
            if (i.name.equals(str)) {
                i.add(new LightBulb(light));
                break;
            }
        }
    }

    public void addFurniture(String str, String str2, double area) throws SpaceUsageTooMuchException {
        for (Room i : rooms) {
            if (i.name.equals(str)) {
                i.add(new Furniture(str2, area));
                break;
            }
        }
    }

    public void describe() {
        System.out.println(name);
        for (Room i : rooms) {
            System.out.println("  " + i.name);
            System.out.print("    Освещённость = " + i.lighting + " (" + i.window + " окна по 700 лк");
            if (!i.lightBulbArrayList.isEmpty()) {
                for (LightBulb j : i.lightBulbArrayList)
                    System.out.print(", лампачка " + j.lighting + " лк");
            }
            System.out.println(")");
            System.out.print("    Площадь = " + i.allArea + " м.кв. (");
            if (i.usableArea != 0)
                System.out.print("занято " + i.usableArea + " м.кв., ");
            System.out.println("свободно "+(100-i.usableArea*100/i.allArea)+"%)");
            if(!i.furnitureArrayList.isEmpty()){
                System.out.println("    Мебель:");
                for(Furniture j:i.furnitureArrayList)
                    System.out.println("      "+j.name+" (площадь "+j.area+" м.кв.)");
            }else
                System.out.println("    Мебели нет");
        }
    }
}
