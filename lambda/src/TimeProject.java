import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

class TimeProject {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your day of birth:");
        int day = sc.nextInt();

        System.out.println("Enter your month of birth:");
        int month = sc.nextInt();

        System.out.println("Enter your year of birth:");
        int year = sc.nextInt();

        //stores the user input in LocalDate
        LocalDate birthday = LocalDate.of(year, month, day);
        System.out.println("Your birthday is " + birthday);

        LocalDate now = LocalDate.now();        // Gets localDate

        Period diff = Period.between(birthday, now);    // Difference between the dates is calculated

        // Displays the current age using getYears,getMonths and getDays
        System.out.print("Your current age is : ");
        System.out.println(diff.getYears() + " years " + diff.getMonths() + " months " + diff.getDays() + " days ");


        // Calculate the next birthday
        LocalDate nextBirthday = birthday.withYear(now.getYear()); //gets the current year
        if ((nextBirthday.compareTo(now) < 0)) {
            nextBirthday = nextBirthday.plusYears(1);
        }

        // Calculates the difference between current date and the next birthday
        long nDays = ChronoUnit.DAYS.between(now, nextBirthday);

        // Displays the days remaining for next birthday
        System.out.print("Days remaining until your next birthday :" + nDays + " days ");

    }

}