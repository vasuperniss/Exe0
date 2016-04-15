package model.geometry2d;

import model.matrixLib.Matrix;

/**
 * The Interface I2DVertex.
 * @author Michael Vassernis 319582888
 */
public interface I2DVertex {
	
	/**
	 * Gets the x coordinate.
	 *
	 * @return the x
	 */
	public float getX();
	
	/**
	 * Gets the y coordinate.
	 *
	 * @return the y
	 */
	public float getY();
	
	/**
	 * Apply matrix.
	 *
	 * @param matrix the matrix
	 * @return the resulted vertex 2d
	 */
	Vertex2D applyMatrix(Matrix matrix);
}
