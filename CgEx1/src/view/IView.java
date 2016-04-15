package view;

import java.util.List;

import view.drawing.IDrawable;
import controller.IViewController;

/**
 * The Interface IDrawable.
 * 
 * @author Michael Vassernis 319582888
 */
public interface IView{
	
	/**
	 * Sets the controller.
	 *
	 * @param controller the new controller
	 */
	void setController(IViewController controller);
	
	/**
	 * Draws the given drawables.
	 *
	 * @param drawables the drawables
	 */
	void draw(List<IDrawable> drawables);
}
