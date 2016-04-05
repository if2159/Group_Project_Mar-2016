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
public class Withdraw extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
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
		
		JLabel lblNewLabel = new JLabel("Amount to Withdraw: $");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 160, 216, 20);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(225, 163, 165, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnExit = new JButton("\u2190 Return");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExit.setBounds(10, 11, 134, 23);
		contentPane.add(btnExit);
		
		JButton btnwithdrawamount = new JButton("<html><center>Withdraw<br>Amount</center></html>");
		btnwithdrawamount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnwithdrawamount.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnwithdrawamount.setBounds(403, 195, 121, 53);
		contentPane.add(btnwithdrawamount);
	}
}
