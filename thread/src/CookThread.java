
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class CookThread implements Runnable {

    private BlockingQueue<Food> queue;
    private List<Food> foods;

    public CookThread(BlockingQueue<Food> queue, List<Food> foods) {
        this.queue = queue;
        this.foods = foods;
    }

    public void cook(Food food) {
        try {

            System.out.println("COOK READY");

            System.out.println("COOK STARTING: " + food);
            Thread.sleep(TimeUnit.SECONDS.toMillis(food.getCookTime()));
            System.out.println("COOK ENDING: " + food);

            queue.put(food);

        } catch (InterruptedException e) {
            Thread.interrupted();
        }
    }

    public void run() {
        foods.forEach(this::cook);
    }

}
