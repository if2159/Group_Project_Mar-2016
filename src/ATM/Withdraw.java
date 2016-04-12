package ATM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//DO NOT IMPLEMENT. 
//For design only.
@SuppressWarnings("serial")
@Deprecated
public class Withdraw extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Withdraw frame = new Withdraw();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public Withdraw() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton returnBtn = new JButton("\u2190 Return");
		returnBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		returnBtn.setBounds(10, 11, 134, 23);
		contentPane.add(returnBtn);
		returnBtn.addActionListener(this);
		
		JLabel inbeddedLabel = new JLabel("Amount to Deposit: $");
		inbeddedLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		inbeddedLabel.setBounds(10, 187, 216, 20);
		contentPane.add(inbeddedLabel);
		
		JLabel inbeddedLabel2 = new JLabel("Transfer Fund to: ");
		inbeddedLabel2.setFont(new Font("Tahoma", Font.BOLD, 18));
		inbeddedLabel2.setBounds(10, 160, 216, 20);
		contentPane.add(inbeddedLabel2);
		
		textField = new JTextField();
		textField.setBounds(209, 187, 134, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		/*textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(209, 160, 134, 20);
		contentPane.add(textField_1);
		*/
		JButton transferButton = new JButton("<html><center>Transfer</center></html>");
		transferButton.addActionListener(this);
		transferButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		transferButton.setBounds(386, 218, 121, 53);
		contentPane.add(transferButton);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
