import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Streams demo.
 */
public class StreamsDemo {

    private static final Pattern WORD_SPLITER_PATTERN = Pattern.compile("[\\P{L}]+");

    public static void main(String[] args) {

        Stream<String> threadLabels = Stream.of("B1", "B2", "B3");

        // 1. Example of using method reference
        //    Stream<Thread> threadStream = threadLabels.map(Thread::new);

        // 2. Example of using lambda, equivalent to method reference
        Stream<Thread> threadStream = threadLabels.map(threadLabel -> {
            return new Thread(threadLabel);
        });

        threadStream.forEach(t -> {
            System.out.println("Start thread " + t.getName());
            t.start();
        });

        // 3. Create an infinite stream of random integers
        Random rnd = new Random();
        Stream.generate(rnd::nextInt).limit(5).forEach(System.out::println);

        // 4. Create an infinite stream of the odd, positive numbers.
        Stream.iterate(1, n -> n + 2).limit(5).forEach(System.out::println);

        // Create a stream of dictionary words from a file

        Path path = Paths.get("words.txt");

        //The stream hence file will also be closed here
        try (Stream<String> lines = Files.lines(path).onClose(() -> System.out.println("File closed"))) {
            // Use flatMap to map one entry to multiple entries
            lines.flatMap(WORD_SPLITER_PATTERN::splitAsStream).collect(Collectors.toList()).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a stream of eviction objects.
        // Create a stream of job objects.
    }
}
