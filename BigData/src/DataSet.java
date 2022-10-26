import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.*;

// Question 1: How much are the total awards for each state?
// Question 2: What are the total number of recipients for each state?
// Quesiong 3: How much are the average awards for each state?
// Question 4: What is the top 5 schools with most awards amount?
// Question 5: What is the top 10 schools with average of awards?
public class DataSet {

    private static final String CSV_LOCATION = "data.csv";

    private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance(Locale.US);

    private static final NumberFormat NUMBER_FORMATTER = NumberFormat.getNumberInstance(Locale.US);

    static {
        CURRENCY_FORMATTER.setMaximumFractionDigits(0);
        NUMBER_FORMATTER.setMaximumFractionDigits(0);
    }

    public static void main(String[] args) {

        // School list.
        List<School> schoolList = new ArrayList<>();

        // Open and parse CSV
        try (Scanner scanner = new Scanner(new File(CSV_LOCATION), Charset.defaultCharset().name())) {

            // Parse the content of CSV
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                // Split the line by comma
                String[] fields = splitCSVLine(line);

                // Purposely omitting school city and institution type becuase the purpose only to count awards and recipients by state.
                final long opeID = Long.parseLong(fields[0]);
                final String schoolName = fields[1].trim();
                final String state = fields[3].trim();
                final long awards = Long.parseLong(fields[5].trim().replaceAll("\\$", "").replaceAll(",", ""));
                final int recipients = Integer.parseInt(fields[6].trim().replaceAll("\\$", "").replaceAll(",", ""));

                schoolList.add(new School(opeID, schoolName, state, awards, recipients));
            }

            // Award and recipient HashMaps.
            Map<String, Long> awardsMap = new HashMap<>();
            Map<String, Integer> recipientsMap = new HashMap<>();


            // Tally up the number of awards and recipients per state by adding them to a HashMap.
            for (School school : schoolList) {

                String state = school.getState();

                if (awardsMap.containsKey(state)) {
                    awardsMap.put(state, awardsMap.get(state) + school.getAwards());
                } else {
                    awardsMap.put(state, school.getAwards());
                }

                if (recipientsMap.containsKey(state)) {
                    recipientsMap.put(state, recipientsMap.get(state) + school.getRecipients());
                } else {
                    recipientsMap.put(state, school.getRecipients());
                }

                // Test the implementation of the toString method.
                // System.out.println(school.toString());

            }

            // Sort the maps by state name and print the results.
            Set<String> states = new TreeSet<>(awardsMap.keySet());

            System.out.println("The total awards, the total number of recipients and the average awards for each state:");
            for (String state : states) {
                long awards = awardsMap.get(state);
                int recipients = recipientsMap.get(state);
                double avg = awards / recipients;

                System.out.println(state + ": Awards=" + CURRENCY_FORMATTER.format(awards) + ", Recipients=" + NUMBER_FORMATTER.format(recipients) + ", Average=" + CURRENCY_FORMATTER.format(avg));
            }


            // Show the top 5 schools with the most awards amount
            Collections.sort(schoolList, new School.AwardsComparator());
            System.out.println();
            System.out.println("The top 5 schools with most awards amount:");

            for (int i = 0; i < 5; i++) {
                System.out.println(schoolList.get(i));
            }

            // Show the top 10 schools with average
            System.out.println();
            System.out.println("The top 10 schools with average of awards:");
            Collections.sort(schoolList, new School.AwardsPerRecipientComparator());
            for (int i = 0; i < 10; i++) {
                School school = schoolList.get(i);

                System.out.println(school.getName() + " " + "$" + school.getAwards() / school.getRecipients());
            }


        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String[] splitCSVLine(String line) {
        List<String> result = new ArrayList<>();

        boolean newStart = true;
        boolean inQuote = false;

        StringBuilder token = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (newStart) {
                // Reuse token
                token.setLength(0);

                if (c == '\"') {
                    inQuote = true;
                } else {
                    token.append(c);
                }
                newStart = false;
            } else if (inQuote) {
                if (c == '\"') {
                    inQuote = false;
                } else {
                    token.append(c);
                }
            } else if (c == ',') {
                result.add(token.toString());
                newStart = true;
                inQuote = false;
            } else {
                token.append(c);
            }
        }

        if (token.length() > 0) {
            result.add(token.toString());
        }

        return result.toArray(new String[0]);
    }

}
