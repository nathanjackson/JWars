import javax.swing._
import java.awt._

/** Laser class for the jWars project */
class Laser(src: Friendly) extends Entity {

	private val firingShip = src

	xPos = -100
	yPos = -100

	/** Paint the laser on the graphics component
	 *  @param g graphics object */
	override def paintComponent(g: Graphics) = {
		if(xPos == -100) {
			xPos = firingShip.xPos + firingShip.entityWidth / 2
		}
		if(yPos == -100) {
			yPos = firingShip.yPos
		}

		updateLocations()

		g.setColor(Color.GREEN)
		g.fillRect(xPos, yPos, 2, 20)
	}

	/** Move the laser up the screen */
	def move() = {
		if(yPos != -100)
			yPos = yPos - 5
	}

}
