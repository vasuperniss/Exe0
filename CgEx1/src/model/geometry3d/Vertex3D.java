package model.geometry3d;

import model.geometry2d.Vertex2D;
import model.matrixLib.Matrix;
import model.matrixLib.Vector;

/**
 * The Class Polygon2D.
 * 
 * @author Michael Vassernis 319582888
 */
public class Vertex3D extends Vertex2D implements I3DVertex {

	/**
	 * Instantiates a new vertex3d.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public Vertex3D(float x, float y, float z) {
		super(new Vector(new float[] { x, y, z, 1 }));
	}

	/**
	 * Instantiates a new vertex3 d.
	 */
	public Vertex3D() {
		super(new Vector(new float[] { 0, 0, 0, 1 }));
	}

	/**
	 * Instantiates a new vertex3d.
	 *
	 * @param vector the vector
	 */
	protected Vertex3D(Vector vector) {
		super(vector);
	}

	/* (non-Javadoc)
	 * @see model.geometry3d.I3DVertex#getZ()
	 */
	@Override
	public float getZ() {
		return this.vector.getValue(2);
	}

	/* (non-Javadoc)
	 * @see model.geometry2d.Vertex2D#applyMatrix(model.matrixLib.Matrix)
	 */
	@Override
	public Vertex3D applyMatrix(Matrix matrix) {
		Vertex3D result = new Vertex3D(matrix.multiply(this.vector));
		result.vector.normalizeByLastDim();
		return result;
	}

	/* (non-Javadoc)
	 * @see model.geometry3d.I3DVertex#getDistanceFrom(model.geometry3d.I3DVertex)
	 */
	@Override
	public float getDistanceFrom(I3DVertex lookAt) {
		return (float) Math.sqrt(Math.pow(this.getX() - lookAt.getX(), 2)
				+ Math.pow(this.getY() - lookAt.getY(), 2)
				+ Math.pow(this.getZ() - lookAt.getZ(), 2));
	}
}
