package edu.ucalgary.ensf409;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIProject extends JFrame implements ActionListener, MouseListener{

    private int NumberofClients;
    private int TypeofClients;

    private JLabel instructions;
    private JLabel NOCLabel;
    private JLabel TOCLabel;

    private JTextField NOCInput;
    private JTextField TOCInput;

    public GUIProject(){
        super("Ordering Form");
        setupGUI();
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void setupGUI(){
        instructions = new JLabel("Please enter the number of clients");
        NOCLabel = new JLabel("Number of Clients");
        TOCLabel = new JLabel("Client Number");

        NOCInput = new JTextField("e.g 4", 3);
        TOCInput = new JTextField("e.g 1", 1);

        NOCInput.addMouseListener(this);
        TOCInput.addMouseListener(this);

        JButton submitInfo = new JButton("Submit");
        submitInfo.addActionListener(this);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());

        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());

        headerPanel.add(instructions);
        clientPanel.add(NOCLabel);
        clientPanel.add(NOCInput);
        clientPanel.add(TOCLabel);
        clientPanel.add(TOCInput);
        submitPanel.add(submitInfo);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);


        
        

    }
    public void actionPerformed(ActionEvent event){
        NumberofClients = Integer.parseInt(NOCInput.getText());
        TypeofClients = Integer.parseInt(TOCInput.getText());

        if(validInput()){
            String NumberID = NumberIDProcessing();
            String ClientID = ClientIDProcessing();
            JOptionPane.showMessageDialog(this, "This is the Number of clients: " + NumberID);
            JOptionPane.showMessageDialog(this,"Client IDs: " + ClientID);

        }

    }

    public void mouseClicked(MouseEvent event){

        if(event.getSource().equals(NOCInput))
            NOCInput.setText("");

        if(event.getSource().equals(TOCInput))
            TOCInput.setText("");

    }
    public void mouseEntered(MouseEvent event){

    }
    public void mouseExited(MouseEvent event){

    }
    public void mousePressed(MouseEvent event){

    }
    public void mouseReleased(MouseEvent event){

    }

    private String ClientIDProcessing(){
        
        String ClientID = new String(String.valueOf(TypeofClients));


        return ClientID;
        
    }
    private String NumberIDProcessing(){
        
        String NumberID = new String(String.valueOf(NumberofClients));

        return NumberID;
        
    }

    private boolean validInput(){

        boolean allValidInput = true;


        if(TypeofClients <= 0 || TypeofClients > 4){
            allValidInput = false;
            JOptionPane.showMessageDialog(this, TypeofClients + "is an invalid Type of Client.");
        }

        if(NumberofClients < 0){
            allValidInput = false; 
            JOptionPane.showMessageDialog(this, NumberofClients + "is an invalid Number of Clients.");
        }


            return allValidInput;
    }

    public static void main(String args[]){
        EventQueue.invokeLater(() -> {
            new GUIProject().setVisible(true);
            //JFrame frame = new JFrame("Ordering Form");
            //frame.setSize(600,400);
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.setVisible(true);
        });
    }
    
}
