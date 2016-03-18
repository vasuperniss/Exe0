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
		Canvas2D canvas = new Canvas2D();
		canvas.setSize(WIDTH, HEIGHT);
		
		// Model Creation
		Scene scene = new Scene();
		
		// Presenter Creation
		Presenter presenter = new Presenter(scene);
		
		// View Creation continued
		ViewEventHandler viewHandler = new ViewEventHandler(presenter);
		canvas.addMouseListener(viewHandler);
		canvas.addKeyListener(viewHandler);
		
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
}
