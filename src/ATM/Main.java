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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField accntNumField;
	private JTextField passwordField;
	private JButton loginBtn;
	private JLabel errorLbl;
	private HashMap<Long, String> accounts = new HashMap<Long, String>();
	private static Main frame;
        private Connection conn;
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
			if(login()){//Creates new windows for the user to access account.
				AccountScreen AS = new AccountScreen(Long.parseLong(accntNumField.getText()),conn);
				AS.setVisible(true);
				frame.setVisible(false);
			}
		}
		
	}
	/*
	 * Reads in all accounts and passwords
	 * Adds them to HashMap accounts to use for logging in.
	 * */
	public void startUp(){
            conn= new DBConnection().getConnection();
            
            try{
                    Statement stmnt = conn.createStatement();
                        ResultSet rs = stmnt.executeQuery("SELECT accountNumber,password FROM accounts");
                        while(rs.next()){
                            Long accntNum = rs.getLong("accountNumber");
                            String password = rs.getString("password");
                            accounts.put(accntNum,password);
                            
                        }
                       
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            
            
	}
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
	public boolean login(){
            long accntNum;
            try{
                    accntNum = Long.parseLong(accntNumField.getText());
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
            
            /*
		int accntNum;
		try{
			accntNum = Integer.parseInt(accntNumField.getText());
		}
		catch(NumberFormatException e){
			errorLbl.setText("Account Number is Incorrect.");
			return false;
		}
                try{
                    Statement stmnt = conn.createStatement();
                    ResultSet rs = stmnt.executeQuery("SELECT COUNT(AccountNumber) AS total FROM accounts"
                            + "WHERE AccountNumber="+accntNum);
                    if(rs.getInt("total")>0){
                        ResultSet passrs = stmnt.executeQuery("SELECT password AS pass FROM accounts"
                            + "WHERE AccountNumber="+accntNum);
                        
                    }
                    else{
                        errorLbl.setText("Account does not exist.");
                    }
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
		*/
	}
	
	
	
	
}
