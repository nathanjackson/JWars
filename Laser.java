import javax.swing.*;
import java.awt.*;

/** Laser class for the jWars project */
public class Laser extends Entity {

	private Friendly firingShip;

	/** Standard constructor takes in a friendly object as a source of the firing 
	 *  @param firingShip ship that fired the laser */
	public Laser(Friendly firingShip) {
		super();
		setFiringShip(firingShip);
		setXpos(-100);
		setYpos(-100);
	}

	/** Set the source of the laser fire
	 *  @param firingShip ship that fired the laser */
	public void setFiringShip(Friendly firingShip) {
		this.firingShip = firingShip;
	}

	/** Paint the laser on the graphics component
	 *  @param g graphics object */
	public void paintComponent(Graphics g) {
		if(getXpos() == -100) {
			setXpos(firingShip.getXpos() + firingShip.getEntityWidth() / 2);
		}
		if(getYpos() == -100) {
			setYpos(firingShip.getYpos());
		}

		g.setColor(Color.GREEN);
		g.fillRect(getXpos(),getYpos(),2,20);
	}

	/** Move the laser up the screen */
	public void move() {
		if(!(getYpos() == -100))
			setYpos(getYpos() - 5);
	}

}
