import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** JPanel containing score controls and fire controls. 
 *  @author Nathan Jackson*/
public class ControlPanel extends JPanel {
	
	private JButton fireButton;
	private JLabel scoreLabel;
	private JTextField scoreText;

	/** No args constructor initializes the controls */
	public ControlPanel() {
		super();
		
		// Initialize fireButton
		fireButton = new JButton("Fire!");
		
		// Initialize scoreLabel
		scoreLabel = new JLabel("Score: ");

		// Initialize scoreText
		scoreText = new JTextField("0");
		scoreText.setHorizontalAlignment(JTextField.CENTER);
		scoreText.setColumns(5);
		scoreText.setEnabled(false);

		// Add controls to the panel
		this.add(fireButton);
		this.add(scoreLabel);
		this.add(scoreText);	
	}

	/** Return the firebutton 
	 *  @return JButton that fires a laser */
	public JButton getFireButton() {
		return fireButton;
	}

	/** Return the score label
	 *  @return JLabel that indicates the score */
	public JLabel getScoreLabel() {
		return scoreLabel;
	}

	/** Return the score text field
	 *  @return JTextField that shows the current score */
	public JTextField getScoreText() {
		return scoreText;
	}
}
