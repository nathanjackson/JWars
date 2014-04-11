import javax.swing._
import java.awt._
import java.util.Random

/** Tie bomber class for jWars project */
class TieBomber(lyp: Int) extends Enemy(lyp) {

	pointValue = 10

	private val imgIcon = new ImageIcon("tiebomber.gif")
	entityWidth = imgIcon.getIconWidth()
	entityHeight = imgIcon.getIconHeight()

	/** Paint the tie bomber on the screen
	 *  @param g graphics component to paint on */
	override def paintComponent(g: Graphics) = {
		if(xPos == -1 && yPos == -1) {
			var generator = new Random(System.nanoTime())
			xPos = generator.nextInt(600) + 1
			generator = new Random(System.nanoTime())
			yPos = generator.nextInt(299) + 1
		}

		g.drawImage(imgIcon.getImage(), xPos, yPos, null, null)
		updateLocations()
	}
}
