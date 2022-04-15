package edu.ucalgary.ensf409;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;


import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HamperGUI extends JFrame implements ActionListener, MouseListener{

    private static int NumberofHampers;

    private JLabel instructions;
    private JLabel HamperLabel;
    private JTextField HamperInput;
    private int counter = 0;
    private int counter2 = -1;
    private static String[][] Hampers;
    private static int hamperCounter = 0;

    public HamperGUI(){
        super("Hamper GUI");
        setupHamper(); //Calls a method to allow for GUI to work
        setSize(400,150); //sizing of the GUI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Default Close 
    }

    public void setupHamper(){
        //Instructions for GUI
        instructions = new JLabel("Enter desired amount of Hampers");
        //Number of Hampers
        HamperLabel = new JLabel("Number of Hampers: ");

        //Input for Number of Hampers
        HamperInput = new JTextField(2);

        //Mouse inputs 
        HamperInput.addMouseListener(this);

        //Submit button
        JButton submitInfo = new JButton("Submit");
        submitInfo.addActionListener(this);
        if (this != null)
        {
            counter = 1;
        }

        //Instruction Panel 
        JPanel instructionPanel = new JPanel();
        instructionPanel.setLayout(new FlowLayout());

        //Hamper Panel 
        JPanel HamperPanel = new JPanel();
        HamperPanel.setLayout(new FlowLayout());

        //Submit Panle 
        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());

        instructionPanel.add(instructions);
        submitPanel.add(submitInfo);
        HamperPanel.add(HamperLabel);
        HamperPanel.add(HamperInput);

        //Layout of the Panels 
        this.add(instructionPanel, BorderLayout.NORTH);
        this.add(HamperPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);

    }

    public void actionPerformed(ActionEvent event){

        //Gets the input 
        NumberofHampers = Integer.parseInt(HamperInput.getText());

        //If the Input is valid goes to Client GUI and displays the number of Hampers reqeusted
        if(validInput() && counter2 != 0){
            String Hampers = HamperNumber();
            JOptionPane.showMessageDialog(this, "Number of Hampers: " + Hampers);
            int numberOfHampers = getHampers();
            for(int i = 1; i <= numberOfHampers; i++){
                if (counter == 1)
                {
                    counter2 = numberOfHampers;
                    counter = -1;
                }
                new ClientGUI(i);
                counter2--;
            }
            EventQueue.invokeLater(() -> {
                //new ClientGUI().setVisible(true);

            });
        }
    }

    //Mouse inputs 
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
    
    //Returns the Number of Hampers 
    private String HamperNumber(){
        String Hampers = new String(String.valueOf(NumberofHampers));

        return Hampers;
    }

    //Checks for Valid input
    private boolean validInput(){
        boolean allValidInput = true;

        if(NumberofHampers <= 0 || NumberofHampers > 10){
            allValidInput = false;
            JOptionPane.showMessageDialog(this,NumberofHampers + "is an invalid input");
        }

        return allValidInput;
    }
    public static int getHampers(){
        return NumberofHampers;
    }

    public static void addHampers(String[] hamper, int i)
    {
        Hampers = new String[hamperCounter + 1][4];
        hamperCounter++;

        for (int j = 0; j < Hampers[i - 1].length; j++)
        {
            System.out.println(i + "---" + j);
            Hampers[i - 1][j] = hamper[j];
            System.out.println(Hampers[i-1][j]);
        }

        if (Hampers.length == NumberofHampers)
        {
            //Order(Hampers);
        }
    }


public static void main(String[] args){

    EventQueue.invokeLater(() -> {
        new HamperGUI().setVisible(true);
    });
}

}