import model.Scene;
import presenter.Presenter;
import view.Canvas2D;
import view.CloseableFrame;
import view.ViewEventHandler;

public class Main {
	private static final int WIDTH = 700, HEIGHT = 450;
	
	public static void main(String[] args) {
		// View Creation
		CloseableFrame frame = new CloseableFrame();
		
		// Model Creation
		Scene scene = new Scene();
		
		// Presenter Creation
		Presenter presenter = new Presenter(scene);
		
		// View Creation continued
		Canvas2D canvas = new Canvas2D(presenter);
		canvas.setSize(WIDTH, HEIGHT);
		ViewEventHandler viewHandler = new ViewEventHandler(canvas, presenter);
		canvas.addMouseListener(viewHandler);
		canvas.addKeyListener(viewHandler);
		
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
}
