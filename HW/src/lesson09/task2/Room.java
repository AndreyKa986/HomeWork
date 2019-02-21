package lesson09.task2;

import java.util.ArrayList;

public class Room {
    public String name;
    public int window;
    public int lighting;
    public double usableArea=0;
    public double allArea;
    public ArrayList<LightBulb> lightBulbArrayList = new ArrayList<>();
    public ArrayList<Furniture> furnitureArrayList = new ArrayList<>();

    public Room(String name, double area, int window) throws IlluminanceTooMuchException {
        if (window > 0 && window < 6) {
            this.name = name;
            allArea = area;
            this.window = window;
            lighting = window * 700;
        } else throw new IlluminanceTooMuchException();
    }

    public void add(LightBulb lightBulb) throws IlluminanceTooMuchException {
        lightBulbArrayList.add(lightBulb);
        lighting += lightBulb.lighting;
        if (lighting > 4000)
            throw new IlluminanceTooMuchException();
    }

    public void add(Furniture furniture) throws SpaceUsageTooMuchException {
        furnitureArrayList.add(furniture);
        usableArea += furniture.area;
        if (usableArea * 100 / allArea > 70)
            throw new SpaceUsageTooMuchException();
    }
}
