package lesson03;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        int[] array = new int[10];
        Scanner scan = new Scanner(System.in);
        inter(array, scan);
        out(array);
        choice(array, scan);
        scan.close();
        out(array);
        outEvenNumbers(array);
    }

    public static void inter(int mass[], Scanner scan) {
        System.out.println("Введите 10 чисел:");
        for (int i = 0; i < mass.length; i++)
            mass[i] = scan.nextInt();
    }

    public static void out(int mass[]) {
        System.out.print("\"" + mass[0] + "\"");
        for (int i = 1; i < mass.length; i++)
            System.out.print(" | \"" + mass[i] + "\"");
    }

    public static void shellSort(int mass[]) {
        for (int i = mass.length / 2; i > 0; i /= 2) {
            for (int j = i; j < mass.length; j += i) {
                for (int k = j; k > 0; k -= i) {
                    if (mass[k] < mass[k - i])
                        replace(mass, k, k - i);
                    else
                        break;
                }
            }
        }
    }

    public static int sizeArray;

    public static void heapSort(int mass[]) {
        sizeArray = mass.length;
        for (int i = mass.length / 2 - 1; i >= 0; i--)
            compare(mass, i);
        for (int i = mass.length - 1; i > 0; i--) {
            replace(mass, i, 0);
            sizeArray--;
            compare(mass, 0);
        }
    }

    public static void compare(int ar[], int pos) {
        int pos1 = pos * 2 + 1;
        int pos2 = pos * 2 + 2;
        int max = pos;
        if (pos1 < sizeArray && ar[pos1] > ar[pos])
            max = pos1;
        if (pos2 < sizeArray && ar[pos2] > ar[pos] && ar[pos2] > ar[pos1])
            max = pos2;
        if (max != pos) {
            replace(ar, max, pos);
            compare(ar, max);
        }
    }

    public static void replace(int[] a, int b, int c) {
        int temp = a[b];
        a[b] = a[c];
        a[c] = temp;
    }

    public static void timSort(int mass[]) {
        int n = (int) Math.sqrt(mass.length);
        for (int i = 0; i < mass.length; i += n) {
            for (int j = i + 1; j < mass.length; j++) {
                if (j < i + n) {
                    for (int k = j; k > i; k--) {
                        if (mass[k] < mass[k - 1])
                            replace(mass, k, k - 1);
                        else
                            break;
                    }
                } else
                    break;
            }
        }
        int[] mass2 = new int[mass.length];
        while (n < mass.length) {
            for (int j = 0, i = 0; i < mass.length; i += 2 * n) {
                for (int k = i, l = i + n; j < i + 2 * n; j++) {
                    if (l < mass.length) {
                        if (k < i + n && l < i + 2 * n) {
                            if (mass[k] > mass[l]) {
                                mass2[j] = mass[l];
                                l++;
                            } else {
                                mass2[j] = mass[k];
                                k++;
                            }
                            continue;
                        }
                        if (k < i + n) {
                            mass2[j] = mass[k];
                            k++;
                            continue;
                        }
                        if (l < i + 2 * n) {
                            mass2[j] = mass[l];
                            l++;
                            continue;
                        }
                    }
                    if (k < mass.length && k < i + n) {
                        mass2[j] = mass[k];
                        k++;
                        continue;
                    }
                    if (j == mass.length)
                        break;
                }
            }
            for (int i = 0; i < mass.length; i++)
                mass[i] = mass2[i];
            n *= 2;
        }
    }

    public static void quickSort(int mass[]) {
        int began = 0;
        int end = mass.length - 1;
        goSort(mass, began, end);
    }

    public static void goSort(int[] mass, int began, int end) {
        if (began >= end)
            return;
        int i = began;
        int j = end;
        int middle = began - (began - end) / 2;
        while (i < j) {
            while (mass[i] <= mass[middle] && i < middle)
                i++;
            while (mass[middle] <= mass[j] && j > middle)
                j--;
            if (i < j) {
                replace(mass, i, j);
                if (i == middle)
                    middle = j;
                else if (j == middle)
                    middle = i;
            }
        }
        goSort(mass, began, middle);
        goSort(mass, middle + 1, end);
    }

    public static void choice(int[] array, Scanner sc) {
        System.out.println("\nВыберете метод сортировки:\n  1 - Сортировка Шелла\n  " +
                "2 - Пиромидальная сортировка\n  3 - Сортировка Timsort\n  4 - Быстроя" +
                " сортировка");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                shellSort(array);
                break;
            case 2:
                heapSort(array);
                break;
            case 3:
                timSort(array);
                break;
            case 4:
                quickSort(array);
                break;
            default:
                System.out.println("Введён не правильный номер.");
                choice(array, sc);
        }
    }

    public static void outEvenNumbers(int[]ar){
        System.out.println("\nЧётные числа в массиве:");
        int f=0;
        for (int i=0;i<ar.length;i++){
            if(ar[i]%2==0){
                if(f==0){
                    System.out.print("\""+ar[i]+"\"");
                    f=1;
                }else
                    System.out.print(" | \""+ar[i]+"\"");
            }
        }
        if(f==0)
            System.out.print("Чётных чисел не обноруженно.");
    }
}
