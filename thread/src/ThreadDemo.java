import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ThreadDemo {

    public static DateTimeFormatter DATE_TIME_FORMATTER;

    static {
        DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    }

    public static class MaxCounterThread implements Runnable {

        public static final long LOOP_COUNTER = 10;

        public static final long NUM_COUNTER = Integer.MAX_VALUE;

        @Override
        public void run() {
            mark("Counter thread starts!");

            for (int loop = 1; loop <= LOOP_COUNTER; loop++) {
                if (Thread.currentThread().isInterrupted()) {
                    mark("Interrupted, exit the thread at start of the loop #" + loop + "!");
                    return;
                }

                mark("CounterThread: >>> Loop #" + loop);
                for (int num = 0; num < NUM_COUNTER; num++) {

                }
                mark("CounterThread: <<< Loop #" + loop);

            }


            mark("Counter thread exits!");
        }
    }

    public static void main(String[] args) {

        mark("Main thread starts!");

        Thread counterThread = new Thread(new MaxCounterThread());

        counterThread.start();

//        counterThread.interrupt();

        try {
            counterThread.join();
        } catch (InterruptedException e) {
            mark("Main thread exits!");
        }

        mark("Main thread exits!");

    }

    public static void mark(String msg) {
        System.out.format("%s: %s%n", DATE_TIME_FORMATTER.format(LocalDateTime.now()), msg);
//        System.out.flush();
    }

}
