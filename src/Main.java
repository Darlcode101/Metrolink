
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        
        
        //file location to run the program from my mac
        String file = "/Users/alexdarlington/Desktop/summer project/Metrolink_times_linecolour(in).csv";
        Scanner input = new Scanner(System.in);

        String startLocation = CSVreader.InputVerification("enter departure", file, input);
        String endLocation = CSVreader.InputVerification("enter destination", file, input);

        System.out.println("Trip starts at " + startLocation + " and ends at " + endLocation);
    }
}