import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PositiveNumbers {
    public static void main(String[] args) {

        String fileName = null;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter file name: ");
            fileName = scanner.nextLine();
        }

        // Read file
        try (Scanner scanner = new Scanner(new File(fileName))) {

            // Initialize sum to 0
            int sum = 0;

            while (scanner.hasNextInt()) {

                // Reading integer
                int numbers = scanner.nextInt();

                //checking if numbers is negative
                if (numbers < 0) {
                    // Throwing negative number exception
                    throw new NegativeNumberException("Negative number found: " + numbers);
                }

                // Add number to sum
                sum += numbers;
            }

            // Dump the sum for all the numbers
            System.out.println("Sum of all numbers is " + sum);

        } catch (FileNotFoundException e) {
            // File not found
            System.err.println(e.getMessage());
        } catch (NegativeNumberException e) {
            // Negative number found in the file
            System.err.println(e.getMessage());
        }

    }
}

/**
 * class to represent an Exception when a negative value is found
 */

class NegativeNumberException extends Exception {
    public NegativeNumberException(String str) {
        //passing error message to super class constructor
        super(str);
    }
}

