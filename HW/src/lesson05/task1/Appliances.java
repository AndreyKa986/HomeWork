package lesson05.task1;

public abstract class Appliances {
    String manufacturer;
    String colour;
    int height;
    int width;
    int depth;
    int volume;

    int volume(int height, int width, int depth) {
        int volume = height * width * depth;
        return volume;
    }

    abstract void work();
}
