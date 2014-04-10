import javax.swing._
import java.awt._
import java.util.Random

/** Tie bomber class for jWars project */
class TieBomber(lyp: Int) extends Enemy(lyp) {

	setPointValue(10)

	val imgIcon = new ImageIcon("tiebomber.gif")
	setEntityWidth(imgIcon.getIconWidth())
	setEntityHeight(imgIcon.getIconHeight())

	/** Paint the tie bomber on the screen
	 *  @param g graphics component to paint on */
	override def paintComponent(g: Graphics) = {
		if(getXpos() == -1 && getYpos() == -1) {
			var generator = new Random(System.nanoTime())
			setXpos(generator.nextInt(600) + 1)
			generator = new Random(System.nanoTime())
			setYpos(generator.nextInt(299) + 1)
		}

		g.drawImage(imgIcon.getImage(), getXpos(), getYpos(), null, null)
	}
}
