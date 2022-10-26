import java.util.Random;

public class HiLowGame {

    private final int min;

    private final int max;

    private int correctNumber;

    private GuessStatus status = GuessStatus.NOT_STARTED;

    public enum GuessStatus {
        NOT_STARTED, TOO_HIGH, TOO_LOW, CORRECT, OUT_OF_RANGE
    }

    public HiLowGame(int min, int max) {
        this.min = min;
        this.max = max;
        restart();
    }

    public void restart() {
        //correctNumber = new Random().nextInt(max - min + 1) + min;
        correctNumber = (int) Math.round(new Random().nextDouble() * 100 + 1);
        status = GuessStatus.NOT_STARTED;
    }

    public int getCorrectNumber() {
        return correctNumber;
    }

    public GuessStatus guess(int inputNumber) {

        if (correctNumber == inputNumber) {
            status = GuessStatus.CORRECT;
        } else if (inputNumber > max || inputNumber < min) {
            status = GuessStatus.OUT_OF_RANGE;
        } else if (correctNumber < inputNumber) {
            status = GuessStatus.TOO_HIGH;
        } else {
            status = GuessStatus.TOO_LOW;
        }

        return status;

    }

    public GuessStatus getStatus() {
        return status;
    }


}
