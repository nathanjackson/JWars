/** @author Nathan Jackson
 *  CS241 Project 3 GraphicsPanel class */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;

/** GraphicsPanel class for JWars.  Contains the enemies and the player's ship.  Handles resize events. */
public class GraphicsPanel extends JPanel {

	private Friendly xwing;
	private int numberOfEnemies;

	private ArrayList<Entity> entityList;
	/** No args constructor, adds component listener and makes a call to the super constructor. */
	public GraphicsPanel() {
		super();
		xwing = new Friendly();
		entityList = new ArrayList<Entity>();
		numberOfEnemies = 0;
		this.addComponentListener(new GraphicsPanelEvent());
	}

	/** Paint the Component in its parent container
	 * @param g graphics object to paint on */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.animate();

		if(xwing.getXpos() == -1) {
			xwing.setXpos((this.getWidth() / 2) - xwing.getEntityWidth() / 2);
		}
		if(xwing.getYpos() == -1) {
			xwing.setYpos(this.getHeight() - xwing.getEntityHeight());
		}
	
		g.setColor(Color.BLACK);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
	
		xwing.paintComponent(g);
       		for(Entity e : entityList) {
			e.paintComponent(g);
		}
	}

	/** Nested Event Handler, allows to move the xwing if the panel is resized. */
	private class GraphicsPanelEvent implements ComponentListener {
		public void componentHidden(ComponentEvent e) {
			// No need to do anything
		}

		public void componentMoved(ComponentEvent e) {
			// No need to do anything
		}

		public void componentResized(ComponentEvent e) {
			xwing.setYpos(-1);
			for(Entity et : entityList) {
				if(et instanceof Enemy) {
					Enemy en = (Enemy) et;
					en.lowestYpos_$eq(getHeight() - 150);
				}
			}
		}

		public void componentShown(ComponentEvent e) {
			// No need to do anything
		}
	}

	/** Move the onscreen ship to the left */
	public void moveShipLeft() {
		if(!(xwing.getXpos() <=0))
			xwing.setXpos(xwing.getXpos() - 7);
	}

	/** Move the onscreen ship to the right */
	public void moveShipRight() {
		if(!(xwing.getXpos() >= this.getWidth() - xwing.getEntityWidth()))
			xwing.setXpos(xwing.getXpos() + 7);
	}

	/** Method called to animate the onscreen objects */
	public void animate() {

		for(int i=0;i<entityList.size();i++) {
			Entity e = entityList.get(i);
					
			for(int j=0;j<entityList.size();j++) {
				Entity e2 = entityList.get(j);

				if(detectCollision(e,e2))
					break;
			}
	
			if(e instanceof TieFighter || e instanceof TieBomber) {
				Enemy en = (Enemy) e;
				en.move(this.getWidth());	
			}
			else if(e instanceof Laser) {
				Laser l = (Laser) e;
				l.move();
				if(l.getYpos() <= -1 && l.getYpos() >= -10) {
					entityList.remove(e);
				}
			}

			else if(e instanceof Explosion) {
				Explosion ex = (Explosion) e;
				if(ex.getEntityWidth() > ex.source().getEntityWidth()) {
					entityList.remove(e);
				}
			}
		}
	}

	/** Get the friendly object controlled by the player */
	public Friendly getXwing() {
		return xwing;
	}

	/** Add a laser to the panel
	 *  @param laser the new laser to add */
	public void addLaser(Laser laser) {
		int numLasers = 0;
		for(int i=0;i<entityList.size();i++) {
			if(entityList.get(i) instanceof Laser) {
				numLasers++;
			}
		}

		if(numLasers < 3) {
			entityList.add(laser);
		}
	}

	/** Method that detects a collision between two entities
	 *  @param e1 first entity 
	 *  @param e2 second entity 
	 *  @return true if a collision has happened, false otherwise */
	public boolean detectCollision(Entity e1, Entity e2) {
		if(e1 == e2) {
			return false;
		}
		if(e1.getCenterPoint().distance(e1.getBottomMostPoint()) + e2.getCenterPoint().distance(e2.getBottomMostPoint()) > e1.getCenterPoint().distance(e2.getCenterPoint())) {
			processCollision(e1,e2);
			return true;
		}
		return false;
	}

	/** Process the collision between two given entities
	 *  @param e1 first entity
	 *  @param e2 second entity */
	public void processCollision(Entity e1, Entity e2) {
		if((e1 instanceof Enemy && e2 instanceof Laser) || (e1 instanceof Laser && e2 instanceof Enemy)) {
			if(e1 instanceof Enemy) {
                                Enemy en = (Enemy) e1;
				entityList.add(new Explosion(en));
                                getXwing().incrementScore(en.pointValue());
                        }
                        else if(e2 instanceof Enemy) {
                                Enemy en = (Enemy) e2;
				entityList.add(new Explosion(en));
                                getXwing().incrementScore(en.pointValue());
                        }
	

			entityList.remove(e1);
			entityList.remove(e2);
		
			numberOfEnemies--;		
		}
	}

	/** Add an enemy to the panel
	 *  @param en the new enemy */
	public void addEnemy(Enemy en) {
		entityList.add(en);	
		numberOfEnemies++;
	}

	/** Get the total number of enemies on the screen
	 *  @return the number of enemies */
	public int getNumberOfEnemies() {
		return numberOfEnemies;
	}
}
