package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

public class mouseReleased implements mouseCommand{

	@Override
	public void execute(MouseEvent e, PaintModel model, Shape shape) {
		
		if (PaintPanel.getMode() == "Squiggle") {
			PaintPanel.shape = null;
				
		} else if (PaintPanel.getMode() == "Circle") {
			if (shape != null) {
				Circle circle = (Circle) shape; // CAST
				int radius = (int) Math.sqrt(Math.pow(circle.getCentre().getX() - e.getX(), 2) + Math.pow(circle.getCentre().getY() - e.getY(), 2));				
				circle.setRadius(radius);
				
				PaintPanel.shape = null;
			}
			
		} else if (PaintPanel.getMode() == "Rectangle") {
			if (shape != null) {
				Rect rectangle = (Rect) shape;
				
				int width = (int) (rectangle.getCentre().getX() - e.getX()); // calculate the distance from the first 
				int length = (int) ((rectangle.getCentre().getY()) - e.getY()); // click to when you release to get length and width
				rectangle.setLength(length);
				rectangle.setWidth(width);

				PaintPanel.shape = null;
			}
			
		} else if (PaintPanel.getMode() == "Square") {
			if (shape != null) {
				Square square = (Square) shape;
				
				int sides = (int) (square.getCentre().getX() - e.getX());
				square.setLength(sides);
				square.setWidth(sides);
				
				PaintPanel.shape = null;
			}		
		}
		
	}

}
