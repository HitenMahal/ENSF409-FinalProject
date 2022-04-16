package edu.ucalgary.ensf409;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class HamperGUI extends JFrame implements ActionListener, MouseListener{

    // Important Global Variable States
    private static int NumberofHampers;
    private static String[][] Request;
    private static Order createdOrder;
    // Panels and Fields
    private JTextField HamperInput;
    private JPanel masterContainer;
    private CardLayout masterCardLayout = new CardLayout(0,0);
    // Class wide Counters
    private static int hamperCounter = 0;

    public HamperGUI(){
        super("Hamper GUI");
        Inventory.downloadDatabase();
        setupHamper(); //Calls a method to allow for GUI to work
        setSize(600,200); //sizing of the GUI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Default Close 
    }

    public void setupHamper(){
        masterContainer = new JPanel(masterCardLayout);
        this.add(masterContainer);
        createHamperAsker();
    }

    public void actionPerformed(ActionEvent event){
        if (event.getActionCommand().equals("Submit")) {
            handleHamperNumber();
        }
        else if (event.getActionCommand().equals("Create")) {
            handleClientNumber();
        }
    }

    public void handleHamperNumber() {
        //Gets the input 
        NumberofHampers = Integer.parseInt(HamperInput.getText());

        //If the Input is valid goes to Client GUI and displays the number of Hampers reqeusted
        if(validHamperInput()){
            JOptionPane.showMessageDialog(this, "Number of Hampers: " + NumberofHampers);
            Request = new String[NumberofHampers][];
            for(int i = 1; i <= NumberofHampers; i++){
                createClientAsker(i);
            }
            masterCardLayout.next(masterContainer);
        }        
    }

    public void handleClientNumber() {
        JPanel currPanel = null;
        Component[] panels = masterContainer.getComponents();
        for (Component panel : panels) {
            if (panel.isVisible()) {
                currPanel = (JPanel)panel;
                break;
            }
        }
        
        panels = currPanel.getComponents();
        int i = 0;
        String[] values = {"","","","",""};
        for (Component panel : panels) {
            if (panel.getName().equals("Client Input")) {
                JPanel textFields = (JPanel)panel;
                for (Component textField : textFields.getComponents()) {
                    if (textField instanceof JTextField) {
                        JTextField input = (JTextField)textField;
                        values[i] = input.getText();
                        i++;
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(this,"<html>You inputted the following values:<br/>" 
                                        + values[1] + " Adult Males,<br/>" 
                                        + values[2] + " Adult Females,<br/>"
                                        + values[3] + " Children Over 8,<br/>"
                                        + values[4] + " Children Under 8<html/>");

        if ( !validClientInput(values) ) {
            JOptionPane.showMessageDialog(this,"Inputted value is invalid, please try again");
            return;
        }

        Request[hamperCounter] = values;
        hamperCounter++;
        System.out.println(hamperCounter);
        if (hamperCounter == Request.length) {
            System.out.println("Printing Reciept, Hampers="+hamperCounter);
            createRecieptDisplay();
        }
        else {
            masterCardLayout.next(masterContainer);
        }
    }

    //Checks for Valid input
    private boolean validHamperInput(){
        boolean allValidInput = true;
        
        if(NumberofHampers <= 0 || NumberofHampers > 10){
            allValidInput = false;
            JOptionPane.showMessageDialog(this,NumberofHampers + "is an invalid input");
        }
        return allValidInput;
    }

    private boolean validClientInput(String[] values) {
        boolean allValidInput = true;
        for (String num : values) {
            if (Integer.parseInt(num) < 0){
                allValidInput = false;
                return allValidInput;
            };
        }
        if (Integer.parseInt(values[0]) != Integer.parseInt(values[1]) + Integer.parseInt(values[2])
            + Integer.parseInt(values[3]) + Integer.parseInt(values[4])) {
                allValidInput = false;
        }
        return allValidInput;
    }

    public void createHamperAsker() {
        //Instructions for GUI
        JLabel instructions = new JLabel("Enter desired amount of Hampers");
        JPanel instructionPanel = new JPanel();
        instructionPanel.setLayout(new FlowLayout());
        instructionPanel.add(instructions);

        //User Input for Number of Hampers
        JLabel HamperLabel = new JLabel("Number of Hampers: ");
        HamperInput = new JTextField(2);
        HamperInput.addMouseListener(this);
        JPanel HamperPanel = new JPanel();
        HamperPanel.setLayout(new FlowLayout());
        HamperPanel.add(HamperLabel);
        HamperPanel.add(HamperInput);
        HamperPanel.setName("Hamper Input");

        // Submit Button
        JButton submitInfo = new JButton("Submit");
        submitInfo.addActionListener(this);
        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());
        submitPanel.add(submitInfo);

        // Changes color of the GUI
        instructionPanel.setBackground(new Color(189, 245, 255));
        HamperPanel.setBackground(new Color(189, 245, 255));
        submitPanel.setBackground(new Color(189, 245, 255));

        // Create HamperAsker Container
        JPanel HamperAsker = new JPanel();
        //Layout of the Panels 
        HamperAsker.setLayout(new BoxLayout(HamperAsker,BoxLayout.Y_AXIS));
        HamperAsker.add(instructionPanel, BorderLayout.NORTH);
        HamperAsker.add(HamperPanel, BorderLayout.CENTER);
        HamperAsker.add(submitPanel, BorderLayout.PAGE_END);
        HamperAsker.setName("HamperAsker");
        masterContainer.add(HamperAsker);        
    }

    public void createClientAsker(int i) {
        JLabel instructions = new JLabel("<html><h>HAMPER " + i + "<h><br/>"
        + "Please enter the number of clients i.e 4. and Please enter the Type of Client 1,2,3 or 4<html>");
        //Number of clients 
        JLabel NOCLabel = new JLabel("Number of Clients");
       
        //Type of Client
        JLabel TOCLabel1 = new JLabel("Client 1's: ");
        JLabel TOCLabel2 = new JLabel("Client 2's: ");
        JLabel TOCLabel3 = new JLabel("Client 3's: ");
        JLabel TOCLabel4 = new JLabel("Client 4's: ");
        

        //Input for the Type of Clients 
        JTextField TOCInput1 = new JTextField(1);
        JTextField TOCInput2 = new JTextField(1);
        JTextField TOCInput3 = new JTextField(1);
        JTextField TOCInput4 = new JTextField(1);
        

        //Input for the Number of Clients
        JTextField NOCInput = new JTextField(3);
        

        //Mouse inputs
        NOCInput.addMouseListener(this);
        TOCInput1.addMouseListener(this);
        TOCInput2.addMouseListener(this);
        TOCInput3.addMouseListener(this);
        TOCInput3.addMouseListener(this);
        TOCInput4.addMouseListener(this);
       

        
        //Submit button 
        JButton submitInfo = new JButton("Create");
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

        // Set Panel Names
        headerPanel.setName("headerPanel");
        clientPanel.setName("Client Input");
        submitPanel.setName("submitPanel");
        

        submitPanel.add(submitInfo);
        //frame.getContentPane().add(BorderLayout.NORTH, buttonsPanel);

        // ClientAsker Container
        JPanel ClientAsker = new JPanel();
        //Layout of the Panels 
        ClientAsker.setLayout(new BoxLayout(ClientAsker,BoxLayout.Y_AXIS));
        ClientAsker.add(headerPanel, BorderLayout.NORTH);
        ClientAsker.add(clientPanel, BorderLayout.CENTER);
        ClientAsker.add(submitPanel, BorderLayout.PAGE_END);

        ClientAsker.setName("Hamper" + i);
        masterContainer.add(ClientAsker);      
    }

    public void createRecieptDisplay() {
        String reciept = "";
        try {
            //TODO
            System.out.println("Sending Order Request");
            createdOrder = new Order( prepareRequest(Request) );
            System.out.println("Getting OrderForm");
            reciept = OrderForm.getOrderForm(createdOrder);
        } catch (InsufficientFoodException e) {
            reciept = "InsufficientFoodException";
        }

        JLabel recieptText = new JLabel(reciept);
        JPanel recieptPanel= new JPanel();
        recieptPanel.add(recieptText);
        JScrollPane recieptScroller = new JScrollPane(recieptPanel);
        masterContainer.add(recieptScroller);
        masterCardLayout.next(masterContainer);
    }

    public String[][] prepareRequest(String[][] request) {
        String[][] outputRequest = new String[request.length][];
        for (int i =0; i < request.length; i++) {
            int total = Integer.parseInt(request[i][0]);
            int type1 = Integer.parseInt(request[i][1]);
            int type2 = Integer.parseInt(request[i][2]);
            int type3 = Integer.parseInt(request[i][3]);
            int type4 = Integer.parseInt(request[i][4]);
            String[] tmp = new String[total];
            int counter = 0;
            int j = 0;
            for (j=0; j < type1; j++) {
                tmp[counter] = "1";
                counter++;
            }
            for (j=0; j < type2; j++) {
                tmp[counter] = "2";
                counter++;
            }
            for (j=0; j < type3; j++) {
                tmp[counter] = "3";
                counter++;
            }
            for (j=0; j < type4; j++) {
                tmp[counter] = "4";
                counter++;
            }
            outputRequest[i] = tmp;
        }
        return outputRequest;
    }

//Mouse inputs 
public void mouseClicked(MouseEvent event){}
public void mouseEntered(MouseEvent event){}
public void mouseExited(MouseEvent event){}
public void mousePressed(MouseEvent event){}
public void mouseReleased(MouseEvent event){}

public static void main(String[] args){
    EventQueue.invokeLater(() -> {
        new HamperGUI().setVisible(true);
    });
}

}