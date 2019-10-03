	package ca.utoronto.utm.paint;
	
	import javafx.event.EventHandler;
	import javafx.scene.canvas.Canvas;
	import javafx.scene.canvas.GraphicsContext;
	import javafx.scene.input.MouseEvent;
	import javafx.scene.layout.StackPane;
	import java.util.Observable;
	import java.util.Observer;
	
	class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {
	
		private int i = 0;
		private PaintModel model; // slight departure from MVC, because of the way painting works
		private View view; // So we can talk to our parent or other components of the view
	
		private static String mode; // modifies how we interpret input (could be better?)

		protected static Shape shape; // Set to protected as MouseCommands need access
		//private ShapeFactory shapeFactory = new ShapeFactory(); // B9 Where to place?
		
		private static boolean fill;
	
		private Canvas canvas;

		public PaintPanel(PaintModel model, View view) {
	
			this.canvas = new Canvas(600, 600);
			this.getChildren().add(this.canvas);
			// The canvas is transparent, so the background color of the
			// containing pane serves as the background color of the canvas.
			this.setStyle("-fx-background-color: white");
	
			this.addEventHandler(MouseEvent.ANY, this);
	
			//this.mode = "Circle"; // bad code here?
	
			this.model = model;
			this.model.addObserver(this);
	
			this.view = view;
		}
		
		public static String getMode() {
			
			return mode;
			
		}

		public void repaint() {
	
			GraphicsContext g = this.canvas.getGraphicsContext2D();
	
			// Clear the canvas
			g.clearRect(0, 0, this.getWidth(), this.getHeight());
			g.strokeText("i=" + i, -50, -75);
			i = i + 1;
	
			// ============================== DRAW SHAPES ==============================
			
			// Execute all DrawingCommands in PaintModel's commandStack.
			this.model.getPaintCommandStack().operateAll(g);
		}
	
		@Override
		public void update(Observable o, Object arg) {
	
			// Not exactly how MVC works, but similar.
			this.repaint();
		}
	
		/**
		 * Controller aspect of this
		 */
		public void setMode(String mode) {
			this.mode = mode;
		}
		
		public void setFill(boolean fill) {
			this.fill = fill;
		}
		
		public static boolean getFill() { //For future use if necessary
			return fill;
		}
		
		// ============================== UNDO/REDO ==============================
		public void undo() {
			this.model.undo();
		}
		
		public void redo() {
			this.model.redo();
		}
		@Override
		public void handle(MouseEvent event) {
	
			if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
				((mouseCommand)new mouseDragged()).execute(event, model, shape);
			} else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
				((mouseCommand)new mousePressed()).execute(event, model, shape);
			} else if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
				((mouseCommand)new mouseMoved()).execute(event, model, shape);
			} else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
				((mouseCommand)new mouseClicked()).execute(event, model, shape);
			} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
			((mouseCommand)new mouseReleased()).execute(event, model, shape);
			}
		}
}