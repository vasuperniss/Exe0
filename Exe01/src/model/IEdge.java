package model;

import java.awt.Graphics;
import java.awt.Point;

/**
 * The Interface IEdge.
 */
public interface IEdge {

	/**
	 * Gets the start Vertex of the Edge.
	 *
	 * @return the start Vertex
	 */
	public Point getStart();
	
	/**
	 * Gets the end Vertex of the Edge.
	 *
	 * @return the end Vertex
	 */
	public Point getEnd();

	/**
	 * Draws the Edge on g.
	 *
	 * @param g the Graphics object to draw on
	 */
	public void draw(Graphics g);
}
