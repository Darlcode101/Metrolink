    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.PriorityQueue;
    import java.util.Set;

    public class Dijkstra {

        private double totTime;
        private int changeCount = 0;

            public List<String> findRoute(Hashmap hashmap, String start, String end){
                
                
                Map<String, Double> distances = new HashMap<>();
                Map<String, String> previous = new HashMap<>();
                Map<String, String> previousColour = new HashMap<>();
                String firstcolour = "";

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
                        if (current.equals(start) && firstcolour.equals("")) {
                            firstcolour = edge.colour;
                        }

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

            while (current != null) {
                String prevStation = previous.get(current);
                String colour = previousColour.get(current);

                if (colour != null){
                    route.add(0, current + " on " + colour + " line");
                }else {
                route.add(0, current + " on " + firstcolour + " line");
                }
                if (previousColour.get(prevStation)!= null&&(!previousColour.get(current).equalsIgnoreCase(previousColour.get(prevStation)))){
                    route.add(0,"*** Change to the " +previousColour.get(current)+" line ***");
                    changeCount ++;
                }
                current = previous.get(current);
            }   
            totTime = distances.get(end);
            return route;
        }
        public double getTotTime() {
        return totTime;
    }
        public int getChangeCount() {
        return changeCount;
    }

    }




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
