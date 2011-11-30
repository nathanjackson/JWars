import javax.swing.*;
import java.awt.*;
import java.util.Random;

/** Tie Fighter class for jWars project */
public class TieFighter extends Enemy {
	private ImageIcon imgIcon;

	/** Standard constructor takes in the lowest Y pos
	 *  @param lowestYpos lowest possible y pos */
	public TieFighter(int lowestYpos) {
		super(lowestYpos);
		this.setPointValue(5);
		imgIcon = new ImageIcon("tiefighter.gif");
		setEntityWidth(imgIcon.getIconWidth());
		setEntityHeight(imgIcon.getIconHeight());
	}

	/** Paint the tie fighter on the panel
	 *  @param g graphics object to paint on */
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
