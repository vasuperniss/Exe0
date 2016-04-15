package view.drawing;

import java.awt.Color;
import java.awt.Graphics;

/**
 * The Interface IDrawable.
 * 
 * @author Michael Vassernis 319582888
 */
public interface IDrawable {
	
	/**
	 * Draw on Graphics object.
	 *
	 * @param g the Graphics object
	 */
	void draw(Graphics g);
	
	/**
	 * Sets the color.
	 *
	 * @param c the new color
	 */
	void setColor(Color c);
	
	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	Color getColor();
}
