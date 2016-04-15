package view.drawing;

import java.awt.Color;
import java.awt.Graphics;

/**
 * The Abstract Class BaseDrawable.
 * 
 * @author Michael Vassernis 319582888
 */
public abstract class BaseDrawable implements IDrawable {
	
	/** The color. */
	protected Color color;
	
	/**
	 * Instantiates a new base drawable.
	 */
	public BaseDrawable() {
		this.color = Color.PINK;
	}

	/* (non-Javadoc)
	 * @see view.drawing.IDrawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
	}
	
	/* (non-Javadoc)
	 * @see view.drawing.IDrawable#setColor(java.awt.Color)
	 */
	@Override
	public void setColor(Color c) {
		this.color = c;
	}
	
	/* (non-Javadoc)
	 * @see view.drawing.IDrawable#getColor()
	 */
	@Override
	public Color getColor() {
		return this.color;
	}
}
