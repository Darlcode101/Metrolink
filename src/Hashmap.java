import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

//adjacency matrix from CSV, havent implemented colours yet or handeled error case from first row being from to and time

public class Hashmap{

    private Map<String, List <Edge>> alist = new HashMap<>();

    public void MapLoader(String file){
    BufferedReader reader = null;
    String line ;
    String colour = "";
    
        try {   
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                
                if (row.length == 1){
                            colour = row[0].trim();
                            System.out.println(colour);
                    }
                    
                
                else if (!row[2].equals("Time (mins)")){
                    
                    
                   
                    String stationA = row[0].trim();
                    String stationB = row[1].trim();
                    double travelTime = Double.parseDouble(row[2].trim());
                    
                        
                    //connection between stations
                    alist.putIfAbsent(stationA, new ArrayList<>());
                    alist.get(stationA).add(new Edge(stationB, travelTime, colour));
                    
                    //connection between stations the other way around
                    alist.putIfAbsent(stationB, new ArrayList<>());
                    alist.get(stationB).add(new Edge(stationA, travelTime, colour));
                    

                }
                
            }
        }
        catch (Exception e) {
            e.printStackTrace();

        }

        }
        public List<Edge> getNeighbours(String stationName) {
        return alist.getOrDefault(stationName, new ArrayList<>());
    }
        public Set<String> getAllStations() {
        System.out.println(alist.keySet());
        return alist.keySet(); 
       
    }
        
}
