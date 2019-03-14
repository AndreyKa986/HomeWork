package lesson14.task2;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

class FileScanner {

    private ArrayList<LocalMp3File> mp3Files = new ArrayList<>();
    private ArrayList<LocalFile> allFiles = new ArrayList<>();

    public ArrayList<LocalMp3File> getMp3Files() {
        return mp3Files;
    }

    public ArrayList<LocalFile> getAllFiles() {
        return allFiles;
    }

    void scanDirectory(File folder) {
        File[] filesFromFolder = folder.listFiles();
        if (filesFromFolder != null) {
            for (File entry : filesFromFolder) {
                if (entry.isDirectory())
                    scanDirectory(entry);
                else {
                    createFile(entry.getAbsolutePath());
                }
            }
        }
    }

    private void createFile(String pathname) {
        String checkSum = getCheckSum(pathname);
        if (pathname.endsWith(".mp3")) {
            LocalMp3File localMp3File = createMp3File(pathname, checkSum);
            mp3Files.add(localMp3File);
        }
        LocalFile localFile = new LocalFile(pathname, checkSum);
        allFiles.add(localFile);
    }

    private LocalMp3File createMp3File(String pathname, String checkSum) {
        try {
            LocalMp3File localMp3File = new LocalMp3File(pathname, checkSum);
            Mp3File song = new Mp3File(pathname);
            if (song.hasId3v2Tag()) {
                ID3v2 tag = song.getId3v2Tag();
                if (tag.getArtist() != null)
                    localMp3File.setArtist(tag.getArtist());
                else localMp3File.setArtist("Название исполнителя отсутствует");
                if (tag.getAlbum() != null)
                    localMp3File.setAlbum(tag.getAlbum());
                else localMp3File.setAlbum("Название альбома отсутсвует");
                if (tag.getTitle() != null)
                    localMp3File.setTitle(tag.getTitle());
                else localMp3File.setTitle("Название трэка отсутствует");
                localMp3File.setTime(getStringTime(song.getLengthInSeconds()));
            } else {
                localMp3File.setArtist("Название исполнителя отсутствует");
                localMp3File.setAlbum("Название альбома отсутсвует");
                localMp3File.setTitle("Название трэка отсутствует");
                localMp3File.setTime("Длительность трэка отсутствует");
            }
            return localMp3File;
        } catch (IOException | UnsupportedTagException | InvalidDataException | IllegalArgumentException e) {
            System.out.println("\nВнимание!!!");
            System.out.println("Обноружен файл который не является mp3 файлом :");
            System.out.println(pathname+"\n\n");
        }
        return null;
    }

    private String getStringTime(long time) {
        long minute = time / 60;
        long second = time % 60;
        return minute + ":" + second;
    }

    private String getCheckSum(String path) {
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] bytes = new byte[1024];
            int bytesRead;
            byte[] messageDigestBytes;
            StringBuilder stringBuilder = new StringBuilder();
            while ((bytesRead = fileInputStream.read(bytes)) > 0) {
                messageDigest.update(bytes, 0, bytesRead);
            }
            messageDigestBytes = messageDigest.digest();
            for (byte messageDigestByte : messageDigestBytes) {
                stringBuilder.append(Integer.toString((messageDigestByte & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
