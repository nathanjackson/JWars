import javax.swing._
import java.awt._
import scala.util.Random

/** Tie Fighter class for jWars project */
class TieFighter(lyp: Int) extends Enemy(lyp) {

	pointValue = 5
	private val imgIcon = new ImageIcon("tiefighter.gif")
	entityWidth = imgIcon.getIconWidth()
	entityHeight = imgIcon.getIconHeight()

	/** Paint the tie fighter on the panel
	 *  @param g graphics object to paint on */
	override def paintComponent(g: Graphics) = {
		if(xPos == -1 && yPos == -1) {
			xPos = Random.nextInt(600) + 1
			yPos = Random.nextInt(299) + 1
		}

		g.drawImage(imgIcon.getImage(), xPos, yPos, null, null)
		updateLocations()
	}
}
