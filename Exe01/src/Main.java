import view.Canvas2D;
import view.CloseableFrame;
import view.ViewHandler;

public class Main {
	private static final int WIDTH = 700, HEIGHT = 450;
	
	public static void main(String[] args) {
		CloseableFrame frame = new CloseableFrame();
		Canvas2D canvas = new Canvas2D();
		canvas.setSize(WIDTH, HEIGHT);
		
		ViewHandler viewHandler = new ViewHandler();
		canvas.addMouseListener(viewHandler);
		canvas.addKeyListener(viewHandler);
		
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
}
