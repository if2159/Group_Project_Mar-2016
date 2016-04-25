package finalproject;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Alert extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton okayBtn;
	
	/**
	 * Create the frame.
	 */
	public Alert(String text) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(Color.RED);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("<html><center>"+text+"</center></html>");
		label.setBounds(5, 5, 379, 106);
		contentPane.add(label);
		
		okayBtn = new JButton("Okay.");
		okayBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		okayBtn.setBounds(150, 88, 86, 23);
		contentPane.add(okayBtn);
		okayBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okayBtn){
			this.dispose();//closes the Alert screen
		}
		
	}
}
