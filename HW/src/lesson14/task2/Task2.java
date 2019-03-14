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
        ArrayList<LocalMp3File> mp3Files = fileScanner.getMp3Files();
        if (!fileScanner.getAllFiles().isEmpty()) {
            if (!fileScanner.getMp3Files().isEmpty()) {
                Collections.sort(mp3Files);
                printMP3Collection(mp3Files);
                printSameSong(mp3Files);
            } else {
                System.out.println("Отсутствуют mp3 файлы");
            }
            printEqualsFile(fileScanner.getAllFiles());
        } else {
            System.out.println("В директории отсутсвуют файлы");
        }
    }

    private static void printMP3Collection(ArrayList<LocalMp3File> mp3Files) {
        LocalMp3File mp3File = mp3Files.get(0);
        System.out.println(mp3File.getArtist());
        System.out.println("\t" + mp3File.getAlbum());
        System.out.println("\t\t" + mp3File.getTitle() + " " + mp3File.getTime() + " (" + mp3File.getPathname() + ")");
        for (int i = 1; i < mp3Files.size(); i++) {
            mp3File = mp3Files.get(i);
            if (!mp3File.getArtist().equals(mp3Files.get(i - 1).getArtist())) {
                System.out.println(mp3File.getArtist());
                System.out.println("\t" + mp3File.getAlbum());
            } else {
                if (!mp3File.getAlbum().equals(mp3Files.get(i - 1).getAlbum()))
                    System.out.println("\t" + mp3File.getAlbum());
            }
            System.out.println("\t\t" + mp3File.getTitle() + " " + mp3File.getTime() + " (" + mp3File.getPathname() + ")");
        }
    }

    private static void printSameSong(ArrayList<LocalMp3File> mp3Files) {
        System.out.println("\n\nСписок одинаковых песен:");
        int flag;
        int sing;
        ArrayList<LocalMp3File> listSameSong = new ArrayList<>();
        LocalMp3File mp3File;
        LocalMp3File secondMp3File;
        for (int i = 0; i < mp3Files.size() - 1; i++) {
            flag = 0;
            sing = 0;
            mp3File = mp3Files.get(i);
            for (LocalMp3File localMp3File : listSameSong) {
                if (mp3File.equals(localMp3File)) {
                    sing = 1;
                    break;
                }
            }
            if (sing == 1)
                continue;
            for (int j = i + 1; j < mp3Files.size(); j++) {
                secondMp3File = mp3Files.get(j);
                if (mp3File.equals(secondMp3File)) {
                    flag++;
                    if (flag == 1) {
                        System.out.println("\n" + mp3File.getArtist() + " - " + mp3File.getAlbum() +
                                " - " + mp3File.getTitle() + " :");
                        System.out.println("\t" + mp3File.getPathname());
                        System.out.println("\t" + secondMp3File.getPathname());
                        listSameSong.add(new LocalMp3File(mp3File.getPathname(), mp3File.getArtist(),
                                mp3File.getAlbum(), mp3File.getTitle()));
                    } else {
                        System.out.println("\t" + secondMp3File.getPathname());
                    }
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
        LocalFile file;
        LocalFile secondFile;
        for (int i = 0; i < allFiles.size() - 1; i++) {
            flag = 0;
            sing = 0;
            file = allFiles.get(i);
            for (LocalFile listSameFile : listSameFiles) {
                if (file.getCheckSum().equals(listSameFile.getCheckSum())) {
                    sing = 1;
                    break;
                }
            }
            if (sing == 1)
                continue;
            for (int j = i + 1; j < allFiles.size(); j++) {
                secondFile = allFiles.get(j);
                if (file.getCheckSum().equals(secondFile.getCheckSum())) {
                    flag++;
                    if (flag == 1) {
                        System.out.println("\nДубликат - " + ++duplicate + " :");
                        System.out.println("\t" + file.getPathname());
                        System.out.println("\t" + secondFile.getPathname());
                        listSameFiles.add(new LocalFile(file.getPathname(), file.getCheckSum()));
                    } else {
                        System.out.println("\t" + secondFile.getPathname());
                    }
                }
            }
        }
        if (duplicate == 0)
            System.out.println("Идентичные файлы отсутсвуют");
    }
}
