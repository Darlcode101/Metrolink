import java.io.*;

public class Main {
    public static void main(String[] args){
        
        
        //file location to run the program from my mac
        String file = "/Users/alexdarlington/Desktop/summer project/Metrolink_times_linecolour(in).csv";
        String file1 = "/Users/alexdarlington/Desktop/summer project/walktimes(in).csv";

        BufferedReader reader = null;

        String line = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null) {
                String[] row = line.split(",");

                for (String index : row) {
                    System.out.printf("%-5s", index);
                }
                System.out.println();
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
