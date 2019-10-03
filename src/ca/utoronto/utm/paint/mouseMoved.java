package ca.utoronto.utm.paint;

import java.util.Observable;

import javafx.scene.input.MouseEvent;

public class mouseMoved implements mouseCommand{

	@Override
	public void execute(MouseEvent e, PaintModel model, Shape shape) {
		if (shape != null && shape instanceof Polyline) {
			Polyline poly = (Polyline) shape; // CAST
			
			Point cursor = new Point((int) e.getX(), (int) e.getY());
			poly.getPoints().set(poly.getNumberOfPoints()-1, cursor);
			model.notifyChange();
		}
		
	}
	
}
