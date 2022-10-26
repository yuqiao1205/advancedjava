import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * References:
 * 1. https://www.baeldung.com/java-inifinite-streams
 * 2. https://vijay-vk.github.io/java-concurrency/java-cache-miss.html
 */
public class CacheMiss {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        int count = 10;

        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            intList.add(i);
        }

        System.out.println("Main Thread : " + Thread.currentThread().getName());

        Stream.iterate(0, i -> i + 1).limit(count).forEach(i -> doSomeRandomOperationInList(intList));

        CountDownLatch latch = new CountDownLatch(count);
        Stream.iterate(0, i -> i + 1).limit(count).forEach(i -> runAsync(intList, i, latch));

        latch.await();
    }

    private static void runAsync(List<Integer> intList, int t, CountDownLatch latch) {
        CompletableFuture.runAsync(() -> {
            System.out.println("new thread - " + t + " : " + Thread.currentThread().getName());
            long s = System.currentTimeMillis();
            List<Integer> l = intList.stream().map(i -> i * 2).collect(Collectors.toList());
            long e = System.currentTimeMillis();
            System.out.println("Thread : " + t + " : " + (e - s));

            latch.countDown();
        });
    }

    private static void doSomeRandomOperationInList(List<Integer> intList) {
        long startTime = System.currentTimeMillis();
        intList.stream().map(i -> i * 2).collect(Collectors.toList());
        long endTime = System.currentTimeMillis();
        System.out.println(
                "Thread : " + Thread.currentThread().getName() + " : Time Taken in (ms) : " + (endTime - startTime));
    }

}