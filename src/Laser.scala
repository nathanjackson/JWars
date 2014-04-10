import javax.swing._
import java.awt._

/** Laser class for the jWars project */
class Laser(src: Friendly) extends Entity() {

	private val firingShip = src

	setXpos(-100);
	setYpos(-100);

	/** Paint the laser on the graphics component
	 *  @param g graphics object */
	override def paintComponent(g: Graphics) = {
		if(getXpos() == -100) {
			setXpos(firingShip.getXpos() + firingShip.getEntityWidth() / 2);
		}
		if(getYpos() == -100) {
			setYpos(firingShip.getYpos());
		}

		g.setColor(Color.GREEN);
		g.fillRect(getXpos(), getYpos(), 2, 20);
	}

	/** Move the laser up the screen */
	def move() = {
		if(getYpos() != -100)
			setYpos(getYpos() - 5);
	}

}
