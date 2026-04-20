import java.io.*;
import java.util.*;


public class CSVreader {
    
    public static boolean valueChecker(String file, String location){
        BufferedReader reader = null;
        String line ;
        
        //Buffered reader to check if the value inputed by the user is in the csv file

        try {   
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                if (row.length > 1 && row[1].equals(location)){
                    return true;
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
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String InputVerification (String text, String file, Scanner input){
        while (true) { 
            System.out.println(text);
            String location = input.nextLine();

            if (valueChecker(file, location) == true){
                return location;
            }
            else {
                System.out.println("Try again, location not found.");
            }
        }
    }
    
}