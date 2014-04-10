import javax.swing._
import java.awt._
import java.util.Random

/** Enemy class for jWars project.  All enemies will extend this class */
class Enemy(lyp: Int) extends Entity() {
	
	var pointValue = 0
	var lowestYpos = lyp

	setXpos(-1)
	setYpos(-1)
	setEntityWidth(50)
	setEntityHeight(50)

	var run = {
		val generator = new Random(System.nanoTime())
		generator.nextInt(9) + 1
	}

	var rise = {
		val generator = new Random(System.nanoTime())
		generator.nextInt(9) + 1
	}
	
	override def paintComponent(g: Graphics) = {
		if(getXpos() == -1 && getYpos() == -1) {
			var generator = new Random(System.nanoTime())
			setXpos(generator.nextInt(600) + 1)
			generator = new Random(System.nanoTime())
			setYpos(generator.nextInt(299) + 1)
		}

		g.setColor(Color.RED)
		g.fillRect(getXpos(),getYpos(),getEntityWidth(),getEntityHeight())
	}

	def move(parentWidth: Int) = {
		// First, check to ensure that the ball isn't at an edge.
		if(getXpos() < (0-run) || getXpos() > (parentWidth - getEntityWidth()))
			run = -run
		if(getYpos() < (0-rise) || getYpos() > (lowestYpos - getEntityHeight()))
            		rise = -rise

		setXpos(getXpos() + run)
		setYpos(getYpos() + rise)
	}

}
