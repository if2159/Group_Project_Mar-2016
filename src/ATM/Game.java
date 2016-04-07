package ATM;
//IGNORE ME I DON'T EXIST
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Game extends JFrame implements Runnable {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
					frame.setVisible(true);
					Thread t = new Thread(frame);
					 t.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	JLabel moveLbl;
	public Game() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 514, 339);
		contentPane.add(panel);
		panel.setLayout(null);
		
		moveLbl = new JLabel("New label");
		moveLbl.setBounds(378, 54, 79, 14);
		panel.add(moveLbl);
		
	}

	@Override
	public void run() {
		Thread t = new Thread(this);
		while(true){
			try {
				t.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			moveLbl.setLocation(moveLbl.getX()-2, moveLbl.getY());
			System.out.println("Boop");
			
		}
		
	}
}
