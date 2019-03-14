package lesson14.task2;

class LocalFile {
    private String pathname;
    private String checkSum;

    LocalFile(String pathname) {
        this(pathname, null);
    }

    LocalFile(String pathname, String checkSum) {
        this.pathname = pathname;
        this.checkSum = checkSum;
    }

    public String getPathname() {
        return pathname;
    }

    public String getCheckSum() {
        return checkSum;
    }
}
