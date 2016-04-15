package model.geometry2d;

import model.matrixLib.Matrix;
import model.matrixLib.Vector;

/**
 * The Class Vertex2D.
 * 
 * @author Michael Vassernis 319582888
 */
public class Vertex2D implements I2DVertex {

	/** The vector. */
	protected Vector vector;
	
	/**
	 * Instantiates a new vertex 2d with two coordinates.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Vertex2D(float x, float y) {
		this.vector = new Vector(new float[]{x, y, 1});
	}
	
	/**
	 * Instantiates a new vertex 2d with a vector.
	 *
	 * @param vector the vector
	 */
	protected Vertex2D(Vector vector) {
		this.vector = vector;
	}

	/* (non-Javadoc)
	 * @see model.geometry2d.I2DVertex#getX()
	 */
	@Override
	public float getX() {
		return this.vector.getValue(0);
	}

	/* (non-Javadoc)
	 * @see model.geometry2d.I2DVertex#getY()
	 */
	@Override
	public float getY() {
		return this.vector.getValue(1);
	}
	
	/* (non-Javadoc)
	 * @see model.geometry2d.I2DVertex#applyMatrix(model.matrixLib.Matrix)
	 */
	@Override
	public Vertex2D applyMatrix(Matrix matrix) {
		return new Vertex2D(matrix.multiply(this.vector));
	}
}
