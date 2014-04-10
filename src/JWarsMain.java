import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/** Main Class for JWars.  Contains the main method and frame for the entire game */
public class JWarsMain extends JFrame {

	// Static final variables that define basic window properties	
	private final static Dimension DEFAULT_SIZE = new Dimension(1024,576);
	private final static String DEFAULT_WINDOW_TITLE = "jWars";
	private final static int waitToCreateEnemy = 50;

	// Instance variables
	private ControlPanel controlPane;
	private GraphicsPanel graphicsPane;
	private Timer animationTimer;

	/** No args constructor, most of the execution of the game occurs here */
	public JWarsMain() {
		super();

		controlPane = new ControlPanel();
		
		controlPane.getFireButton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				graphicsPane.addLaser(new Laser(graphicsPane.getXwing()));	
				controlPane.setFocusable(true);
			}
		});

		graphicsPane = new GraphicsPanel();
			
		animationTimer = new Timer(25, new ActionListener() {
			
			private int waitStatus = 0;	
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(controlPane.getScoreText().getText()) >= 300) {
					animationTimer.stop();
					JOptionPane.showMessageDialog(null,"Score limit reached (300), congratulations!","Winner!",JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);		
				}
				graphicsPane.repaint();
				controlPane.getScoreText().setText(Integer.toString(graphicsPane.getXwing().score()));
				if(graphicsPane.getNumberOfEnemies() < 3) {
					if(waitStatus < waitToCreateEnemy) {
						waitStatus++;
					}
					else {
						Random generator = new Random(System.nanoTime());
						int randint = generator.nextInt(100);
						if(randint < 75) {
							graphicsPane.addEnemy(new TieFighter(graphicsPane.getHeight() - 150));
						}
						else {
							graphicsPane.addEnemy(new TieBomber(graphicsPane.getHeight() - 150));	
						}
						waitStatus = 0;	
					}
				}
			}

		});

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(DEFAULT_WINDOW_TITLE);
		this.setSize(DEFAULT_SIZE);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		controlPane.addKeyListener(new KeyAdapter() {

			private Timer moveLeftTimer = new Timer(1, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					graphicsPane.moveShipLeft();	
				}
			});
			
			private Timer moveRightTimer = new Timer(1, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					graphicsPane.moveShipRight();
				}
			});

			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					moveLeftTimer.start();
				}
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					moveRightTimer.start();
				}
				else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					graphicsPane.addLaser(new Laser(graphicsPane.getXwing()));		
				}
			}

			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
					moveLeftTimer.stop();
					moveRightTimer.stop();
				}
			}
		});

		controlPane.setFocusable(true);
		controlPane.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				controlPane.requestFocus();
			}
		});

		this.add(graphicsPane,BorderLayout.CENTER);

		this.add(controlPane,BorderLayout.PAGE_END);	
		this.setVisible(true);
			
		animationTimer.start();		
	}

	/** Entry point for jWars game.
	 *  @param args CLI arguments */
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null,"Move the ship left and right using the arrow keys.  Fire the laser using the spacebar or optionally, the fire button.  Can you reach the maximum score (300)?","Instructions",JOptionPane.INFORMATION_MESSAGE);
		JWarsMain frameMain = new JWarsMain();
	}
}
