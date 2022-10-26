import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveNumToFile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number or Q to quit");
        String line;
        int number;
        ArrayList<Integer> numbers = new ArrayList<>();
        while(true){
            try{
                line = sc.nextLine();
                if(line.equalsIgnoreCase("q")){
                    break;
                }
                number = Integer.parseInt(line);
                numbers.add(number);
            }catch(Exception e){
                System.out.println("Invalid number entered!!!");
            }
        }

        System.out.print("Enter file name : ");
        String filename = sc.nextLine();
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(filename));
            for(Integer i : numbers){
                output.write(i+"\n");
            }
            System.out.println("Data exported to file "+filename+" successfully");
            output.close();
        } catch (Exception e) {
            System.out.println("file not found");
        }
        sc.close();
    }
}