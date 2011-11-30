import javax.swing.*;
import java.awt.*;
import java.util.Random;

/** Enemy class for jWars project.  All enemies will extend this class */
public class Enemy extends Entity {
	
	private int pointValue;
	private int lowestYpos;
	private int rise,run;

	/** Standard constructor, takes the lowest possible Y position of the enemy 
	 *  @param lowestYpos lowest possible Y position of the enemy */	
	public Enemy(int lowestYpos) {
		super();
		setPointValue(0);
		setXpos(-1);
		setYpos(-1);
		setEntityWidth(50);
		setEntityHeight(50);

		Random generator = new Random(System.nanoTime());
		setRun(generator.nextInt(9) + 1);
		
		generator = new Random(System.nanoTime());
		setRise(generator.nextInt(9) + 1);
		setLowestYPos(lowestYpos);	
	}

	/** Paint the enemy within its parent container
	*   @param g graphics object to paint on */
	public void paintComponent(Graphics g) {
		if(getXpos() == -1 && getYpos() == -1) {
			Random generator = new Random(System.nanoTime());
			setXpos(generator.nextInt(600) + 1);
			generator = new Random(System.nanoTime());
			setYpos(generator.nextInt(299) + 1);	
		}

		g.setColor(Color.RED);
		g.fillRect(getXpos(),getYpos(),getEntityWidth(),getEntityHeight());	
	}

	/** Move the enemy within the confines of the parent width
         *  @param parentWidth the parent container width */
	public void move(int parentWidth) {
		// First, check to ensure that the ball isn't at an edge.
		if(this.getXpos() < (0-this.getRun()) || this.getXpos() > (parentWidth - this.getEntityWidth())) {
			this.setRun(-this.getRun());
		}
		if (this.getYpos() < (0-this.getRise()) || this.getYpos() > (getLowestYPos() - this.getEntityHeight())) { 
            		this.setRise(-this.getRise()); 
        	}
		this.setXpos(getXpos() + run);
		this.setYpos(getYpos() + rise);
	}

	/** Set the point value of the enemy
	 *  @param pointValue point value of the enemy */	
	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}

	/** Get the point value of the enemy
	 *  @return the point value of the enemy */
	public int getPointValue() {
		return pointValue;
	}

	/** set the lowest possible y position of the enemy
	 *  @param lowestYpos lowest possible y position of the enemy */
	public void setLowestYPos(int lowestYpos) {
		this.lowestYpos = lowestYpos;
	}

	/** Get the lowest possible y position of the enemy
	 *  @return lowest possible y position */
	public int getLowestYPos() {
		return lowestYpos;
	}

	/** Set the rise of the enemy
	 *  @param rise specified rise */
	public void setRise(int rise) {
		this.rise = rise;
	}

	/** Get the rise of the enemy
	 *  @return Enemy rise */
	public int getRise() {
		return rise;
	}

	/** Get the run of the enemy 
	 *  @param run specified run */
	public void setRun(int run) {
		this.run = run;
	}

	/** Get the run of the enemy
	 *  @return run of the enemy */
	public int getRun() {
		return run;
	}

}
