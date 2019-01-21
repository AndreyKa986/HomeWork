package lesson2;

import java.util.Arrays;

public class Task1 {
    public static void main(String[] args) {
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++)
            array[i] = (int) (Math.random() * 98 + 1);
        int min = array[0];
        int max = array[0];
        int temp;
        int minNumber = 0;
        int maxNumber = 0;
        for (int i = 1; i < array.length; i++) {
            if (min > array[i]) {
                min = array[i];
                minNumber = i;
            }
            if (max < array[i]) {
                max = array[i];
                maxNumber = i;
            }
        }
        System.out.println("min value = " + min);
        System.out.println("max value = " + max);
        array[minNumber] = 0;
        array[maxNumber] = 99;
        System.out.print(Arrays.toString(array));
    }
}
