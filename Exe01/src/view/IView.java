package view;

import java.awt.Frame;

/**
 * The Interface IView.
 * @author Michael Vassernis 319582888
 * @author Eran Haberman 201508793
 */
public interface IView {
	
	/**
	 * Request the view to re-draw itself on the count
	 * of changes made.
	 */
	public void reDraw();

	public Frame getFrame();
}
