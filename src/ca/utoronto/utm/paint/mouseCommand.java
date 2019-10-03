package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

public interface mouseCommand {
	
	void execute(MouseEvent e, PaintModel model, Shape shape);
	
	

}
