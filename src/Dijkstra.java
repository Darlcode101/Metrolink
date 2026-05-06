    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.PriorityQueue;
    import java.util.Set;

    public class Dijkstra {

        private double totTime;
        private int changeCount = 0;

        public double getTotTime() {
        return totTime;
    }

    public int getChangeCount() {
        return changeCount;
    }    
        

        public List<String> findRoute(Hashmap hashmap, String start, String end){
                
                this.changeCount = 0;
                this.totTime = 0;

                Map<String, Double> distances = new HashMap<>();
                Map<String, String> previous = new HashMap<>();
                Map<String, String> previousColour = new HashMap<>();
               
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
                        String compareColour = edge.colour;
            
                        if(previousColour.get(current)!= null && !previousColour.get(current).equalsIgnoreCase(compareColour)){
                                travelTime += 2;
                        }
                        
                        totTime = distances.get(current) + travelTime ; 

                        if (totTime < distances.get(edge.neighbour)){
                            
                            distances.put(edge.neighbour, totTime);
                            previous.put(neighbour, current);
                            previousColour.put(neighbour, edge.colour);

                            pq.add(neighbour);


                        }
                        
                        

                }
            }
            List<String> route = new ArrayList<>();

            String current = end;
            String sourcecolour = "";
            

            while (current != null) {
                String prevStation = previous.get(current);
                String colour = previousColour.get(current);

                if (colour != null) {
                sourcecolour = colour;
                }
                
                    route.add(0, current + " on " + sourcecolour + " line");
                
                if (previousColour.get(prevStation)!= null&&(!previousColour.get(current).equalsIgnoreCase(previousColour.get(prevStation)))){
                    route.add(0,"*** Change to the " +previousColour.get(current)+" line ***");
                    changeCount ++;
                }
                current = previous.get(current);
            }   
            totTime = distances.get(end);
            
            return route;
        }
   
        public List<String> findFewestRoute(Hashmap hashmap, String start, String end){
            this.changeCount = 0;
            this.totTime = 0;

            Map<lineInfo, Double> distances = new HashMap<>();
            Map<lineInfo, Double> time = new HashMap<>();
            Map<lineInfo, lineInfo> previous = new HashMap<>();
            
            

            PriorityQueue<lineInfo> pq = new PriorityQueue<>((a, b) -> Double.compare(distances.get(a), distances.get(b)));

            for (Edge edge : hashmap.getNeighbours(start)){
                lineInfo startState = new lineInfo(start, edge.colour);
                distances.put(startState, 0.0);
                time.put(startState, edge.time);
                pq.add(startState);
                }
            
            lineInfo endState = null;

            while (!pq.isEmpty()){
                lineInfo current = pq.poll();
                
                if (current.station().equals(end)) {
                endState = current;
                break;
                }
                for (Edge edge : hashmap.getNeighbours(current.station())) {
                    double weight;
                    if( edge.colour.equalsIgnoreCase(current.colour())){
                            weight = 1.0;
                        } else {
                            weight = 1000.0;
                        }
                    
                    double newDist = distances.get(current) + weight;
                    double newTime = time.get(current) + edge.time;
                    lineInfo nextState = new lineInfo(edge.neighbour, edge.colour);
                    
                    
                    if (newDist < distances.getOrDefault(nextState, Double.MAX_VALUE)) {
                        distances.put(nextState, newDist);
                        time.put(nextState, newTime);
                        previous.put(nextState, current);
                        pq.add(nextState);
                    }

                }
                }
            List<String> route = new ArrayList<>();
            
            lineInfo current = endState;

            while (current != null) {
                
                lineInfo prevStation = previous.get(current);
                

                if (prevStation != null&& !current.colour().equalsIgnoreCase(prevStation.colour())){
                    route.add(0,"*** Change to the " +current.colour()+" line ***");
                    changeCount ++;
                }
                
                route.add(0, current.station() + " on " + current.colour() + " line");
                
               
                current = previous.get(current);
            }   
            totTime = time.get(endState);
            
            return route;
        }
    }
        
        
    record lineInfo(String station, String colour) {}
    class Edge {
        String neighbour ;
        double time;
        String colour;
        
        public Edge(String neighbour, double time, String colour) {
            this.neighbour = neighbour;
            this.time = time;
            this.colour = colour;
        }
       

    }