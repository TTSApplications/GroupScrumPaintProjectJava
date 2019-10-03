package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

public class mouseClicked implements mouseCommand{
	
	ShapeFactory shapeFactory = new ShapeFactory();

	@Override
	public void execute(MouseEvent e, PaintModel model, Shape shape) {
		
		if (PaintPanel.getMode() == "Polyline") { // ~j
			Polyline polyline = (Polyline) shape;
			
			if (e.getClickCount() == 2) {
			    // System.out.println(e.getClickCount()); // for debugging
				// Double-click to set last point and end a polyline
				PaintPanel.shape = null;
			} else {
				// Click to add new Point to existing Polyline
				if (shape == null) {
					
					shape = shapeFactory.createShape("Polyline");
					
					PaintPanel.shape = shape;
					
					polyline = (Polyline) shape; // CAST
					
					Point first = new Point((int) e.getX(), (int) e.getY());
					polyline.addPoint(first);
					polyline.addPoint(first); // Added twice so Polyline has at least 2 points.
					
					polyline.setColor(ShapeChooserPanel.getColor());
					polyline.setStrokeThickness(ShapeChooserPanel.getThickness());

					// this.model.addShape(polyline);
					model.addCommand(new DrawPolyline(polyline));
					
				} else {
					Point newPolyPoint = new Point((int) e.getX(), (int) e.getY());
					polyline.addPoint(newPolyPoint);
					model.notifyChange();
				}
			}
		}
	}
}
