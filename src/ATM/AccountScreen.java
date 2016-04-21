package ATM;
/* TODO Implement the following actions
 * We will do it on the same frame.
 * Use button.setVisible(boolean) to hide the buttons.
 * Should always allow return button can be moved however.
 * Change Password
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
	private JButton tranBtn, depositBtn, chngPassBtn, balanceBtn, withdrawBtn, exitBtn, reactivateBtn;
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
		
		/*
		 * If account is not active it disables all the buttons
		 * Also creates the reactivate account button
		 * */
		if(!Active){
			withdrawBtn.setEnabled(false);
			tranBtn.setEnabled(false);
			depositBtn.setEnabled(false);
			balanceBtn.setEnabled(false);
			
			reactivateBtn = new JButton("<html><center>Reactivate<br>Account</center></html>");
			reactivateBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
			reactivateBtn.setBounds(204, 130, 125, 75);
			contentPane.add(reactivateBtn);
			reactivateBtn.addActionListener(this);
		}
	}
	
	/*
	 * Used to debug to console.
	 * */
	/*
	private void Debug(){
		System.out.println("Account Number: "+Account_Number);
		System.out.println("Last Name: "+Last_Name);
		System.out.println("First Name: " + First_Name);
		System.out.println("Balance: " + Balance);
		System.out.println("Active: " + Active);
	}
	*/
	
	/*
	 * Reads all account info from file.
	 * Does not load other account details.
	 * Also checks if the account is active
	 * */
	private void startUp(){
		
		try{
			Scanner file = new Scanner(new File("AccountInformation.txt"));
			while(file.hasNext()){
				int accntNum = file.nextInt();
				if(Account_Number == accntNum){
					file.nextLine();//Throws away the \n left by the nextInt()
					Last_Name = file.nextLine();
					First_Name = file.nextLine();
					Balance = file.nextDouble();
					file.nextLine();//Throw away new Line
					String act = file.nextLine().trim();
					if(act.equals("Active")){
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
				
			}
			file.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File Not Found: Account Information");
			e.printStackTrace();
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
				alert("Invalid Input.");
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
				alert("Invalid Amount.");
			}
		}
		else if(chngPassBtn== event.getSource()){
			changePassword();
		}
		// Checks if the passwords are the same. 
		// Also checks if there is a password at all
		// Can easily change to require larger password size requirements
		else if(changePasswordButton == event.getSource()){
			if(passwordField.getText().equals(confirmPasswordField.getText())){
				String newPass=passwordField.getText();
				if(newPass.length()>0){
					changePassword(newPass);
				
				}
				else{
					alert("No password entered.");
				}
			}
			else{
				alert("Passwords do not match.");
			}
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
		else if(reactivateBtn == event.getSource()){
			activate();
		}
		//When pressed it hides all the other components from other scenes
		//Then is displays the home screen
		else if(returnBtn == event.getSource()){
			clearScreen();
			
			withdrawBtn.setVisible(true);
			tranBtn.setVisible(true);
			balanceBtn.setVisible(true);
			chngPassBtn.setVisible(true);
			exitBtn.setVisible(true);
			depositBtn.setVisible(true);
		}
		
	}
	//The following methods activate when the buttons are press
	//Allows the display of the relevant scenes
	//TODO
	/*
	 * Passwords cannot contain spaces.
	 * */
	private JTextField passwordField,confirmPasswordField;
	private JButton changePasswordButton;
	
	private void changePassword() {//You can look at writeAccnt() and Main.login() to see how to do this.
		clearScreen();
		
		returnBtn = new JButton("\u2190 Return");
		returnBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		returnBtn.setBounds(10, 11, 134, 23);
		contentPane.add(returnBtn);
		returnBtn.addActionListener(this);
		
		inbeddedLabel = new JLabel("Enter Password Again:");
		inbeddedLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		inbeddedLabel.setBounds(10, 187, 216, 20);
		contentPane.add(inbeddedLabel);
		
		inbeddedLabel2 = new JLabel("Enter New Password:");
		inbeddedLabel2.setFont(new Font("Tahoma", Font.BOLD, 18));
		inbeddedLabel2.setBounds(10, 160, 216, 20);
		contentPane.add(inbeddedLabel2);
		
		confirmPasswordField = new JTextField();
		confirmPasswordField.setBounds(219, 190, 134, 20);
		contentPane.add(confirmPasswordField);
		confirmPasswordField.setColumns(10);
		
		changePasswordButton = new JButton("<html><center>Confirm<br>Change</center></html>");
		changePasswordButton.addActionListener(this);
		changePasswordButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		changePasswordButton.setBounds(386, 218, 121, 53);
		contentPane.add(changePasswordButton);
		
		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(219, 163, 134, 20);
		contentPane.add(passwordField);
		
		
	}
	
	
	/*
	 * Check to make sure they can withdraw that amount. Compare to $Balance
	 * Withdraw amount from balance. Open file and write change to file.
	 */
	private JButton withdrawButton, returnBtn;
	private JTextField withdrawField;
	private JLabel inbeddedLabel;
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
	//END OF DISPLAY METHODS
	//TODO
	
	/*
	 * Used to reactivate the user's account.
	 * Re-enables the accounts functionality.
	 * */
	private void activate(){
		Active = true;
		writeAccnt();
		withdrawBtn.setEnabled(true);
		tranBtn.setEnabled(true);
		depositBtn.setEnabled(true);
		balanceBtn.setEnabled(true);
		reactivateBtn.setVisible(false);
		alert("Account Reactivated");
	}
	
	/*
	 * Changes global variable Balance 
	 * Does not allow for insufficient funds to be withdrawn
	 * Writes new balance to file.
	 * */
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

	/*
	 * Uses the Alert class to tell user of any pertinent information
	 * This opens a new screen that needs to be acknowledged
	 * */
	private void alert(String text) {
		Alert a = new Alert(text);
		a.setVisible(true);
		
	}
	
	/*
	 * Adds amnt to the current balance
	 * then writes the balance to the file
	 * */
	private void deposit(Double amnt){
		Balance +=amnt ;
		alert("Amount Deposited. New Balance is $"+Balance+".");
		writeAccnt();
	}
	
	
	
	
	
	/*
	 * Writes new password to file and checks if it fits criteria
	 * Does not allow spaces to be used
	 * Allows for any other character to be used.
	 * Alerts user to any changes or errors that occur
	 * */
	private void changePassword(String newPass){
		if(newPass.split(" ").length==1){
			try{
				Scanner read = new Scanner(new File("LoginInformation.txt"));
				String output="";
				while(read.hasNext()){
					String in = read.nextLine();
					if(Long.parseLong(in.split(" ")[0])==Account_Number){
						output+=Account_Number+" "+newPass+"\n";
					}
					else{
						output+=in+"\n";
					}
				}
				read.close();
				PrintWriter write = new PrintWriter(new File("LoginInformation.txt"));
				write.write(output);
				write.close();
				alert("Password has been updated.");
				
			}
			catch(IOException e){
				System.out.println("File Not Found: LoginInformation.txt");
				e.printStackTrace();
			}
		}
		else{
			alert("Password not changed: Cannot contain spaces");
		}
	}
	
	/*
	 * This is used to hide all of the buttons on screen when changing scenes.
	 * e.g. going from homescreen to balance screen and going back to home screen
	 * */
	private void clearScreen(){//Used to hide home screen buttons
		for(Component c:contentPane.getComponents()){
			c.setVisible(false);
		}
	}
	
	
	/*
	 * Displays the user's current balance.
	 * Uses string format to allow for when the user has more or less that 2 decimal places.
	 * */
	private void checkBalance() {
		clearScreen();
		returnBtn = new JButton("\u2190 Return");
		returnBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		returnBtn.setBounds(10, 11, 134, 23);
		contentPane.add(returnBtn);
		returnBtn.addActionListener(this);
		returnBtn.setVisible(true);
		inbeddedLabel = new JLabel("Your Balance is: $"+((String.format("%.2f",Balance))));
		inbeddedLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		inbeddedLabel.setBounds(10, 160, 550, 20);
		contentPane.add(inbeddedLabel);
		writeAccnt();
		
	}
	
	
	//Used to exit the application.
	private void exit() {
		System.exit(0);
	}
	
	/*
	 * Transfers funds to another account.
	 * Will only transfer if other account exists otherwise it fails.
	 * User must have sufficient funds.
	 * Cannot transfer to the users own account.
	 * Uses its own write method because writeAccnt() cannot handle writing data for other accounts.
	 * */
	private void transfer(long accntNumber, double amnt){
		if(Balance< amnt){
			alert("Insuffcient Funds: Transfer Cancelled.");
			return;
		}
		else if(accntNumber== Account_Number){
			alert("Transfer Failed: Cannot Transfer to own Account");
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
	
	/*
	 * Writes all current information, name, balance, and whether it is active or not.
	 * Does not write the password because that is in a different file
	 * */
	private void writeAccnt(){
		try {
			
			Scanner read =new Scanner(new File("AccountInformation.txt"));
			String text="";
			while(read.hasNext()){
				long accntNum = read.nextLong();
				read.nextLine();
				if(accntNum == Account_Number){
					text += Account_Number + "\n"+Last_Name+"\n"+First_Name+"\n"+Balance+"\n"+((Active)?("Active"):("Not Active"))+"\n";
					read.nextLine();//Used to skip over lines of data that are replaced
					read.nextLine();
					read.nextLine();
					read.nextLine();
				}
				else{//reads account data that is not changed.
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
	
	/*
	 * Used to check an account exists
	 * Used when you want this account to interact with other account holders
	 * Does not check if that user is Active.
	 * */
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