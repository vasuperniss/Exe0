import model.Scene;
import presenter.Presenter;
import view.Canvas2D;
import view.CloseableFrame;
import view.ViewEventHandler;

/**
 * The Class Main.
 */
public class Main {
	
	/** The Constant HEIGHT, WIDTH. */
	private static final int WIDTH = 700, HEIGHT = 450;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// View Creation
		CloseableFrame frame = new CloseableFrame();
		frame.setTitle("Polygon Drawing - Exe0");
		
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
		canvas.addMouseMotionListener(viewHandler);
		
		// start up the Frame
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
}
