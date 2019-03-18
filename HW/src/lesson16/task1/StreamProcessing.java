package lesson16.task1;

public class StreamProcessing implements Runnable {
    private Parse parse;
    private int lengthOfArray;

    StreamProcessing(Parse parse) {
        this.parse = parse;
        lengthOfArray = parse.arrayOfLink.length;
    }

    @Override
    public void run() {
        for (int i = 0; i < lengthOfArray; i++)
            parse.print();
    }
}

