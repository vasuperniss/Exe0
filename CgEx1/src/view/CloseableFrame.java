package view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The Class CloseableFrame.
 * @author Michael Vassernis 319582888
 */
public class CloseableFrame extends Frame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new closeable frame.
	 */
	public CloseableFrame() {
		WindowAdapter myWindowAdapter = new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		this.setMinimumSize(new Dimension(50, 50));
		this.addWindowListener(myWindowAdapter);
	}
}
