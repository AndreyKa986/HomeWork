package lesson02;

import java.util.Random;

public class Task2 {
    public static void main(String[] args) {
        float[] array = new float[10];
        for (int i = 0; i < array.length; i++)
            array[i] = Math.round(new Random().nextFloat() * 5 + 1);
        for (int i = 0; i < array.length - 1; i++) {
            float temp = array[i];
            int quant = 1;
            int flag = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (temp == array[j]) {
                    flag++;
                    break;
                }
            }
            if (flag == 1)
                continue;
            for (int a = i + 1; a < array.length; a++) {
                if (temp == array[a])
                    quant++;
            }
            if (quant != 1)
                System.out.println("[" + (int) (array[i]) + "] - повторений " + quant);
        }
    }
}
