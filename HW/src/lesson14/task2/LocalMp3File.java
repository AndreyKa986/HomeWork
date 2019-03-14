package lesson14.task2;

import java.util.Objects;

public class LocalMp3File extends LocalFile implements Comparable {

    private String artist;
    private String album;
    private String title;
    private String time;

    LocalMp3File(String pathname, String checkSum) {
        super(pathname, checkSum);
    }

    LocalMp3File(String pathname, String artist, String album, String title) {
        super(pathname);
        this.artist = artist;
        this.album = album;
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalMp3File that = (LocalMp3File) o;
        return Objects.equals(artist, that.artist) &&
                Objects.equals(album, that.album) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int compareTo(Object o) {
        LocalMp3File that = (LocalMp3File) o;
        if (this.getArtist().compareToIgnoreCase(that.getArtist()) > 0)
            return 1;
        else if (this.getArtist().compareToIgnoreCase(that.getArtist()) < 0)
            return -1;
        else {
            if (this.getAlbum().compareToIgnoreCase(that.getAlbum()) > 0)
                return 1;
            else if (this.getAlbum().compareToIgnoreCase(that.getAlbum()) < 0)
                return -1;
            else return this.getTitle().compareToIgnoreCase(that.getTitle());
        }
    }
}
