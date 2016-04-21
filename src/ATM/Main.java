package ATM;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField accntNumField;
	private JTextField passwordField;
	private JButton loginBtn;
	private JLabel errorLbl;
	private HashMap<Integer, String> accounts = new HashMap<Integer, String>();
	private static Main frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		startUp();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Account Number:");
		lblNewLabel.setBounds(90, 140, 159, 22);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel);
		
		accntNumField = new JTextField();
		accntNumField.setBounds(260, 144, 159, 20);
		contentPane.add(accntNumField);
		accntNumField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(155, 173, 94, 22);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblPassword);
		
		passwordField = new JTextField();
		passwordField.setBounds(260, 177, 159, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		loginBtn = new JButton("Login");
		loginBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		loginBtn.setBounds(200, 206, 125, 50);
		contentPane.add(loginBtn);
		loginBtn.addActionListener(this);
		
		errorLbl = new JLabel("");
		errorLbl.setForeground(Color.RED);
		errorLbl.setVerticalAlignment(SwingConstants.TOP);
		errorLbl.setHorizontalAlignment(SwingConstants.CENTER);
		errorLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		errorLbl.setBounds(50, 260, 440, 80);
		contentPane.add(errorLbl);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginBtn){
			if(login()){
				AccountScreen AS = new AccountScreen(Integer.parseInt(accntNumField.getText()));
				AS.setVisible(true);
				frame.setVisible(false);
			}
		}
		
	}
	
	public void startUp(){
		try{
			Scanner sc = new Scanner(new File("./LoginInformation.txt"));
			while(sc.hasNext()){
				String input = sc.nextLine();
				String in[]= input.split(" ");
				if(in.length == 2){
					int accntNum = Integer.parseInt(in[0]);
					String password = in[1];
					accounts.put(accntNum,password);
				}
				else{
					System.out.println("Invalid whitespace likely password contains whitespace Char.\n\t"
							+ input);
					
				}
			}
			sc.close();
		}
		catch(IOException e){
			System.out.println("File not Found: LoginInformation");
		}
		
		
	}
	
	public boolean login(){
		/*
		 * Check that password is correct for user.
		 * if success:
		 * 		return true
		 * 		open Account Screen
		 * If failure return false and write to errorLbl why.
		 * Error Messages:
		 * 		"Password and Account Number do not match"
		 * 		"You must enter password"
		 * 		"You must enter Account Number"
		 * */
		int accntNum;
		try{
			accntNum = Integer.parseInt(accntNumField.getText());
		}
		catch(NumberFormatException e){
			errorLbl.setText("Account Number is Incorrect.");
			return false;
		}
		if(accounts.containsKey(accntNum)){//Check if that account exists
			if(accounts.get(accntNum).equals(passwordField.getText())){//checks if password matches account number
				errorLbl.setText("");
				return true;
			}
			else{
				errorLbl.setText("Password and Account Number do not match.");
				return false;
			}
		}
		else{
			errorLbl.setText("Account Number does not exist"); //Consider changing to do not match. This is more readable.
			return false;
		}
	}
	
	
	
	
}
