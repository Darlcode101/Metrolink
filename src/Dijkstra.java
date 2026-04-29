    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.PriorityQueue;
    import java.util.Set;

    public class Dijkstra {

        private double totTime;
        private int changeCount = 0;
        

            public List<String> findRoute(Hashmap hashmap, String start, String end, boolean useCost){
                
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
                        int penalty = 0;

                        if(previousColour.get(current)!= null && !previousColour.get(current).equalsIgnoreCase(compareColour))
                        {
                            if (useCost){
                                penalty = 100;
                            }
                            else{
                                travelTime += 2;
                            }
                        }
                        
                        
                        totTime = distances.get(current) + travelTime + penalty ; 

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
            
            if (useCost){
                totTime = totTime - (changeCount  * 100) + (changeCount * 2);
            }
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
