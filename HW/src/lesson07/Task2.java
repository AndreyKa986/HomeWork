package lesson07;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader("TXT.txt");
        int i;
        while ((i=reader.read())!=-1){
            System.out.print((char)i);
        }
        reader.close();
        FileWriter writer=new FileWriter("New.txt",false);
        System.out.println("\n\nВведите текст)");
        Scanner scanner=new Scanner(System.in);
        writer.write(scanner.nextLine());
        writer.flush();
        writer.close();
    }
}
