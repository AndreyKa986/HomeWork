package lesson02;

public class Task5 {
    public static void main(String[] args) {
        int size=(int)(Math.random()*10+10);
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++)
            array[i] = (int) (Math.random() * 100);
        int n = size / 2;
        for (; n > 0; ) {
            int flag = 0;
            for (int a = 0; a + n < size; a++) {
                if (array[a] > array[a + n]) {
                    int c = array[a];
                    array[a] = array[a + n];
                    array[a + n] = c;
                    flag = 1;
                }
            }
            if (flag == 0)
                n /= 2;
        }
        for(int i:array)
            System.out.print(i+" ");
    }
}
