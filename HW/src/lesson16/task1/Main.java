package lesson16.task1;

public class Main {
    public static void main(String[] args) {
        String[] arrayOfLink = {"https://goo.gl/tFpBDV", "https://goo.gl/AZnd2V", "https://goo.gl/Hc8J4n"};
        Parse parse = new Parse(arrayOfLink);
        StreamLoading loading = new StreamLoading(parse);
        StreamProcessing processing = new StreamProcessing(parse);
        new Thread(loading).start();
        new Thread(processing).start();
    }
}
