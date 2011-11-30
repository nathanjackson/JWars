import javax.swing.*;
import java.awt.*;

/** Explosion class for jWars */
public class Explosion extends Entity {
	
	private Entity source;
	private Color currentColor;

	/** Standard constructor creates a new explosion from the given source
	 *  @param src source of the explosion */
	public Explosion(Entity src) {
		super();	
		setSource(src);
		setEntityWidth(1);
		setXpos(src.getXpos());
		setYpos(src.getYpos());	
		currentColor = Color.ORANGE;
	}
	
	/** Paint the explosion
	 *  @param g graphics object to paint on */
	public void paintComponent(Graphics g) {
		g.setColor(currentColor);
		if(getEntityWidth() < source.getEntityWidth()) {
			g.fillOval(getXpos(),getYpos(),getEntityWidth(),getEntityWidth());
			setEntityWidth(getEntityWidth() + 2);
			if(currentColor.equals(Color.ORANGE)) {
				currentColor = Color.RED;
			}
			else {
				currentColor = Color.ORANGE;
			}
		}	
	}

	/** Set the source of the explosion
	 *  @param src source of the explosion */	
	public final void setSource(Entity src) {
		source = src;
	}

	/** Get the source of the explosion
	 *  @return explosion source */
	public final Entity getSource() {
		return source;
	}
}
