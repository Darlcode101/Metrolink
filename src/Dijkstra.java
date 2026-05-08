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
            
            //stores the actual time rather than the weight
            Map<lineInfo, Double> realTime = new HashMap<>();
            

            PriorityQueue<lineInfo> pq = new PriorityQueue<>((a, b) -> Double.compare(distances.get(a), distances.get(b)));

            for (Edge edge : hashmap.getNeighbours(start)){
                lineInfo startState = new lineInfo(start, edge.colour);
                distances.put(startState, 0.0);
                realTime.put(startState, 0.0);
                pq.add(startState);
                }
            
            lineInfo endState = null;

            while (!pq.isEmpty()){
                lineInfo currentStation = pq.poll();
                
                if (currentStation.station().equals(end)) {
                endState = currentStation;
                break;
                }

                for (Edge edge : hashmap.getNeighbours(currentStation.station())) {
                double punishment = 0.0 ;
                double changeTime = 0.0;
                   
                    if( !edge.colour.equals(currentStation.colour())){
                            if (fewestChanges){
                                punishment += 100.0;
                                changeTime += 2.0;
                            }
                            else{
                                punishment += 2.0;
                                changeTime += 2.0;
                            }
                        }
                    
                    double timeWithPunishment = distances.get(currentStation) + edge.time + punishment;
                    
                    double RealTime = realTime.get(currentStation) + edge.time + changeTime;
                    
                    lineInfo nextStation = new lineInfo(edge.neighbour, edge.colour);
                    
                    
                    if (timeWithPunishment < distances.getOrDefault(nextStation, Double.MAX_VALUE)) {
                        distances.put(nextStation, timeWithPunishment);
                        realTime.put(nextStation, RealTime);
                        previous.put(nextStation, currentStation);
                        pq.add(nextStation);
                    }

                }
                }
            List<String> route = new ArrayList<>();
            
            lineInfo currentStation = endState;

            while (currentStation != null) {
                
                lineInfo prevStation = previous.get(currentStation);
                
                route.add(0, currentStation.station() + " on " + currentStation.colour() + " line");

                if (prevStation != null &&  currentStation.station() != null && !currentStation.colour().equals(prevStation.colour())){
                    route.add(0,"*** Change to the " + currentStation.colour()+" line ***");
                    changeCount ++;
                }
                
                
                
               
                currentStation = previous.get(currentStation);
            }   
            totTime = realTime.get(endState);
            
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