import javax.swing._
import java.awt._

/** Explosion class for jWars */
class Explosion(src: Entity) extends Entity() {
	
	var source = src
	var currentColor = Color.ORANGE

	setEntityWidth(1);
	setXpos(src.getXpos());
	setYpos(src.getYpos());	
	
	/** Paint the explosion
	 *  @param g graphics object to paint on */
	override def paintComponent(g: Graphics) = {
		g.setColor(currentColor);
		if(getEntityWidth() < source.getEntityWidth()) {
			g.fillOval(getXpos(),getYpos(),getEntityWidth(),getEntityWidth())
			setEntityWidth(getEntityWidth() + 2)
			if(currentColor.equals(Color.ORANGE)) {
				currentColor = Color.RED
			}
			else {
				currentColor = Color.ORANGE
			}
		}	
	}

}
