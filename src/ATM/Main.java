package ATM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField accntNumField;
	private JTextField passwordField;
	private JButton loginBtn;
	private JLabel errorLbl;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
			login();
		}
		
	}
	
	
	public void login(){
		
		
	}
	
	
	
}
