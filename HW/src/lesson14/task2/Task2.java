package lesson14.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь папки для сканированиая:");
        FileScanner fileScanner = new FileScanner();
        fileScanner.scanDirectory(new File(scanner.nextLine()));
        scanner.close();
        ArrayList<LocalMp3File>mp3Files=fileScanner.getMp3Files();
        if (!fileScanner.getAllFiles().isEmpty()) {
            if (!fileScanner.getMp3Files().isEmpty()) {
                Collections.sort(mp3Files);
                printMP3Collection(mp3Files);
                printSameSong(mp3Files);
            } else {
                System.out.println("Отсутствуют mp3 файлы");
            }
            printEqualsFile(fileScanner.getAllFiles());
        } else
            System.out.println("В директории отсутсвуют файлы");
    }

    private static void printMP3Collection(ArrayList<LocalMp3File> mp3Files) {
        System.out.println(mp3Files.get(0).getArtist());
        System.out.println("\t" + mp3Files.get(0).getAlbum());
        System.out.println("\t\t" + mp3Files.get(0).getTitle() + " " + mp3Files.get(0).getTime() + " (" +
                mp3Files.get(0).getPathname() + ")");
        for (int i = 1; i < mp3Files.size(); i++) {
            if (!mp3Files.get(i).getArtist().equals(mp3Files.get(i - 1).getArtist())) {
                System.out.println(mp3Files.get(i).getArtist());
                System.out.println("\t" + mp3Files.get(i).getAlbum());
            } else {
                if (!mp3Files.get(i).getAlbum().equals(mp3Files.get(i - 1).getAlbum()))
                    System.out.println("\t" + mp3Files.get(i).getAlbum());
            }
            System.out.println("\t\t" + mp3Files.get(i).getTitle() + " " + mp3Files.get(i).getTime() + " (" +
                    mp3Files.get(i).getPathname() + ")");
        }
    }

    private static void printSameSong(ArrayList<LocalMp3File> mp3Files) {
        System.out.println("\n\nСписок одинаковых песен:");
        int flag;
        int sing;
        ArrayList<LocalMp3File> listSameSong = new ArrayList<>();
        for (int i = 0; i < mp3Files.size() - 1; i++) {
            flag = 0;
            sing = 0;
            for (LocalMp3File localMp3File : listSameSong) {
                if (mp3Files.get(i).equals(localMp3File)) {
                    sing = 1;
                    break;
                }
            }
            if (sing == 1)
                continue;
            for (int j = i + 1; j < mp3Files.size(); j++) {
                if (mp3Files.get(i).equals(mp3Files.get(j))) {
                    flag++;
                    if (flag == 1) {
                        System.out.println("\n" + mp3Files.get(i).getArtist() + " - " + mp3Files.get(i).getAlbum() +
                                " - " + mp3Files.get(i).getTitle() + " :");
                        System.out.println("\t" + mp3Files.get(i).getPathname());
                        System.out.println("\t" + mp3Files.get(j).getPathname());
                        listSameSong.add(new LocalMp3File(mp3Files.get(i).getPathname(), mp3Files.get(i).getArtist(),
                                mp3Files.get(i).getAlbum(), mp3Files.get(i).getTitle()));
                    } else
                        System.out.println("\t" + mp3Files.get(j).getPathname());
                }
            }
        }
        if (listSameSong.size() == 0)
            System.out.println("Одинаковые песни отсутсвуют");
    }

    private static void printEqualsFile(ArrayList<LocalFile> allFiles) {
        System.out.println("\n\nСписок идентичных файлов:");
        int flag;
        int sing;
        int duplicate = 0;
        ArrayList<LocalFile> listSameFiles = new ArrayList<>();
        for (int i = 0; i < allFiles.size() - 1; i++) {
            flag = 0;
            sing = 0;
            for (LocalFile listSameFile : listSameFiles) {
                if (allFiles.get(i).getCheckSum().equals(listSameFile.getCheckSum())) {
                    sing = 1;
                    break;
                }
            }
            if (sing == 1)
                continue;
            for (int j = i + 1; j < allFiles.size(); j++) {
                if (allFiles.get(i).getCheckSum().equals(allFiles.get(j).getCheckSum())) {
                    flag++;
                    if (flag == 1) {
                        System.out.println("\nДубликат - " + ++duplicate + " :");
                        System.out.println("\t" + allFiles.get(i).getPathname());
                        System.out.println("\t" + allFiles.get(j).getPathname());
                        listSameFiles.add(new LocalFile(allFiles.get(i).getPathname(), allFiles.get(i).getCheckSum()));
                    } else
                        System.out.println("\t" + allFiles.get(j).getPathname());
                }
            }
        }
        if (duplicate == 0)
            System.out.println("Идентичные файлы отсутсвуют");
    }
}
