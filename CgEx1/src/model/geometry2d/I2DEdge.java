package model.geometry2d;

import view.drawing.IDrawable;

/**
 * The Interface IEdge.
 * @author Michael Vassernis 319582888
 * @author Eran Haberman 201508793
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

	public I2DVertex getIntVertexAtY(float yMax);

	public I2DVertex getIntVertexAtX(float xMin);
}
