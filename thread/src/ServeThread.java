
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ServeThread implements Runnable {

    private final BlockingQueue<Food> queue;
    private final int foodToServe;

    public ServeThread(BlockingQueue<Food> queue, int foodToServe) {
        this.queue = queue;
        this.foodToServe = foodToServe;
    }

    public void serve(Food food) throws InterruptedException {
        System.out.println("SERVER STARTING: " + food);
        Thread.sleep(TimeUnit.SECONDS.toMillis(food.getServeTime()));
        System.out.println("SERVER ENDING: " + food);
    }

    public void run() {

        try {
            for (int i = 0; i < foodToServe; i++) {
                System.out.println("SERVER READY");
                serve(queue.take());
            }
        } catch (InterruptedException e) {
            System.out.println("Serve interrupted!");
        }
    }
}
