import javax.swing._
import java.awt._
import java.awt.event._

/** JPanel containing score controls and fire controls. 
 *  @author Nathan Jackson*/
class ControlPanel extends JPanel {
	
	var fireButton = new JButton("Fire!")
	var scoreLabel = new JLabel("Score: ")
	var scoreText = new JTextField("0")

//	scoreText.setHorizontalAlignment(JTextField.CENTER);
	scoreText.setColumns(5);
	scoreText.setEnabled(false);

	add(fireButton);
	add(scoreLabel);
	add(scoreText);	

}
