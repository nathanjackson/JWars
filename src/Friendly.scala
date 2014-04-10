import javax.swing._
import java.awt._
import java.awt.event._

/** Player class for JWars project.  Uses the picture of an X-Wing */
class Friendly extends Entity {
	private var imgIcon = new ImageIcon("xwing.gif")
	var score = 0	

	setEntityWidth(imgIcon.getIconWidth())
	setEntityHeight(imgIcon.getIconHeight())
	setXpos(-1)
	setYpos(-1)

	/** Paint the fighter on the screen. */
	@Override
	override def paintComponent(g: Graphics) =
		g.drawImage(imgIcon.getImage(),getXpos(),getYpos(),null,null)

	/** Increment the score by a given amount 
	 *  @param amt amount to increment */
	def incrementScore(amt: Int) = score += amt
}
