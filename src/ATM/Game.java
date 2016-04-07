package ATM;
//IGNORE ME I DON'T EXIST
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Random;

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
	JPanel panel;
	public Game() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 514, 339);
		contentPane.add(panel);
		panel.setLayout(null);
		
		moveLbl = new JLabel("New label");
		moveLbl.setBounds(378, 54, 79, 14);
		panel.add(moveLbl);
		
	}
	/*
	 * Minimum spacing >40px 
	 * */
	public JLabel[] newSpace(){
		int spacing = (new Random().nextInt(30))+40;
		JLabel pipe[]= {new JLabel("WallWallWallWall"),new JLabel("WallWallWallWall")};
		pipe[0].setBounds(200-spacing/2,300, 200-spacing/2, 30);
		pipe[1].setBounds(200-spacing/2,300, 200-spacing/2, 30);
		return pipe;
	}
	
	@Override
	public void run() {
		Thread t = new Thread(this);
		JLabel pipe[] = newSpace();
		pipe[0].setVisible(true);
		panel.add(pipe[0]);
		pipe[1].setVisible(true);
		panel.add(pipe[1]);
		while(true){
			try {
				t.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			

			
		}
		
	}
	
	public void update(){
		moveLbl.setLocation(moveLbl.getX()-2, moveLbl.getY());
		
	}
}
