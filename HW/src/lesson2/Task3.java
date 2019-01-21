package lesson2;

import java.util.Arrays;

public class Task3 {
    public static void main(String[] args) {
        int[] array = new int[11];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 20);
            if (i != array.length - 1)
                System.out.print(array[i] + " ");
            else
                System.out.println(array[i]);
        }
        for (int i = 0, j = array.length - 1, temp; i < j; i++, j--) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1)
                System.out.print(array[i] + " ");
            else
                System.out.print(array[i]);
        }
    }
}