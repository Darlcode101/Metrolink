import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        
        
        //file location to run the program from my mac
        String file = "/Users/alexdarlington/Desktop/summer project/Metrolink_times_linecolour(in).csv";
        BufferedReader reader = null;
        String line = "";

        Scanner start = new Scanner(System.in);
        System.out.println("enter start location ");
        String startLocation = start.nextLine();
        System.out.println("start location is" + startLocation);

        Scanner end = new Scanner(System.in);
        System.out.println("enter end location ");
        String endLocation = end.nextLine();
                try {   
                reader = new BufferedReader(new FileReader(file));
                while((line = reader.readLine()) != null) {
                    String[] row = line.split(",");
                    if (row.length > 1 && row[1].equals(endLocation)){
                        System.out.println("end location is " + endLocation);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();

            }

            finally{
                try {
                    reader.close(); 
                } catch (Exception e) {
                }

            }

    
    }
}
