import javax.swing._
import java.awt._
import java.awt.event._
import java.util.Random

/** Main Class for JWars.  Contains the main method and frame for the entire game */
object JWarsMain extends JFrame {

	implicit def toActionListener(f: ActionEvent => Unit) = new ActionListener {
		def actionPerformed(e: ActionEvent) = f(e)
	}
		
	// Static final variables that define basic window properties	
	val DEFAULT_SIZE = new Dimension(1024, 576)
	val DEFAULT_WINDOW_TITLE = "jWars"
	val waitToCreateEnemy = 50
	var waitStatus = 0

	// Instance variables
	val controlPane = new ControlPanel()
	val graphicsPane = new GraphicsPanel()
	val animationTimer: Timer = new Timer(25, { e: ActionEvent =>
		if(Integer.parseInt(controlPane.scoreText.getText()) >= 300) {
			animationTimer.stop();
			JOptionPane.showMessageDialog(null,"Score limit reached (300), congratulations!","Winner!",JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);		
		}
		graphicsPane.repaint();
		controlPane.scoreText.setText(Integer.toString(graphicsPane.xwing.score));
		if(graphicsPane.numberOfEnemies < 3) {
			if(waitStatus < waitToCreateEnemy)
				waitStatus += 1
			else {
				var generator = new Random(System.nanoTime());
				var randint = generator.nextInt(100);
				if(randint < 75)
					graphicsPane.addEnemy(new TieFighter(graphicsPane.getHeight() - 150));
				else
					graphicsPane.addEnemy(new TieBomber(graphicsPane.getHeight() - 150));	
					waitStatus = 0;	
			}
		}
	})

	controlPane.fireButton.addActionListener { e: ActionEvent =>
		graphicsPane.addLaser(new Laser(graphicsPane.xwing))
		controlPane.setFocusable(true)
	}

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle(DEFAULT_WINDOW_TITLE);
	setSize(DEFAULT_SIZE);
	setResizable(false);
	setLayout(new BorderLayout());
	controlPane.addKeyListener(new KeyAdapter {

		val moveLeftTimer = new Timer(1, { e: ActionEvent =>
			graphicsPane.moveShipLeft()
		})
		
		val moveRightTimer = new Timer(1, { e: ActionEvent =>
			graphicsPane.moveShipRight();
		})

		override def keyPressed(e: KeyEvent) = {
			if(e.getKeyCode() == KeyEvent.VK_LEFT)
				moveLeftTimer.start()
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
				moveRightTimer.start()
			else if(e.getKeyCode() == KeyEvent.VK_SPACE)
				graphicsPane.addLaser(new Laser(graphicsPane.xwing))
		}

		override def keyReleased(e: KeyEvent) = {
			if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
				moveLeftTimer.stop();
				moveRightTimer.stop();
			}
		}
	})

	controlPane.setFocusable(true);
	controlPane.addFocusListener(new FocusAdapter {
		override def focusLost(e: FocusEvent) = {
			controlPane.requestFocus();
		}
	})

	add(graphicsPane,BorderLayout.CENTER);

	add(controlPane,BorderLayout.PAGE_END);	

	animationTimer.start();		

	/** Entry point for jWars game.
	 *  @param args CLI arguments */
	def main(args: Array[String]) {
		JOptionPane.showMessageDialog(null,"Move the ship left and right using the arrow keys.  Fire the laser using the spacebar or optionally, the fire button.  Can you reach the maximum score (300)?","Instructions",JOptionPane.INFORMATION_MESSAGE);
		setVisible(true)
	}
}
