package lesson16;

public class Task2 {

    private static boolean btExit = true;

    public static void main(String[] args) {
        new Thread(threadOne).start();
        System.out.println("thread One started");
        new Thread(threadTwo).start();
        System.out.println("thread Two started");
    }

    private static Runnable threadOne = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            }
            btExit = false;
            System.out.println("thread One finished");
        }
    };

    private static Runnable threadTwo = new Runnable() {
        @Override
        public void run() {
            while (btExit) {
                // без volatile этот цикл крутится бесконечно
            }
            System.out.println("thread Two finished");
        }
    };
}