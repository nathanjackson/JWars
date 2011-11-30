import javax.swing.*;
import java.awt.*;

/** Entity class for jWars project.  All onscreen objects extend this.  If placed into the graphics pane, a default white square represents the entity */
public class Entity extends JPanel {
	
	private int entityHeight;
	private int entityWidth;
	private int xPos;
	private int yPos;

	private Point rightSide,leftSide,topSide,bottomSide,center;
	
	/** Default no args constructor creates an entity with default values */
	public Entity() {
		super();
		rightSide = new Point();
		leftSide = new Point();
		topSide = new Point();
		bottomSide = new Point();
		center = new Point();

		setEntityHeight(10);
		setEntityWidth(10);
		setXpos(0);
		setYpos(0);
	}

	/** Default paintComponent will paint a square on the screen 
	 *  @param g Graphics Component to paint on */
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);		
		g.fillRect(getXpos(),getYpos(),getEntityWidth(),getEntityHeight());
	}

	/** Update all points of the entity */
	private void updateLocations() {
		rightSide.setLocation(getXpos() + getEntityWidth(), getYpos() + (getEntityHeight() / 2));
		leftSide.setLocation(getXpos(),getYpos() + (getEntityHeight() / 2));
		topSide.setLocation(getXpos() + (getEntityWidth() / 2), getYpos());
		bottomSide.setLocation(getXpos() + (getEntityWidth() / 2), getYpos() + getEntityHeight());
		center.setLocation(getXpos() + (getEntityWidth() / 2), getYpos() + (getEntityHeight() / 2));
	}

	/** Set the X position of the entity 
         *  @param xPos x position of the craft */
	public void setXpos(int xPos) {
		this.xPos = xPos;
		updateLocations();
	}

	/** Set the Y position of the entity 
         *  @param yPos y position of the craft */
	public void setYpos(int yPos) {
		this.yPos = yPos;
		updateLocations();
	}

	/** Get the X position of the entity 
         *  @return the x position of the entity */
	public int getXpos() {
		return xPos;
	}

	/** Get the Y position of the entity
	 *  @return y position of the entity */
	public int getYpos() {
		return yPos;
	}

	/** Set the entity width
	 *  @param entityWidth width of the entity */
	public void setEntityWidth(int entityWidth) {
		this.entityWidth = entityWidth;
	}

	/** Set the entity height
	 *  @param entityHeight height of the entity */
	public void setEntityHeight(int entityHeight) {
		this.entityHeight = entityHeight;
	}

	/** Get the entity Height
	 *  @return height of the entity */
	public int getEntityHeight() {
		return entityHeight;
	}

	/** Get the entity width
	 *  @return the width of the entity */
	public int getEntityWidth() {
		return entityWidth;
	}

	/** Get the left most point of the entity
	 *  @return get the left most point of the entity */
	public Point getLeftMostPoint() {
		return leftSide;
	}

	/** Get the right most point of the entity
	 *  @return the right most point */
	public Point getRightMostPoint() {
		return rightSide;
	}

	/** Get the top most point of the entity
	 *  @return the top most point */
	public Point getTopMostPoint() {
		return topSide;
	}

	/** Get the bottom most point of the entity
	 *  @return the bottom most point */
	public Point getBottomMostPoint() {
		return bottomSide;
	}

	/** Get the center point of the entity
	 *  @return the center point */
	public Point getCenterPoint() {
		return center;
	}
}
