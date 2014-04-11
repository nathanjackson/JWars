import javax.swing._
import java.awt._
import java.awt.event._

/** Player class for JWars project.  Uses the picture of an X-Wing */
class Friendly extends Entity {
	private var imgIcon = new ImageIcon("xwing.gif")
	var score = 0	

	entityWidth = imgIcon.getIconWidth()
	entityHeight = imgIcon.getIconHeight()
	xPos = -1
	yPos = -1

	/** Paint the fighter on the screen. */
	@Override
	override def paintComponent(g: Graphics) =
		g.drawImage(imgIcon.getImage(),xPos,yPos,null,null)

	/** Increment the score by a given amount 
	 *  @param amt amount to increment */
	def incrementScore(amt: Int) = score += amt
}
