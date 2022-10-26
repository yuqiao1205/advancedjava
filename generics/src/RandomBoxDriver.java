import java.math.BigDecimal;
import java.util.*;

public class RandomBoxDriver {

    public static void main(String[] args) {

        // Employee drawing
        RandomBox<String> nameDrawing = new RandomBox<>();
        fillNames(nameDrawing);

        System.out.println("Random Name Drawing!");
        System.out.println(nameDrawing);
        nameDrawing.displayEntries();
        System.out.println("Winner: " + nameDrawing.drawWinner());

        // Lottery drawing
        RandomBox<Integer> lottery = new RandomBox<>();
        fillNumbers(lottery, 100);
        System.out.println("\nLottery!");
        lottery.displayEntries();
        System.out.println(lottery);
        System.out.println("Winner: " + lottery.drawWinner());

        // SUGGESTION: CREATE A RANDOMBOX THAT HOLDS SOME OTHER TYPE- ANY CLASS YOU'VE GOT!
        RandomBox<BigDecimal> firstPrizeAmountDrawing = new RandomBox<>();
        fillPrizeAmounts(firstPrizeAmountDrawing);
        System.out.println("\nAmount of first prize today is $" + firstPrizeAmountDrawing.drawWinner() + "!");

        // UNCOMMENT WHEN YOU WRITE YOUR STATIC METHOD
        System.out.println("\nMultiple Winners!\n");
        System.out.println("Random Name Drawing! 5 Unique Winners!");
        List<String> nameWinners = pickMultipleWinners(nameDrawing, 5);
        System.out.println(nameWinners);

        System.out.println("\nLottery! 3 Unique Winners!");
        List<Integer> numberWinners = pickMultipleWinners(lottery, 3);
        System.out.println(numberWinners);

        RandomBox<String> uniqueNameBoxTest = new RandomBox<>();
        uniqueNameBoxTest.addItem("Winner1");
        uniqueNameBoxTest.addItem("Winner2");
        uniqueNameBoxTest.addItem("Winner3");

        List<String> uniqueWinners = pickMultipleWinners(uniqueNameBoxTest, 3);
        System.out.println("List should contain Winner1, Winner2, and Winner3 (in any order)");
        System.out.println(uniqueWinners);

        System.out.println("\nCode should take some logical action but should NOT return a list with duplicate winners or enter an infinite loop.");
        uniqueWinners = pickMultipleWinners(uniqueNameBoxTest, 4);
        System.out.println(uniqueWinners);

    }

    private static void fillPrizeAmounts(RandomBox<BigDecimal> prizeAmountDrawing) {

        prizeAmountDrawing.addItem(new BigDecimal("1"));
        prizeAmountDrawing.addItem(new BigDecimal("10"));
        prizeAmountDrawing.addItem(new BigDecimal("100"));
        prizeAmountDrawing.addItem(new BigDecimal("500"));
        prizeAmountDrawing.addItem(new BigDecimal("1000"));
        prizeAmountDrawing.addItem(new BigDecimal("10000"));
        prizeAmountDrawing.addItem(new BigDecimal("100000"));

    }

    private static <T> List<T> pickMultipleWinners(RandomBox<T> randomBox, int numOfWinners) {

        if (numOfWinners > randomBox.numOfItems()) {
            // More winners than size of the RandomBox, all win!
            return randomBox.getItems();
        }

        Set<T> result = new HashSet<>();

        while (result.size() < numOfWinners) {
            result.add(randomBox.drawWinner());
        }

        return new ArrayList<>(result);

    }

    public static void fillNames(RandomBox<String> wordBox) {
        wordBox.addItem("Adam Zapel");
        wordBox.addItem("Al Dente");
        wordBox.addItem("Al Fresco");
        wordBox.addItem("Al K. Seltzer");
        wordBox.addItem("Alf A. Romeo");
        wordBox.addItem("Amanda Lynn");
        wordBox.addItem("Anita Job");
        wordBox.addItem("Anna Conda");
        wordBox.addItem("Anna Graham");
        wordBox.addItem("Anna Prentice ");
        wordBox.addItem("Anna Sasin");
        wordBox.addItem("Anne Teak");
        wordBox.addItem("B.A. Ware");
        wordBox.addItem("Barb Dwyer");
        wordBox.addItem("Barb E. Dahl");
        wordBox.addItem("Barbara Seville");
        wordBox.addItem("Barry Cade");
        wordBox.addItem("Bea Minor");
        wordBox.addItem("Dee Major");
        wordBox.addItem("Beau Tye");
        wordBox.addItem("Bill Board");
        wordBox.addItem("Cara Van");
        wordBox.addItem("Chris P. Bacon");
        wordBox.addItem("Constance Noring");
        wordBox.addItem("Crystal Ball");
        wordBox.addItem("Crystal Glass");
        wordBox.addItem("Earl Lee Riser");
        wordBox.addItem("Easton West ");
        wordBox.addItem("Ferris Wheeler");
        wordBox.addItem("Flint Sparks");
        wordBox.addItem("Franklin Stein ");
        wordBox.addItem("Gene Poole");
        wordBox.addItem("Heidi Clare");
        wordBox.addItem("Helen Back");
        wordBox.addItem("Helen Wiells");
        wordBox.addItem("Holly McRell");
        wordBox.addItem("Holly Wood");
        wordBox.addItem("Jack Pott");
        wordBox.addItem("Joe Kerr");
        wordBox.addItem("Joy Rider");
        wordBox.addItem("Justin Case");
        wordBox.addItem("Justin Time");
        wordBox.addItem("Kandi Apple");
        wordBox.addItem("Laura Norder");
        wordBox.addItem("Leigh King ");
        wordBox.addItem("Luke Warm");
        wordBox.addItem("Marsha Mellow");
        wordBox.addItem("Marshall Law");
        wordBox.addItem("Marty Graw");
        wordBox.addItem("Olive Branch");
        wordBox.addItem("Paige Turner");
        wordBox.addItem("Pepe Roni");
        wordBox.addItem("Price Wright");
        wordBox.addItem("Rocky Beach");
        wordBox.addItem("Sandy Beach");
        wordBox.addItem("Sal A. Mander");
        wordBox.addItem("Stanley Cupp");
        wordBox.addItem("Tom Morrow");
        wordBox.addItem("Warren Peace");
        wordBox.addItem("Will Power");
        wordBox.addItem("X. Benedict");
    }

    public static void fillNumbers(RandomBox<Integer> numbersBox, int numberOfNumbers) {
        Random generator = new Random();
        int maxNumber = numberOfNumbers * 100;
        for (int i = 0; i < numberOfNumbers; i++) {
            numbersBox.addItem(generator.nextInt(maxNumber));
        }
    }


}
