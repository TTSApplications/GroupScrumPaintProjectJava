package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;

public class mousePressed implements mouseCommand{

	@Override
	public void execute(MouseEvent e, PaintModel model, Shape shape) {
		ShapeFactory shapeFactory = new ShapeFactory();
		
		// Not good to have this here because PaintPanel starts with no mode selected
		// this.shape = shapeFactory.createShape(this.mode); // B9
		
		if (PaintPanel.getMode() == "Squiggle") { // ~j
			shape = shapeFactory.createShape("Squiggle"); // Create and CAST
			Squiggle squiggle = (Squiggle) shape;

			squiggle.addPoint(new Point((int) e.getX(), (int) e.getY()));
			
			squiggle.setColor(ShapeChooserPanel.getColor());
			squiggle.setStrokeThickness(ShapeChooserPanel.getThickness());
			
			// this.model.addShape(squiggle);
			model.addCommand(new DrawSquiggle(squiggle));
			
			PaintPanel.shape = shape;

		} else if (PaintPanel.getMode() == "Circle") {
			shape = shapeFactory.createShape("Circle"); // Create and CAST
			Circle circle = (Circle) shape;
			
			// Problematic notion of radius and centre!!
			Point centre = new Point((int) e.getX(), (int) e.getY());
			int radius = 0;
			
			circle.setCentre(centre); // ADDED
			circle.setRadius(radius);
			
			circle.setColor(ShapeChooserPanel.getColor());
			circle.setStrokeThickness(ShapeChooserPanel.getThickness());

			circle.setFilled(PaintPanel.getFill());

			// this.model.addShape(circle);
			// this.model.getPaintCommandStack().pushCommand(shape);
			model.addCommand(new DrawCircle(circle));
			
			PaintPanel.shape = shape;
			
		} else if (PaintPanel.getMode() == "Rectangle") {
			shape = shapeFactory.createShape("Rectangle"); // Create and CAST
			Rect rectangle = (Rect) shape;
			
			rectangle.setCentre(new Point((int) e.getX(), (int) e.getY())); // ADDED
			rectangle.setWidth(0);
			rectangle.setLength(0);

			rectangle.setColor(ShapeChooserPanel.getColor());
			rectangle.setStrokeThickness(ShapeChooserPanel.getThickness());
			
			rectangle.setFilled(PaintPanel.getFill()); 
			
			// this.model.addShape(rectangle);
			//this.model.getPaintCommandStack().pushCommand(shape);
			model.addCommand(new DrawRectangle(rectangle));
			
			PaintPanel.shape = shape;
			
		} else if (PaintPanel.getMode() == "Square") {
			shape = shapeFactory.createShape("Square"); // Create and CAST
			
			Square square = (Square) shape;
			
			square.setCentre(new Point((int) e.getX(), (int) e.getY())); // ADDED
			square.setWidth(0);
			square.setLength(0);
			
			square.setColor(ShapeChooserPanel.getColor());
			square.setStrokeThickness(ShapeChooserPanel.getThickness());
			
			square.setFilled(PaintPanel.getFill());
			
			//this.model.getPaintCommandStack().pushCommand(shape);
			// this.model.addShape(square);
			model.addCommand(new DrawSquare(square));
			
			PaintPanel.shape = shape;
			
		}
	}
}



