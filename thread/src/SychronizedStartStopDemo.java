import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SychronizedStartStopDemo {

    public static DateTimeFormatter DATE_TIME_FORMATTER;

    static {
        DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    }

    public static class WaitingWorker implements Runnable {

        private List<String> outputScraper;

        private CountDownLatch readyThreadCounter;

        private CountDownLatch callingThreadBlocker;

        private CountDownLatch completedThreadCounter;


        public WaitingWorker(
                List<String> outputScraper,
                CountDownLatch readyThreadCounter,
                CountDownLatch callingThreadBlocker,
                CountDownLatch completedThreadCounter) {

            this.outputScraper = outputScraper;
            this.readyThreadCounter = readyThreadCounter;
            this.callingThreadBlocker = callingThreadBlocker;
            this.completedThreadCounter = completedThreadCounter;
        }

        private void doSomeWork() {
            mark(String.format("Lucky #%d", new Random().nextInt(100)));
        }

        @Override
        public void run() {
            // Mark the initialization finished and wait to be scheduled
            readyThreadCounter.countDown();
            try {
                // Wait to be started
                callingThreadBlocker.await();
                doSomeWork();
                outputScraper.add("Counted down");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // Mark the completion
                completedThreadCounter.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        mark("Main thread starts!");

        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch readyThreadCounter = new CountDownLatch(5);
        CountDownLatch callingThreadBlocker = new CountDownLatch(1);
        CountDownLatch completedThreadCounter = new CountDownLatch(5);
        List<Thread> workers = Stream.generate(() -> new Thread(new WaitingWorker(
                outputScraper, readyThreadCounter, callingThreadBlocker, completedThreadCounter)))
                .limit(5)
                .collect(Collectors.toList());

        workers.forEach(Thread::start);
        readyThreadCounter.await();

        outputScraper.add("Workers ready");
        callingThreadBlocker.countDown();
        completedThreadCounter.await();
        outputScraper.add("Workers complete");

        outputScraper.forEach(System.out::println);

        mark("Main thread exits!");
    }

    public static void mark(String msg) {
        System.out.format("%s(%s): %s%n", DATE_TIME_FORMATTER.format(LocalDateTime.now()), Thread.currentThread().getName(), msg);
    }

}
