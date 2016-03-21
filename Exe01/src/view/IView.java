package view;

import java.awt.Frame;

/**
 * The Interface IView.
 * @author Michael Vassernis 319582888
 * @author 
 */
public interface IView {
	
	/**
	 * Request the view to re-draw itself on the count
	 * of changes made.
	 */
	public void reDraw();

	public Frame getFrame();
}
