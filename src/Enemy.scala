import javax.swing._
import java.awt._
import scala.util.Random

/** Enemy class for jWars project.  All enemies will extend this class */
class Enemy(lyp: Int) extends Entity {
	
	var pointValue = 0
	var lowestYpos = lyp

	xPos = -1
	yPos = -1
	entityHeight = 50
	entityWidth = 50

	var run = Random.nextInt(9) + 1

	var rise = Random.nextInt(9) + 1
	
	override def paintComponent(g: Graphics) = {
		if(xPos == -1 && yPos == -1) {
			xPos = Random.nextInt(600) + 1
			yPos = Random.nextInt(299) + 1
		}

		g.setColor(Color.RED)
		g.fillRect(xPos,yPos,entityWidth,entityHeight)
	}

	def move(parentWidth: Int) = {
		// First, check to ensure that the ball isn't at an edge.
		if(xPos < (0-run) || xPos > (parentWidth - entityWidth))
			run = -run
		if(yPos < (0-rise) || yPos > (lowestYpos - entityHeight))
            		rise = -rise

		xPos = xPos + run
		yPos = yPos + rise
	}

}
