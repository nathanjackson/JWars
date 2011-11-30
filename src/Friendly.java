import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** Player class for JWars project.  Uses the picture of an X-Wing */
public class Friendly extends Entity {
	private ImageIcon imgIcon;
	private int score;	

	/** No args constructor, initializes the image icon associated with the x-wing. */
	public Friendly() {
		super();
		imgIcon = new ImageIcon("xwing.gif");
		setEntityWidth(imgIcon.getIconWidth());
		setEntityHeight(imgIcon.getIconHeight());
		setXpos(-1);
		setYpos(-1);
		setScore(0);
	}

	/** Paint the fighter on the screen. */
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(imgIcon.getImage(),getXpos(),getYpos(),null,null);
	}

	/** Set the score associated with the X-Wing (current player's score 
	 *  @param score score of the player */
	public void setScore(int score) {
		this.score = score;
	}

	/** Get the score associated with the X-Wing
	 *  @return the score */
	public int getScore() {
		return score;
	}

	/** Increment the score by a given amount 
	 *  @param amt amount to increment */
	public void incrementScore(int amt) {
		score += amt;
	}
}
