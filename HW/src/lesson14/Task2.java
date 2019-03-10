package lesson14;

import com.mpatric.mp3agic.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {
    static ArrayList<MP3Fil> mp3Files = new ArrayList<>();
    static ArrayList<AnyFile> allFiles = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь папки для сканированиая:");
        String pathOfFolder = scanner.nextLine();
        scanner.close();
        fileScanning(new File(pathOfFolder));
        setCheckSum();
        chooseMP3();
        setMP3Tag();
        sortMP3Files();
        printMP3Collection();
        printSameSong();
        printEqualsFile();
    }

    public static void fileScanning(File folder) {
        File[] filesFromFolder = folder.listFiles();
        for (File entry : filesFromFolder) {
            if (entry.isDirectory())
                fileScanning(entry);
            else {
                String pathname = entry.getAbsolutePath();
                allFiles.add(new AnyFile(pathname));
            }
        }
    }

    public static void setCheckSum() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            FileInputStream fileInputStream;
            byte[] bytes = new byte[1024];
            int bytesRead;
            byte[] messageDigestBytes;
            StringBuilder stringBuilder = new StringBuilder();
            for (AnyFile file : allFiles) {
                fileInputStream = new FileInputStream(file.pathname);
                while ((bytesRead = fileInputStream.read(bytes)) > 0)
                    messageDigest.update(bytes, 0, bytesRead);
                messageDigestBytes = messageDigest.digest();
                for (int i = 0; i < messageDigestBytes.length; i++)
                    stringBuilder.append(Integer.toString((messageDigestBytes[i] & 0xff) + 0x100, 16).substring(1));
                file.checkSum = stringBuilder.toString();
                stringBuilder.delete(0, stringBuilder.capacity());
                fileInputStream.close();
            }
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void chooseMP3() {
        Pattern pattern = Pattern.compile("\\.mp3$");
        for (AnyFile file : allFiles) {
            Matcher matcher = pattern.matcher(file.pathname);
            if (matcher.find()) {
                mp3Files.add(new MP3Fil(file.pathname));
            }
        }
    }

    public static void setMP3Tag() {
        for (MP3Fil file : mp3Files) {
            try {
                Mp3File song = new Mp3File(file.pathname);
                if (song.hasId3v2Tag()) {
                    ID3v2 tag = song.getId3v2Tag();
                    if (tag.getArtist() != null)
                        file.artist = tag.getArtist();
                    else file.artist = "Название исполнителя отсутствует";
                    if (tag.getAlbum() != null)
                        file.album = tag.getAlbum();
                    else file.album = "Название альбома отсутсвует";
                    if (tag.getTitle() != null)
                        file.title = tag.getTitle();
                    else file.title = "Название трэка отсутствует";
                    file.time = getStringTime(song.getLengthInSeconds());
                }else {
                    file.artist = "Название исполнителя отсутствует";
                    file.album = "Название альбома отсутсвует";
                    file.title = "Название трэка отсутствует";
                    file.time = "Длительность трэка отсутствует";
                }
            } catch (IOException | UnsupportedTagException | InvalidDataException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getStringTime(long time) {
        long minute = time / 60;
        long second = time % 60;
        return minute + ":" + second;
    }

    public static void sortMP3Files() {
        Collections.sort(mp3Files, new Comparator<MP3Fil>() {
            @Override
            public int compare(MP3Fil o1, MP3Fil o2) {
                if (o1.artist.compareToIgnoreCase(o2.artist) > 0)
                    return 1;
                else if (o1.artist.compareToIgnoreCase(o2.artist) < 0)
                    return -1;
                else {
                    if (o1.album.compareToIgnoreCase(o2.album) > 0)
                        return 1;
                    else if (o1.album.compareToIgnoreCase(o2.album) < 0)
                        return -1;
                    else return o1.title.compareToIgnoreCase(o2.title);
                }
            }
        });
    }

    public static void printMP3Collection() {
        System.out.println(mp3Files.get(0).artist);
        System.out.println("\t" + mp3Files.get(0).album);
        System.out.println("\t" + mp3Files.get(0).title + " " + mp3Files.get(0).time + " (" + mp3Files.get(0).pathname + ")");
        for (int i = 1; i < mp3Files.size(); i++) {
            if (!mp3Files.get(i).artist.equals(mp3Files.get(i - 1).artist))
                System.out.println(mp3Files.get(i).artist);
            if (!mp3Files.get(i).album.equals(mp3Files.get(i - 1).album))
                System.out.println("\t" + mp3Files.get(i).album);
            System.out.println("\t" + mp3Files.get(i).title + " " + mp3Files.get(i).time + " (" + mp3Files.get(i).pathname + ")");
        }
    }

    public static void printSameSong() {
        System.out.println("\n\nСписок одинаковых песен:");
        int flag;
        int sing;
        ArrayList<MP3Fil> listSameSong = new ArrayList<>();
        for (int i = 0; i < mp3Files.size() - 1; i++) {
            flag = 0;
            sing = 0;
            for (int j = 0; j < listSameSong.size(); j++) {
                if (mp3Files.get(i).artist.equals(listSameSong.get(j).artist)
                        && mp3Files.get(i).album.equals(listSameSong.get(j).album)
                        && mp3Files.get(i).title.equals(listSameSong.get(j).title)) {
                    sing = 1;
                    break;
                }
            }
            if (sing == 1)
                continue;
            for (int j = i + 1; j < mp3Files.size(); j++) {
                if (mp3Files.get(i).artist.equals(mp3Files.get(j).artist)
                        && mp3Files.get(i).album.equals(mp3Files.get(j).album)
                        && mp3Files.get(i).title.equals(mp3Files.get(j).title)) {
                    flag++;
                    if (flag == 1) {
                        System.out.println("\n" + mp3Files.get(i).artist + " - " + mp3Files.get(i).album + " - "
                                + mp3Files.get(i).title + " :");
                        System.out.println("\t" + mp3Files.get(i).pathname);
                        System.out.println("\t" + mp3Files.get(j).pathname);
                        listSameSong.add(new MP3Fil(mp3Files.get(i).artist, mp3Files.get(i).album,
                                mp3Files.get(i).title));
                    } else
                        System.out.println("\t" + mp3Files.get(j).pathname);
                }
            }
        }
        if (listSameSong.size() == 0)
            System.out.println("Одинаковые песни отсутсвуют");
    }

    public static void printEqualsFile() {
        System.out.println("\n\nСписок идентичных файлов:");
        int flag;
        int sing;
        int duplicate = 0;
        ArrayList<AnyFile> listSameFiles = new ArrayList<>();
        for (int i = 0; i < allFiles.size() - 1; i++) {
            flag = 0;
            sing = 0;
            for (int j = 0; j < listSameFiles.size(); j++) {
                if (allFiles.get(i).checkSum.equals(listSameFiles.get(j).checkSum)) {
                    sing = 1;
                    break;
                }
            }
            if (sing == 1)
                continue;
            for (int j = i + 1; j < allFiles.size(); j++) {
                if (allFiles.get(i).checkSum.equals(allFiles.get(j).checkSum)) {
                    flag++;
                    if (flag == 1) {
                        System.out.println("\nДубликат - " + ++duplicate + " :");
                        System.out.println("\t" + allFiles.get(i).pathname);
                        System.out.println("\t" + allFiles.get(j).pathname);
                        listSameFiles.add(new AnyFile(allFiles.get(i).pathname, allFiles.get(i).checkSum));
                    } else
                        System.out.println("\t" + allFiles.get(j).pathname);
                }
            }
        }
        if (duplicate == 0)
            System.out.println("Идентичные файлы отсутсвуют");
    }

    public static class MP3Fil {
        public String artist;
        public String album;
        public String title;
        public String time;
        public String pathname;

        public MP3Fil(String pathname) {
            this.pathname = pathname;
        }

        public MP3Fil(String artist, String album, String title) {
            this.artist = artist;
            this.album = album;
            this.title = title;
        }
    }

    public static class AnyFile {
        public String pathname;
        public String checkSum;

        public AnyFile(String pathname) {
            this.pathname = pathname;
        }

        public AnyFile(String pathname, String checkSum) {
            this.pathname = pathname;
            this.checkSum = checkSum;
        }
    }
}
