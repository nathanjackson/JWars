import javax.swing.*;
import java.awt.*;
import java.util.Random;

/** Tie bomber class for jWars project */
public class TieBomber extends Enemy {
	private ImageIcon imgIcon;

	/** standard constructor takes the highest possible y position on the screen
	 *  @param lowestYpos lowest y position */
	public TieBomber(int lowestYpos) {
		super(lowestYpos);
		this.setPointValue(10);
		imgIcon = new ImageIcon("tiebomber.gif");
		setEntityWidth(imgIcon.getIconWidth());
		setEntityHeight(imgIcon.getIconHeight());
	}

	/** Paint the tie bomber on the screen
	 *  @param g graphics component to paint on */
	public void paintComponent(Graphics g) {
		if(getXpos() == -1 && getYpos() == -1) {
			Random generator = new Random(System.nanoTime());
			setXpos(generator.nextInt(600) + 1);
			generator = new Random(System.nanoTime());
			setYpos(generator.nextInt(299) + 1);	
		}

		g.drawImage(imgIcon.getImage(), getXpos(), getYpos(), null, null);
	}
}
