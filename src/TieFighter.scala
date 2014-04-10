import javax.swing._
import java.awt._
import java.util.Random

/** Tie Fighter class for jWars project */
class TieFighter(lyp: Int) extends Enemy(lyp) {

	pointValue = 5
	private val imgIcon = new ImageIcon("tiefighter.gif")
	setEntityWidth(imgIcon.getIconWidth())
	setEntityHeight(imgIcon.getIconHeight())

	/** Paint the tie fighter on the panel
	 *  @param g graphics object to paint on */
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
