package model.geometry2d;

import java.awt.Graphics;

/**
 * The Interface IEdge.
 * @author Michael Vassernis 319582888
 * @author Eran Haberman 201508793
 */
public interface I2DEdge {

	/**
	 * Gets the start Vertex of the Edge.
	 *
	 * @return the start Vertex
	 */
	public I2DVertex getStart();
	
	/**
	 * Gets the end Vertex of the Edge.
	 *
	 * @return the end Vertex
	 */
	public I2DVertex getEnd();

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
