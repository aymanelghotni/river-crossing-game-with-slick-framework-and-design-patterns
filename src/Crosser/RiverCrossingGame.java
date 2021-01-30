package Crosser;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Label;

public class RiverCrossingGame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RiverCrossingGame window = new RiverCrossingGame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RiverCrossingGame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 651, 409);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(0, 0, 637, 372);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		Label label = new Label("New label");
		label.setBounds(0, 0, 637, 372);
		panel.add(label);
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("C:\\Users\\Ayman\\Desktop\\Java workspace\\River Crossing Game\\data"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));

		} catch (IOException e) {
			e.printStackTrace();
		}
	
	
	}
}
