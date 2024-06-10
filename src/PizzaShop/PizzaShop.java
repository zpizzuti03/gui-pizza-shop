package PizzaShop;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PizzaShop {
	
	double toppingsTotal = 0, pizzaCost = 16.05; 
	
	JFrame pizzaFrame;
	JPanel pizzaPanel;
	JLabel titleLbl, sizeLbl, toppingsLbl, priceLbl, picLbl;
	JButton compBtn;
	JRadioButton md_pizzaBtn, lg_pizzaBtn;
	JCheckBox tcb_pepperoni, tcb_onions, tcb_cheese, tcb_mushroom;
	BufferedImage pizzaImage;			//Subclass of Image class for pictures
	ButtonGroup sizeBtns;				//Group for radio buttons
	
	//Constructor 
	public PizzaShop(){
		pizzaFrame = new JFrame();									//Create the frame
		pizzaFrame.setSize(500, 500);								//Sets size of frame (width, height)
		pizzaFrame.setTitle("Downtown Pizza Store");				//Sets the title of the frame on the bar at the top.
		pizzaFrame.setLocationRelativeTo(null);						//Opens the frame at the center of screen
		pizzaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Makes the program stop on pressing the X.
		
		//Create instances of listeners
		ActionListener cl1 = new controlsListener();
		ActionListener clk1 = new ClickListener();
		
		//Create panel and set layout to null
		pizzaPanel = new JPanel();
		pizzaPanel.setLayout(null);					//Allows the use of setBounds
		
		//Create all controls for panel (Components are arranged by y position, minus the image which is at the bottom)
		titleLbl = new JLabel("Downtown Pizza Store");
		titleLbl.setBounds(140, 20, 300, 60);
		titleLbl.setFont(new Font("Courier", Font.BOLD, 20));
		
		sizeLbl = new JLabel("Select pizza size:");
		sizeLbl.setBounds(50, 70, 200, 60);							//x, y, width, height
		sizeLbl.setFont(new Font("Serif", Font.ITALIC, 16));		//Stylize text with font
		
		md_pizzaBtn = new JRadioButton("Medium");
		md_pizzaBtn.setBounds(80, 120, 80, 20);						//x, y, width, height
		md_pizzaBtn.addActionListener(cl1);	//Add ActionListeners to the buttons
		
		lg_pizzaBtn = new JRadioButton("Large", true);
		lg_pizzaBtn.setBounds(170, 120, 80, 20);					//x, y, width, height
		lg_pizzaBtn.addActionListener(cl1);
		
		toppingsLbl = new JLabel("Select toppings:");
		toppingsLbl.setBounds(50, 140, 150, 60);					//x, y, width, height
		toppingsLbl.setFont(new Font("Serif", Font.ITALIC, 16));
		
		tcb_pepperoni = new JCheckBox("Pepperoni");
		tcb_pepperoni.setBounds(80, 190, 100, 20);					//x, y, width, height
		tcb_pepperoni.addActionListener(cl1);
		
		tcb_onions = new JCheckBox("Onions");
		tcb_onions.setBounds(80, 220, 100, 20);						//x, y, width, height
		tcb_onions.addActionListener(cl1);
		
		tcb_cheese = new JCheckBox("Extra Cheese");	
		tcb_cheese.setBounds(80, 250, 120, 20);						//x, y, width, height
		tcb_cheese.addActionListener(cl1);
		
		tcb_mushroom = new JCheckBox("Mushroom");
		tcb_mushroom.setBounds(80, 280, 100, 20);					//x, y, width, height
		tcb_mushroom.addActionListener(cl1);
		
		priceLbl = new JLabel("The total price is $" + String.format("%.2f", computeTotal()));	
		priceLbl.setBounds(50, 290, 300, 80);						//x, y, width, height
		priceLbl.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		compBtn = new JButton("Purchase");
		compBtn.setBounds(180, 380, 140, 30);						//x, y, width, height
		compBtn.addActionListener(clk1);
		
		//Open and display the picture *EXTRA CREDIT*
		JLabel picLbl = new JLabel();
		try {
			pizzaImage = ImageIO.read(new File("pizza.jpeg"));
			picLbl.setIcon(new ImageIcon(pizzaImage));
			picLbl.setBounds(300, 100, 150, 150);
		}
		catch(IOException e) {
			System.out.print("Failed to open Image");
		}
		catch(Exception e) {
			System.out.print("Unknown Error");
		}
		
		//Button Group for size options
		sizeBtns = new ButtonGroup();
		sizeBtns.add(md_pizzaBtn);
		sizeBtns.add(lg_pizzaBtn);
		
		//Add controls to panel 
		pizzaPanel.add(titleLbl);
		pizzaPanel.add(sizeLbl);
		pizzaPanel.add(toppingsLbl);
		pizzaPanel.add(priceLbl);
		pizzaPanel.add(compBtn);
		pizzaPanel.add(md_pizzaBtn);
		pizzaPanel.add(lg_pizzaBtn);
		pizzaPanel.add(tcb_pepperoni);
		pizzaPanel.add(tcb_onions);
		pizzaPanel.add(tcb_cheese);
		pizzaPanel.add(tcb_mushroom);
		pizzaPanel.add(picLbl);
		
		//Add the panel to the frame
		pizzaFrame.add(pizzaPanel);
		
		//Last Operation is to make frame visible
		pizzaFrame.setVisible(true);
	}
	
	//General Listener for controls
	class controlsListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			//Radio Buttons
			if(e.getSource() == md_pizzaBtn) {
				pizzaCost = 14.55;
			}
			else if(e.getSource() == lg_pizzaBtn) {
				pizzaCost = 16.05;
			}
			
			//Check Boxes
			toppingsTotal = 0;				//set toppings total to 0 so it does not add over and over
			
			if(tcb_pepperoni.isSelected()) {
				toppingsTotal = toppingsTotal + 3.05;
			}
			if(tcb_onions.isSelected()) {
				toppingsTotal = toppingsTotal + 2.90;
			}
			if(tcb_cheese.isSelected()) {
				toppingsTotal = toppingsTotal + 3.00;
			}
			if(tcb_mushroom.isSelected()) {
				toppingsTotal = toppingsTotal + 2.95;
			}
		}
	}
	
	//Listener for Purchase Button
	class ClickListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			priceLbl.setText("The total price is $" + String.format("%.2f", computeTotal()));
		}
	}
	
	//Methods for background computations
	public double computeTotal() {
		return pizzaCost + toppingsTotal;
	}
	
	//main
	public static void main(String[] args) {
		// Create an instance of the Pizza Shop Window, this is the only thing main does
		PizzaShop instance = new PizzaShop();
	}

}
