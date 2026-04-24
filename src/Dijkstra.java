import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra {
   

        public List<String> findRoute(Hashmap hashmap, String start, String end){
        
            Map<String, Integer> distances = new HashMap<>();
            Map<String, String> previous = new HashMap<>();

            Set<String> details = hashmap.getAllStations();
                for (String station : details) {
                    distances.put(station, Integer.MAX_VALUE);
                    if(station.equals(start)){
                        distances.put(station, 0);
                }
            }

            PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> distances.get(a) - distances.get(b));

            pq.add(start);

            while (!pq.isEmpty()){
                String current = pq.poll();

                if (current.equals(end)){
                    break;
                }

                for (Edge edge : hashmap.getNeighbours(current)){
                    String neighbour = edge.neighbour;
                    int travelTime = edge.time;

                    int totTime = distances.get(current) + travelTime ; 

                    if (totTime < distances.get(edge.neighbour)){
                        distances.put(edge.neighbour, totTime);
                        previous.put(neighbour, current);
                        pq.add(neighbour);
                    }
            }
        }
        List<String> route = new ArrayList<>();

        String current = end;

        while (current != null) {
            route.add(current);
            current = previous.get(current);
        }
        return route;
    }
}




class Edge {
    String neighbour ;
    int time;
    
    public Edge(String neighbour, int time) {
        this.neighbour = neighbour;
        this.time = time;
    }

}
