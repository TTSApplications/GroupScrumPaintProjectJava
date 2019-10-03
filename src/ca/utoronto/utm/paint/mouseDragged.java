package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

public class mouseDragged implements mouseCommand{

	@Override
	public void execute(MouseEvent e, PaintModel model, Shape shape) {
		if (PaintPanel.getMode() == "Squiggle") {
			Squiggle squiggle = (Squiggle) shape; // CAST
			squiggle.addPoint(new Point((int) e.getX(), (int) e.getY()));
			
			model.notifyChange();
			
		} else if (PaintPanel.getMode() == "Circle") { // ~ j
			Circle circle = (Circle) shape; // CAST
			
			int radius = (int) Math.sqrt(Math.pow(circle.getCentre().getX() - e.getX(), 2) + Math.pow(circle.getCentre().getY() - e.getY(), 2));				
			circle.setRadius(radius);
			
			model.notifyChange();
			
		}else if (PaintPanel.getMode() == "Rectangle") {	
			Rect rectangle = (Rect) shape; // CAST
			
			int width = (int) (rectangle.getCentre().getX() - e.getX()); // calculate the distance from the first 
			int length = (int) ((rectangle.getCentre().getY()) - e.getY()); // click to when you release to get length and width
			rectangle.setLength(length);
			rectangle.setWidth(width);

			model.notifyChange();
			
		}else if (PaintPanel.getMode() == "Square") {	
			Square square = (Square) shape; // CAST
			int width = (int) (square.getCentre().getX() - e.getX());
			int length = (int) (square.getCentre().getY() - e.getY());
			square.setLength(length);
			square.setWidth(width);
			square.setSides(length);
			square.setDirection(width, length);

			model.notifyChange();
		}
	}

}
