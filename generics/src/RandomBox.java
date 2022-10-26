import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBox<T> {

    private List<T> items = new ArrayList<>();

    private Random rnd = new Random();

    public RandomBox() {
    }

    public void addItem(T newItem) {
        items.add(newItem);
    }

    public T drawWinner() {
        return items.get(rnd.nextInt(items.size()));
    }

    public void displayEntries() {
        System.out.print("#entries=" + items.size() + ", entries=" + items);
    }

    public int numOfItems() {
        return items.size();
    }

    public List<T> getItems() {
        return items;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RandomBox{");
        sb.append("items=").append(items);
        sb.append('}');
        return sb.toString();
    }
}
