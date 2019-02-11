package lesson06;

import java.util.Arrays;

import static java.lang.Math.random;
import static java.lang.System.arraycopy;

public class Task2 {
    public static void main(String[] args) {
        int[] array1 = new int[10];
        int[] array2 = new int[20];
        input(array1);
        input(array2);
        arraycopy(array1, 0, array2, ((array2.length - array1.length) / 2), array1.length);
        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
    }

    public static void input(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (random()*100);
    }
}
