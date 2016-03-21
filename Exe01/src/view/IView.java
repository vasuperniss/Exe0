package view;

import java.awt.Frame;

/**
 * The Interface IView.
 */
public interface IView {
	
	/**
	 * Request the view to re-draw itself on the count
	 * of changes made.
	 */
	public void reDraw();

	public Frame getFrame();
}
