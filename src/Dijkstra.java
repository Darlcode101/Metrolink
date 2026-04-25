import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra {

    private double totTime;

        public List<String> findRoute(Hashmap hashmap, String start, String end){
            
        
            Map<String, Double> distances = new HashMap<>();
            Map<String, String> previous = new HashMap<>();

            Set<String> details = hashmap.getAllStations();
                for (String station : details) {
                    distances.put(station, Double.MAX_VALUE);
                    if(station.equals(start)){
                        distances.put(station, 0.0);
                }
            }

            PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> Double.compare(distances.get(a), distances.get(b)));

            pq.add(start);

            while (!pq.isEmpty()){
                String current = pq.poll();

                if (current.equals(end)){
                    break;
                }

                for (Edge edge : hashmap.getNeighbours(current)){
                    String neighbour = edge.neighbour;
                    double travelTime = edge.time;

                    double totTime = distances.get(current) + travelTime ; 

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
            route.add(0, current);
            current = previous.get(current);
        }   
        totTime = distances.get(end);
        return route;
    }
    public double getTotTime() {
    return totTime;
}

}




class Edge {
    String neighbour ;
    double time;
    
    public Edge(String neighbour, double time) {
        this.neighbour = neighbour;
        this.time = time;
    }

}
