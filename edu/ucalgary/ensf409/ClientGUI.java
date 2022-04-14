package edu.ucalgary.ensf409;

import java.awt.BorderLayout;

import javax.swing.*;


import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClientGUI extends JFrame implements ActionListener, MouseListener{

    private static int Hampers;
    private int NumberofClients; 
    private int TypeofClient1;
    private int TypeofClient2;
    private int TypeofClient3;
    private int TypeofClient4;

    private JLabel instructions;
    private JLabel NOCLabel;
    private JLabel TOCLabel1;
    private JLabel TOCLabel2;
    private JLabel TOCLabel3;
    private JLabel TOCLabel4;
    

    private JTextField NOCInput;
    private JTextField TOCInput1;
    private JTextField TOCInput2;
    private JTextField TOCInput3;
    private JTextField TOCInput4;
   
    
    public ClientGUI(){
        //super("Ordering Form");
        Hampers = HamperGUI.getHampers();
        for(int i = 1; i <= Hampers; i++){
        JFrame frame = new JFrame("Hamper " + i);
        

        instructions = new JLabel("Please enter the number of clients i.e 4. and Please enter the Type of Client 1,2,3 or 4");
        //Number of clients 
        NOCLabel = new JLabel("Number of Clients");
       
        //Type of Client
        TOCLabel1 = new JLabel("Client 1's: ");
        TOCLabel2 = new JLabel("Client 2's: ");
        TOCLabel3 = new JLabel("Client 3's: ");
        TOCLabel4 = new JLabel("Client 4's: ");
        

        //Input for the Type of Clients 
        TOCInput1 = new JTextField(1);
        TOCInput2 = new JTextField(1);
        TOCInput3 = new JTextField(1);
        TOCInput4 = new JTextField(1);
        

        //Input for the Number of Clients
        NOCInput = new JTextField(3);
        

        //Mouse inputs
        NOCInput.addMouseListener(this);
        TOCInput1.addMouseListener(this);
        TOCInput2.addMouseListener(this);
        TOCInput3.addMouseListener(this);
        TOCInput3.addMouseListener(this);
        TOCInput4.addMouseListener(this);
       

        
        //Submit button 
        JButton submitInfo = new JButton("Submit");
        submitInfo.addActionListener(this);

        //Header Panels 
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());

        //Client Panel 
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        //Submit Panel 
        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());

        //Adding the panels to the GUI 
        headerPanel.add(instructions);

        clientPanel.add(NOCLabel);
        clientPanel.add(NOCInput);
        clientPanel.add(TOCLabel1);
        clientPanel.add(TOCInput1);
        clientPanel.add(TOCLabel2);
        clientPanel.add(TOCInput2);
        clientPanel.add(TOCLabel3);
        clientPanel.add(TOCInput3);
        clientPanel.add(TOCLabel4);
        clientPanel.add(TOCInput4);
        
        

        submitPanel.add(submitInfo);
        //frame.getContentPane().add(BorderLayout.NORTH, buttonsPanel);

        //Layout of the panels 
        frame.getContentPane().add(headerPanel, BorderLayout.NORTH);
        frame.getContentPane().add(clientPanel, BorderLayout.CENTER);
        frame.getContentPane().add(submitPanel, BorderLayout.PAGE_END);
        
        frame.setSize(600,200);   //sizing the GUI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Default close 
        frame.setVisible(true);

        frame.invalidate();
        frame.validate();
        frame.repaint();


    }
}
    //String Hamper[][] = new REQUEST[Hampers][Order];


    
    public void actionPerformed(ActionEvent event){
        
        //Gets the input 
        NumberofClients = Integer.parseInt(NOCInput.getText());
        TypeofClient1 = Integer.parseInt(TOCInput1.getText());
        TypeofClient2 = Integer.parseInt(TOCInput2.getText());
        TypeofClient3 = Integer.parseInt(TOCInput3.getText());
        TypeofClient4 = Integer.parseInt(TOCInput4.getText());
       


        if(validInput()){ //Checks for valid input 
            String NumberID = NumberIDProcessing();
            String ClientID1 = ClientID1Processing();
            String ClientID2 = ClientID2Processing();
            String ClientID3 = ClientID3Processing();
            String ClientID4 = ClientID4Processing();
            

            
            //Shows what the user has entered i.e Number of Clients is ...
            //Shows what the user has entered i.e Client 1 ID: ... depending on Number of Clients

            JOptionPane.showMessageDialog(this, "This is the Number of Clients: " + NumberID);

            JOptionPane.showMessageDialog(this,"Number of Client 1's: " + ClientID1 + "\n"+ "Number of Client 2's: " 
            + ClientID2 + "\n" +"Number of Client 3's: " + ClientID3 +"\n"+ "Number of Client 4's: " + ClientID4);
            
            
            
        }

    }
    
    
    //Mouse click responses 
    public void mouseClicked(MouseEvent event){

    }
    public void mouseEntered(MouseEvent event){

    }
    public void mouseExited(MouseEvent event){

    }
    public void mousePressed(MouseEvent event){

    }
    public void mouseReleased(MouseEvent event){

    }

    //Processes the Number of Clients
    private String NumberIDProcessing(){
        
        String NumberID = new String(String.valueOf(NumberofClients));

        return NumberID;

    }
    //Processes the Type of Clients 
    private String ClientID1Processing(){
        
        String ClientID1 = new String(String.valueOf(TypeofClient1));

        return ClientID1;
    }

    private String ClientID2Processing(){
        
        String ClientID2 = new String(String.valueOf(TypeofClient2));

        return ClientID2;
    }

    private String ClientID3Processing(){
        
        String ClientID3 = new String(String.valueOf(TypeofClient3));

        return ClientID3;
    }

    private String ClientID4Processing(){
        
        String ClientID4 = new String(String.valueOf(TypeofClient4));

        return ClientID4;
    }

    //Function that checks for valid input 
    private boolean validInput(){

        boolean allValidInput = true;

        if(NumberofClients != TypeofClient1 + TypeofClient2 + TypeofClient3 + TypeofClient4){
            allValidInput = false; 
            JOptionPane.showMessageDialog(this, "Number of Clients doesn't match up with Number of Type of Clients");
        }


        if(NumberofClients < 0){
            allValidInput = false; 
            JOptionPane.showMessageDialog(this, NumberofClients + "is an invalid Number of Clients.");

        }
        if(TypeofClient1 < 0 ){
            allValidInput = false;
            JOptionPane.showMessageDialog(this, TypeofClient1 + "is an invalid Type of Client.");
        }

        if(TypeofClient2 < 0 ){
            allValidInput = false;
            JOptionPane.showMessageDialog(this, TypeofClient2 + "is an invalid Type of Client.");
        }

        if(TypeofClient3 < 0 ){
            allValidInput = false;
            JOptionPane.showMessageDialog(this, TypeofClient3 + "is an invalid Type of Client.");

        }

        if(TypeofClient4 < 0 ){
            allValidInput = false;
            JOptionPane.showMessageDialog(this, TypeofClient4 + "is an invalid Type of Client.");
        }

            return allValidInput;
    }

}
