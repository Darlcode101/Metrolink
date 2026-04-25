

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class GUI {
    
    private JTextField departureInput;
    private JTextField arrivalInput;
    private String startLocation;
    private String endLocation;
    private Hashmap hashmap = new Hashmap();

    //file location to run the program from my mac
    String file = "/Users/alexdarlington/Desktop/summer project/Metrolink_times_linecolour(in).csv";
    
    //file location to run the program from school computer
    //String file = "/home/darling6/h-drive/Summer project/Metrolink/Metrolink_times_linecolour(in).csv";

    public GUI(){
        hashmap.MapLoader(file);

        //TITLE
        JFrame frame = new JFrame("Manchester metro route planner");
        frame.setSize(1024,428);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        

        //DEPARTURE
        JLabel departure = new JLabel("Enter departure");
        departure.setBounds(15, 0, 300, 100);
        frame.add(departure);
        
        JButton departureButton = new JButton("confirm choice");
        departureButton.setBounds(214, 30, 200, 60);
        frame.add(departureButton);
        
        departureInput = new JTextField(20);
        departureInput.setBounds(15,70, 150,20);
        frame.add(departureInput);
        
        
        //ARRIVAL
        JLabel arrival = new JLabel("Enter arrival");
        arrival.setBounds(15, 100, 300, 100);
        frame.add(arrival);
        
        JButton arrivalButton = new JButton("confirm choice");
        arrivalButton.setBounds(214, 150, 200, 60);
        frame.add(arrivalButton);

        arrivalInput = new JTextField(20);
        arrivalInput.setBounds(15,170, 150,20);
        frame.add(arrivalInput);

        //ROUTE CALCULATOR
        JButton FindRouteButton = new JButton("Calculate Route");
        FindRouteButton.setBounds(15, 300, 400, 40);
        frame.add(FindRouteButton);

        JLabel FindRouteAnswer = new JLabel("");
        FindRouteAnswer.setBounds(15,340,400,40);
        frame.add(FindRouteAnswer);

        JLabel RoutePlan = new JLabel("");
        RoutePlan.setBounds(524, 15, 500, 400);
        frame.add(RoutePlan);


        departureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String tempVal = departureInput.getText();
                if(CSVreader.valueChecker(file, tempVal)){
                    startLocation = tempVal;
                    departure.setText("Departure set to " + startLocation);
                } 
                else{
                    departure.setText("Invalid station, try again");
                }
            }
        });

        arrivalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String tempVal = arrivalInput.getText();
                if(CSVreader.valueChecker(file, tempVal)){
                    endLocation = tempVal;
                    arrival.setText("Arrival set to " + endLocation);
                } 
                else{
                    arrival.setText("Invalid station, try again");
                }
            }
        });

        FindRouteButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (startLocation != null && endLocation != null){
                   
                    Dijkstra dijkstra = new Dijkstra();
                    List <String> route = dijkstra.findRoute(hashmap, startLocation, endLocation);
                    
                    String result = ("route: " + route );

                    RoutePlan.setText(result);
                }
                else {
                    FindRouteAnswer.setText("u gotta select two stops");
                }
            }
        });


        frame.setVisible(true);
         
    }

}