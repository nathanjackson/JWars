import javax.swing._
import java.awt._

/** Entity class for jWars project.  All onscreen objects extend this.  If placed into the graphics pane, a default white square represents the entity */
class Entity extends JPanel {
	
	var entityHeight = 10
	var entityWidth = 10
	var xPos = 0
	var yPos = 0
	var rightSide = new Point()
	var leftSide = new Point()
	var topSide = new Point()
	var bottomSide = new Point()
	var center = new Point()

	/** Default paintComponent will paint a square on the screen 
	 *  @param g Graphics Component to paint on */
	override def paintComponent(g: Graphics) = {
		g.setColor(Color.WHITE);		
		g.fillRect(xPos,yPos,entityWidth,entityHeight);
	}

	/** Update all points of the entity */
	protected def updateLocations() = {
		rightSide.setLocation(xPos + entityWidth, yPos + (entityHeight / 2));
		leftSide.setLocation(xPos, yPos + (entityHeight / 2));
		topSide.setLocation(xPos + (entityWidth / 2), yPos);
		bottomSide.setLocation(xPos + (entityWidth / 2), yPos + entityHeight);
		center.setLocation(xPos + (entityWidth / 2), yPos + (entityHeight) / 2);
	}
}
