import java.util.Random;

public class DiceRollers extends Thread {

    private int dices;
    private int rolls;
    private int nAllMatches;
    private Random rnd;

    public DiceRollers(int dices, int rolls, String name) {
        this.dices = dices;
        this.rolls = rolls;
        this.setName(name);

        this.rnd = new Random();
    }

    @Override
    public void run() {

        // Roll the n times
        for (int i = 0; i < rolls; i++) {

            boolean allMatched = true;
            int result = 0;
            int initialResult = 0;
            for (int j = 0; j < dices; j++) {
                result = rnd.nextInt(6);

                if (j > 0 && result != initialResult) {
                    allMatched = false;
                    break;
                } else if (j == 0) {
                    initialResult = result;
                }
            }

            if (allMatched) {
                nAllMatches++;
            }
        }

        System.out.format("Thread %s has time(s) got %d times of all matches!%n", this.getName(),  nAllMatches);

    }


    public static void main(String args[]) {

        int nDices = 6; // max no. of dices
        int nRolls = 8; // max no. of rolls

        for (int dice = 2; dice <= nDices; dice++) {
            for (int roll = 2; roll <= nRolls; roll++)
                new DiceRollers(dice, roll, String.format("Roller(%d dices %d times)", dice, roll)).start();
        }

    }
}