    import java.util.*;

    public class Dijkstra {

        private double totTime;
        private int changeCount = 0;

        public double getTotTime() {
        return totTime;
    }

    public int getChangeCount() {
        return changeCount;
    }    
        

        public List<String> findRoute(Hashmap hashmap, String start, String end, boolean fewestChanges){
                
            this.changeCount = 0;
            this.totTime = 0;

            
            Map<lineInfo, Double> distances = new HashMap<>();
            Map<lineInfo, lineInfo> previous = new HashMap<>();
            
            

            PriorityQueue<lineInfo> pq = new PriorityQueue<>((a, b) -> Double.compare(distances.get(a), distances.get(b)));

            for (Edge edge : hashmap.getNeighbours(start)){
                lineInfo startState = new lineInfo(start, edge.colour);
                distances.put(startState, 0.0);
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
                double punishment = 0 ;
                   
                    if( !edge.colour.equalsIgnoreCase(current.colour())){
                            if (fewestChanges){
                                punishment += 100;
                            }
                            else{
                                punishment += 2.0;
                            }
                        }
                    
                    double newTime = distances.get(current) + edge.time + punishment;

                    
                    lineInfo nextState = new lineInfo(edge.neighbour, edge.colour);
                    
                    
                    if (newTime < distances.getOrDefault(nextState, Double.MAX_VALUE)) {
                        distances.put(nextState, newTime);
                        previous.put(nextState, current);
                        pq.add(nextState);
                    }

                }
                }
            List<String> route = new ArrayList<>();
            
            lineInfo current = endState;

            while (current != null) {
                
                lineInfo prevStation = previous.get(current);
                
                route.add(0, current.station() + " on " + current.colour() + " line");

                if (prevStation != null &&  current.station() != null && !current.colour().equalsIgnoreCase(prevStation.colour())){
                    route.add(0,"*** Change to the " + current.colour()+" line ***");
                    changeCount ++;
                }
                
                
                
               
                current = previous.get(current);
            }   
            totTime = distances.get(endState);
            
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