package lesson16.task1;

public class StreamLoading implements Runnable {
    private Parse parse;

    StreamLoading(Parse parse) {
        this.parse = parse;
    }

    @Override
    public void run() {
        for (String string:parse.arrayOfLink)
            parse.loading(string);
    }
}
