package view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CloseableFrame extends Frame {

	private static final long serialVersionUID = 1L;

	public CloseableFrame() {
		WindowAdapter myWindowAdapter = new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		
		this.addWindowListener(myWindowAdapter);
	}
}
