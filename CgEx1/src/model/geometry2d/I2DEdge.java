package model.geometry2d;

import view.drawing.IDrawable;

/**
 * The Interface I2DEdge.
 * @author Michael Vassernis 319582888
 */
public interface I2DEdge extends IDrawable {

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
	 * Gets the slope of the edge.
	 *
	 * @return the slope
	 */
	public float getSlope();

	/**
	 * Gets the vertex at the given y.
	 *
	 * @param y the y position
	 * @return the vertex at y
	 */
	public I2DVertex getIntVertexAtY(float y);

	/**
	 * Gets the vertex at the given x.
	 *
	 * @param x the x position
	 * @return the vertex at x
	 */
	public I2DVertex getIntVertexAtX(float x);
}
