package finalproject;
/* TODO Implement the following actions
 * We will do it on the same frame.
 * Use button.setVisible(boolean) to hide the buttons.
 * Should always allow Exit button can be moved however.
 * Withdraw
 * Deposit 
 * Change Password
 * Check Balance
 * Transfer
 * Exit
 */
//TODO Figure out how to deal with not active account.
//Should they be able to reactivate? 
//Most Buttons should be grayed out and disabled except maybe change pass and reactivate account.

import java.util.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


@SuppressWarnings("serial")
public class AccountScreen extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton tranBtn, depositBtn, chngPassBtn, balanceBtn, withdrawBtn, exitBtn;
	private final int     Account_Number;
	private 	  String  First_Name;
	private 	  String  Last_Name;
	private 	  double  Balance;
	private 	  boolean Active;

	/**
	 * Create the frame.
	 */
	public AccountScreen(int accountNum) {
		Account_Number=accountNum;
		startUp();
		this.setTitle("Welcome "+First_Name+" "+Last_Name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		drawHomeScreen();

		Debug();
	}
	
	private void drawHomeScreen(){

		depositBtn = new JButton("Deposit");
		depositBtn.setToolTipText("Deposit money into account");
		depositBtn.setEnabled(true);
		depositBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		depositBtn.setBounds(10, 30, 125, 75);
		contentPane.add(depositBtn);
		
		chngPassBtn = new JButton("<html><center>Change<br>Password</center></html>");
		chngPassBtn.setToolTipText("Change Your Password");
		chngPassBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		chngPassBtn.setEnabled(true);
		chngPassBtn.setBounds(10, 230, 125, 75);
		contentPane.add(chngPassBtn);
		
		balanceBtn = new JButton("<html><center>Check<br>Balance</center></html>");
		balanceBtn.setToolTipText("Check Balance of Account");
		balanceBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		balanceBtn.setEnabled(true);
		balanceBtn.setBounds(10, 130, 125, 75);
		contentPane.add(balanceBtn);
		
		withdrawBtn = new JButton("Withdraw");
		withdrawBtn.setToolTipText("");
		withdrawBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		withdrawBtn.setEnabled(true);
		withdrawBtn.setBounds(399, 30, 125, 75);
		contentPane.add(withdrawBtn);
		
		tranBtn = new JButton("<html><center>Make a<br>Transfer</center><html>");
		tranBtn.setToolTipText("Transfer Money to Another Account");
		tranBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		tranBtn.setEnabled(true);
		tranBtn.setBounds(399, 130, 125, 75);
		contentPane.add(tranBtn);
		
		exitBtn = new JButton("Exit");
		exitBtn.setToolTipText("Exit and Logout");
		exitBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		exitBtn.setEnabled(true);
		exitBtn.setBounds(399, 230, 125, 75);
		contentPane.add(exitBtn);
		
		exitBtn.addActionListener(this);
		depositBtn.addActionListener(this);
		withdrawBtn.addActionListener(this);
		balanceBtn.addActionListener(this);
		chngPassBtn.addActionListener(this);
		tranBtn.addActionListener(this);
	}
	
	private void Debug(){
		System.out.println("Account Number: "+Account_Number);
		System.out.println("Last Name: "+Last_Name);
		System.out.println("First Name: " + First_Name);
		System.out.println("Balance: " + Balance);
		System.out.println("Active: " + Active);
	}
	
	private void startUp(){//Loads account info file. Should only load relevant account information

		
		try{
			Scanner file = new Scanner(new File("AccountInformation.txt"));
			while(file.hasNext()){
				int accntNum = file.nextInt();
				System.out.println("accnt Num: "+accntNum);
				if(Account_Number == accntNum){
					file.nextLine();//Throws away the \n left by the nextInt()
					System.out.println(0);
					Last_Name = file.nextLine();
					System.out.println(1);
					First_Name = file.nextLine();
					System.out.println(2);
					Balance = file.nextDouble();
					System.out.println(3);
					if(file.nextLine().equals("Active")){
						Active = true;
					}
					else{
						Active = false;
					}
					break;
								
				}
				else{//Skip wrong account
					file.nextLine();
					file.nextLine();
					file.nextLine();
					file.nextLine();
					file.nextLine();
				}
				//System.out.println("BOOP");
				
				
			}
			file.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File Not Found: Account Information");
		}
		catch(InputMismatchException e){
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(tranBtn== event.getSource()){
			transfer();
		}
		else if(transferButton == event.getSource()){
			try{
			transfer(Long.parseLong(accntNumberField.getText()),Double.parseDouble(amntField.getText()));
			}
			catch(NumberFormatException e){
				e.printStackTrace();
			}
		}
		else if(exitBtn== event.getSource()){
			exit();
		}
		else if(balanceBtn== event.getSource()){
			checkBalance();
		}
		else if(withdrawBtn== event.getSource()){
			withdraw();
			
		}
		else if(withdrawButton == event.getSource() ){
			try{
			withdraw(Double.parseDouble(withdrawField.getText()));
			}
			catch(NumberFormatException e){
				alert("Please enter an ammount to withdraw field.");
			}
		}
		else if(chngPassBtn== event.getSource()){
			changePassword();
		}
		else if(chngPassBtn == event.getSource()){
			changePassword(newpass.getText());
			
		}
		
		
		else if(depositBtn== event.getSource()){
			deposit();
		}
		else if(depositButton== event.getSource()){
			try{
				deposit(Double.parseDouble(depositField.getText()));
				}
				catch(NumberFormatException e){
					alert("Please enter an ammount to deposit field.");
				}
		}
		else if(returnBtn == event.getSource()){
			/*if(withdrawButton!=null)withdrawButton.setVisible(false);
			if(withdrawField!=null)withdrawField.setVisible(false);
			if(returnBtn!=null)returnBtn.setVisible(false);
			if(inbeddedLabel!=null)inbeddedLabel.setVisible(false);
			if(depositButton!=null)depositButton.setVisible(false);
			if(depositField != null)depositField.setVisible(false);
			if(accntNumberField!=null)accntNumberField.setVisible(false);
			if(amntField!=null)amntField.setVisible(false);
			if(transferButton!=null)transferButton.setVisible(false);
			if(inbeddedLabel2!=null)inbeddedLabel2.setVisible(false);*/
			for(Component c:contentPane.getComponents()){
				c.setVisible(false);
			}
			
			withdrawBtn.setVisible(true);
			tranBtn.setVisible(true);
			balanceBtn.setVisible(true);
			chngPassBtn.setVisible(true);
			exitBtn.setVisible(true);
			depositBtn.setVisible(true);
			System.out.println("Return");
		}
		
	}

	private void withdraw(double amnt) {
		if(Balance - amnt >=0 ){
			Balance -=amnt;
			alert("Amount Succesfully Withdrawn");
		}
		else{
			alert("Withdraw Failed: Insufficient Funds");
		}
		writeAccnt();
	}

	private void alert(String text) {
		System.out.println("BOOP");
		Alert a = new Alert(text);
		a.setVisible(true);
		
	}
	private JTextField depositField;
	private JButton    depositButton;
	private void deposit() {
		clearScreen();
		inbeddedLabel = new JLabel("Amount to Deposit: $");
		inbeddedLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		inbeddedLabel.setBounds(10, 160, 216, 20);
		contentPane.add(inbeddedLabel);
		
		depositField = new JTextField();
		depositField.setBounds(225, 163, 165, 20);
		contentPane.add(depositField);
		depositField.setColumns(10);
		
		returnBtn = new JButton("\u2190 Return");
		returnBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		returnBtn.setBounds(10, 11, 134, 23);
		contentPane.add(returnBtn);
		returnBtn.addActionListener(this);
		
		depositButton = new JButton("<html><center>Deposit<br>Amount</center></html>");
		depositButton.addActionListener(this);
		depositButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		depositButton.setBounds(403, 195, 121, 53);
		contentPane.add(depositButton);
		repaint();
		
	}
	private void deposit(Double amnt){
		Balance +=amnt ;
		alert("Amount Deposited. New Balance is $"+Balance+".");
	}
	
	/*
	 * Passwords cannot contain spaces.
	 * */
	private JLabel chngpassLabel;
	private JTextField newpass;
	private JButton chngpassButton;
	
	private void changePassword() {//You can look at writeAccnt() and Main.login() to see how to do this.
		// TODO Auto-generated method stub
		clearScreen();
		
		chngpassLabel=new JLabel("Enter the new password: ");
		chngpassLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		chngpassLabel.setBounds(10, 160, 216, 20);	//??Haven't tested the bounds yet
		contentPane.add(chngpassLabel);
		
		newpass=new JTextField();
		newpass.setBounds(225, 163, 165, 20);	//??Haven't tested the bounds yet
		contentPane.add(newpass);
		newpass.setColumns(10);
		
		returnBtn = new JButton("\u2190 Return");
		returnBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		returnBtn.setBounds(10, 11, 134, 23);	//??Haven't tested the bounds yet
		contentPane.add(returnBtn);
		returnBtn.addActionListener(this);
		
		chngpassButton=new JButton("Change the password");
		chngpassButton.addActionListener(this);
		chngpassButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		chngpassButton.setBounds(403, 195, 121, 53);	//??Haven't tested the bounds yet
		contentPane.add(chngpassButton);
		
		}
	private void changePassword(String newpassword){
		try{
			Scanner input=new Scanner(System.in);
			
			
			
		
		}
		catch(IOException e){
			System.out.println("Unable to change password.\n");
		}
		
		
	}
	
	private JButton withdrawButton, returnBtn;
	private JTextField withdrawField;
	private JLabel inbeddedLabel;
	private void clearScreen(){//Used to hide home screen buttons
		withdrawBtn.setVisible(false);
		tranBtn.setVisible(false);
		balanceBtn.setVisible(false);
		chngPassBtn.setVisible(false);
		exitBtn.setVisible(false);
		depositBtn.setVisible(false);
	}
	
	/*
	 * Check to make sure they can withdraw that amount. Compare to $Balance
	 * Withdraw amount from balance. Open file and write change to file.
	 */
	private void withdraw() {
		clearScreen();
		
		inbeddedLabel = new JLabel("Amount to Withdraw: $");
		inbeddedLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		inbeddedLabel.setBounds(10, 160, 216, 20);
		contentPane.add(inbeddedLabel);
		
		withdrawField = new JTextField();
		withdrawField.setBounds(225, 163, 165, 20);
		contentPane.add(withdrawField);
		withdrawField.setColumns(10);
		
		returnBtn = new JButton("\u2190 Return");
		returnBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		returnBtn.setBounds(10, 11, 134, 23);
		contentPane.add(returnBtn);
		returnBtn.addActionListener(this);
		
		withdrawButton = new JButton("<html><center>Withdraw<br>Amount</center></html>");
		withdrawButton.addActionListener(this);
		withdrawButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		withdrawButton.setBounds(403, 195, 121, 53);
		contentPane.add(withdrawButton);
		
	}

	private void checkBalance() {
		clearScreen();
		returnBtn = new JButton("\u2190 Return");
		returnBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		returnBtn.setBounds(10, 11, 134, 23);
		contentPane.add(returnBtn);
		returnBtn.addActionListener(this);
		returnBtn.setVisible(true);
		
		inbeddedLabel = new JLabel("Your Balance is: $"+((Balance%.1!=0)?(Balance+"0"):(Balance)));
		System.out.println(Balance%.1);
		inbeddedLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		inbeddedLabel.setBounds(10, 160, 550, 20);
		contentPane.add(inbeddedLabel);
		writeAccnt();
		
	}

	private void exit() {
		System.exit(0);
	}
	
	
	/*
	 * Only transfer to existing accounts
	 * */
	private JTextField accntNumberField,amntField;
	private JButton transferButton;
	private JLabel inbeddedLabel2;
	private void transfer() {
		clearScreen();
		returnBtn = new JButton("\u2190 Return");
		returnBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		returnBtn.setBounds(10, 11, 134, 23);
		contentPane.add(returnBtn);
		returnBtn.addActionListener(this);
		
		inbeddedLabel = new JLabel("Amount to Deposit: $");
		inbeddedLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		inbeddedLabel.setBounds(10, 187, 216, 20);
		contentPane.add(inbeddedLabel);
		
		inbeddedLabel2 = new JLabel("Transfer Fund to: ");
		inbeddedLabel2.setFont(new Font("Tahoma", Font.BOLD, 18));
		inbeddedLabel2.setBounds(10, 160, 216, 20);
		contentPane.add(inbeddedLabel2);
		
		amntField = new JTextField();
		amntField.setBounds(209, 187, 134, 20);
		contentPane.add(amntField);
		amntField.setColumns(10);
		
		accntNumberField = new JTextField();
		accntNumberField.setColumns(10);
		accntNumberField.setBounds(209, 160, 134, 20);
		contentPane.add(accntNumberField);
		
		transferButton = new JButton("<html><center>Transfer</center></html>");
		transferButton.addActionListener(this);
		transferButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		transferButton.setBounds(386, 218, 121, 53);
		contentPane.add(transferButton);
		
	}
	
	private void transfer(long accntNumber, double amnt){
		if(Balance< amnt){
			alert("Insuffcient Funds: Transfer Cancelled.");
			return;
		}
		else if(checkExist(accntNumber)){
			Balance-=amnt;
			try {
				
				Scanner read =new Scanner(new File("AccountInformation.txt"));
				String text="";
				while(read.hasNext()){
					long accntNum = read.nextLong();
					read.nextLine();
					if(accntNum == accntNumber){
						text += accntNum + "\n"+read.nextLine()+"\n"+read.nextLine()+"\n"+(read.nextDouble()+amnt);
						read.nextLine();
						text+="\n"+read.nextLine()+"\n";
					}
					else{
						text+=accntNum+"\n"+read.nextLine()+"\n"+read.nextLine()+"\n"+read.nextLine()+"\n"+read.nextLine()+"\n";
					}
					System.out.println("***");
				}
				read.close();
				PrintWriter writer = new PrintWriter("AccountInformation.txt");
				writer.write(text);
				writer.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			writeAccnt();
			alert("Transfer Successful");
		}
		else{
			alert("Account does not exist. Please check Account Number and try again.");
			return;
		}
	}
	
	private void writeAccnt(){//Writes all current Information to AccountInformation.txt *This does not include the password.
		try {
			
			Scanner read =new Scanner(new File("AccountInformation.txt"));
			String text="";
			while(read.hasNext()){
				long accntNum = read.nextLong();
				read.nextLine();
				if(accntNum == Account_Number){
					text += accntNum + "\n"+Last_Name+"\n"+First_Name+"\n"+Balance+"\n"+((Active)?("Active"):("Not Active"))+"\n";
					read.nextLine();//Used to skip over lines of data that are replaced
					read.nextLine();
					read.nextLine();
					read.nextLine();
				}
				else{
					text+=accntNum+"\n"+read.nextLine()+"\n"+read.nextLine()+"\n"+read.nextLine()+"\n"+read.nextLine()+"\n";
				}
			}
			read.close();
			PrintWriter writer = new PrintWriter("AccountInformation.txt");
			writer.write(text);
			writer.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	private boolean checkExist(long accntNumber){
		try{
			Scanner sc = new Scanner(new File("LoginInformation.txt"));
			while(sc.hasNext()){
				String input = sc.nextLine();
				String []in=input.split(" ");
				if(Long.parseLong(in[0]) == accntNumber){
					sc.close();
					return true;
				}
			}
			sc.close();
			return false;
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
			return false;
		}
		
	}
}
