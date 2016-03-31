package ATM;


import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

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
		exitBtn.setBounds(399, 219, 125, 75);
		contentPane.add(exitBtn);
		
		exitBtn.addActionListener(this);
		depositBtn.addActionListener(this);
		withdrawBtn.addActionListener(this);
		balanceBtn.addActionListener(this);
		chngPassBtn.addActionListener(this);
		tranBtn.addActionListener(this);
		

		Debug();
	}
	
	public void Debug(){
		System.out.println("Account Number: "+Account_Number);
		System.out.println("Last Name: "+Last_Name);
		System.out.println("First Name: " + First_Name);
		System.out.println("Balance: " + Balance);
		System.out.println("Active: " + Active);
	}
	
	public void startUp(){//Loads account info file. Should only load relevant account information

		
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
	
	//TODO implement the methods for the buttons listed below
	@Override
	public void actionPerformed(ActionEvent event) {
		if(tranBtn== event.getSource()){
			System.out.println("Button!");
		}
		if(exitBtn== event.getSource()){
			System.out.println("Button!");
		}
		if(balanceBtn== event.getSource()){
			System.out.println("Button!");
		}
		if(withdrawBtn== event.getSource()){
			System.out.println("Button!");
		}
		if(chngPassBtn== event.getSource()){
			System.out.println("Button!");
		}
		if(depositBtn== event.getSource()){
			System.out.println("Button!");
		}
		
	}
}
