import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

//adjacency matrix from CSV, havent implemented colours yet or handeled error case from first row being from to and time

public class Hashmap{

    private Map<String, List <Edge>> alist = new HashMap<>();

    public void MapLoader(String file){
    BufferedReader reader = null;
    String line ;
        try {   
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null) {
                String[] row = line.split(",");

                
                if (row.length > 1 && !row[0].equals("From")){
                    String stationA = row[0];
                    String stationB = row[1];
                    int travelTime = Integer.parseInt(row[2]);

                    //connection between stations
                    alist.putIfAbsent(stationA, new ArrayList<>());
                    alist.get(stationA).add(new Edge(stationB, travelTime));
                    
                    //connection between stations the other way around
                    alist.putIfAbsent(stationB, new ArrayList<>());
                    alist.get(stationB).add(new Edge(stationA, travelTime));
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
        return alist.keySet(); 
}
}
