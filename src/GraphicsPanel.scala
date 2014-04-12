/** @author Nathan Jackson
 *  CS241 Project 3 GraphicsPanel class */ import javax.swing._
import java.awt._
import java.awt.event._
import java.util.Random

import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks._

/** GraphicsPanel class for JWars.  Contains the enemies and the player's ship.  Handles resize events. */
class GraphicsPanel extends JPanel {

	var xwing = new Friendly()
	var numberOfEnemies = 0
	private val entityList = ArrayBuffer[Entity]()

	addComponentListener(new GraphicsPanelEvent())

	/** Paint the Component in its parent container
	 * @param g graphics object to paint on */
	@Override
	override def paintComponent(g: Graphics) = {
		super.paintComponent(g);
		
		animate();

		if(xwing.xPos == -1)
			xwing.xPos_$eq((this.getWidth() / 2) - xwing.entityWidth / 2);
		if(xwing.yPos == -1)
			xwing.yPos_$eq(this.getHeight() - xwing.entityHeight);
	
		g.setColor(Color.BLACK);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
	
		xwing.paintComponent(g);
		entityList.foreach(e => e.paintComponent(g))
	}

	/** Nested Event Handler, allows to move the xwing if the panel is resized. */
	class GraphicsPanelEvent extends ComponentListener {
		override def componentHidden(e: ComponentEvent) = {
			// No need to do anything
		}

		override def componentMoved(e: ComponentEvent) = {
			// No need to do anything
		}

		override def componentResized(e: ComponentEvent) = {
			xwing.yPos = -1
			entityList.foreach( et => {
				if(et.isInstanceOf[Enemy]) {
					var enemy = et.asInstanceOf[Enemy]
					enemy.lowestYpos = getHeight() - 150
				}
			})
		}

		override def componentShown(e: ComponentEvent) = {
			// No need to do anything
		}
	}

	/** Move the onscreen ship to the left */
	def moveShipLeft() = if(!(xwing.xPos <= 0)) xwing.xPos = (xwing.xPos - 3);

	/** Move the onscreen ship to the right */
	def moveShipRight() = if(!(xwing.xPos >= this.getWidth() - xwing.entityWidth)) xwing.xPos = xwing.xPos + 3

	/** Method called to animate the onscreen objects */
	def animate() = entityList.foreach(e => {
		entityList.foreach(e2 => breakable {
			if(detectCollision(e, e2)) {
				break
			}
		})

		if(e.isInstanceOf[TieFighter] || e.isInstanceOf[TieBomber]) {
			var en = e.asInstanceOf[Enemy]
			en.move(this.getWidth())
		}
		else if(e.isInstanceOf[Laser]) {
			var l = e.asInstanceOf[Laser]
			l.move();
			if(l.yPos <= -1 && l.yPos >= -10) {
				entityList -= e
			}
		}
		else if(e.isInstanceOf[Explosion]) {
			var ex = e.asInstanceOf[Explosion]
			if(ex.entityWidth > ex.source.entityWidth) {
				entityList -= e
			}
		}
	})

	/** Add a laser to the panel
	 *  @param laser the new laser to add */
	def addLaser(laser: Laser) = {
		var numLasers = 0
		for(i <- 0 to entityList.size - 1) {
			if(entityList(i).isInstanceOf[Laser]) {
				numLasers += 1
			}
		}

		if(numLasers < 3)
			entityList += laser
	}

	/** Method that detects a collision between two entities
	 *  @param e1 first entity 
	 *  @param e2 second entity 
	 *  @return true if a collision has happened, false otherwise */
	def detectCollision(e1: Entity, e2: Entity): Boolean = {
		if(e1 == null || e2 == null)
			return false
		if(e1 == e2) {
			return false;
		}
		if(e1.center.distance(e1.bottomSide) + e2.center.distance(e2.bottomSide) > e1.center.distance(e2.center)) {
			processCollision(e1,e2);
			return true;
		}
		return false;
	}

	/** Process the collision between two given entities
	 *  @param e1 first entity
	 *  @param e2 second entity */
	def processCollision(e1: Entity, e2: Entity) = {
		if((e1.isInstanceOf[Enemy] && e2.isInstanceOf[Laser]) || (e1.isInstanceOf[Laser] && e2.isInstanceOf[Enemy])) {
			if(e1.isInstanceOf[Enemy]) {
				var en = e1.asInstanceOf[Enemy]
				entityList += new Explosion(en)
                                xwing.incrementScore(en.pointValue);
                        }
                        else if(e2.isInstanceOf[Enemy]) {
				var en = e2.asInstanceOf[Enemy]
				entityList += new Explosion(en)
                                xwing.incrementScore(en.pointValue);
                        }
	
			entityList -= e1
			entityList -= e2
		
			numberOfEnemies -= 1
		}
	}

	/** Add an enemy to the panel
	 *  @param en the new enemy */
	def addEnemy(en: Enemy) = {
		entityList += en
		numberOfEnemies += 1;
	}
}
