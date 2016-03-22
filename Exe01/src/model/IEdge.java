package model;

import java.awt.Graphics;

/**
 * The Interface IEdge.
 * @author Michael Vassernis 319582888
 * @author Eran Haberman 201508793
 */
public interface IEdge {

	/**
	 * Gets the start Vertex of the Edge.
	 *
	 * @return the start Vertex
	 */
	public Vertex getStart();
	
	/**
	 * Gets the end Vertex of the Edge.
	 *
	 * @return the end Vertex
	 */
	public Vertex getEnd();

	/**
	 * Draws the Edge on g.
	 *
	 * @param g the Graphics object to draw on
	 */
	public void draw(Graphics g);
	
	/**
	 * Gets the slope of the edge.
	 *
	 * @return the slope
	 */
	public float getSlope();
}
