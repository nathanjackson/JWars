import javax.swing._
import java.awt._

/** Explosion class for jWars */
class Explosion(src: Entity) extends Entity {
	
	var source = src
	var currentColor = Color.ORANGE

	entityWidth = 1
	xPos = src.xPos
	yPos = src.yPos
	
	/** Paint the explosion
	 *  @param g graphics object to paint on */
	override def paintComponent(g: Graphics) = {
		g.setColor(currentColor);
		if(entityWidth < source.entityWidth) {
			g.fillOval(xPos,yPos,entityWidth,entityHeight)
			entityWidth = entityWidth + 2
			entityHeight = entityHeight + 2
			if(currentColor.equals(Color.ORANGE)) {
				currentColor = Color.RED
			}
			else {
				currentColor = Color.ORANGE
			}
		}	
	}

}
