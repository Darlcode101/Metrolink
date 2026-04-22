

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class GUI implements ActionListener{
    public GUI(){

        //file location to run the program from my mac
        //String file = "/Users/alexdarlington/Desktop/summer project/Metrolink_times_linecolour(in).csv";
        
        String file = "/home/darling6/h-drive/Summer project/Metrolink/Metrolink_times_linecolour(in).csv";
        Scanner input = new Scanner(System.in);


        //TITLE
        JFrame frame = new JFrame("Manchester metro route planner");
        frame.setSize(428,428);
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
        
        JTextField departureinput = new JTextField(20);
        departureinput.setBounds(15,70, 150,20);
        frame.add(departureinput);
        
        
        //ARRIVAL
        JLabel arrival = new JLabel("Enter arrival");
        arrival.setBounds(15, 100, 300, 100);
        frame.add(arrival);
        
        JButton arrivalButton = new JButton("confirm choice");
        arrivalButton.setBounds(214, 150, 200, 60);
        frame.add(arrivalButton);

        JTextField arrivalinput = new JTextField(20);
        arrivalinput.setBounds(15,170, 150,20);
        frame.add(arrivalinput);


        departureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                departure.setText("you have confirmed your choice");
            }
        });

        arrivalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                arrival.setText("you have confirmed your choice");
            }
        });

        frame.setVisible(true);
         
    }

}