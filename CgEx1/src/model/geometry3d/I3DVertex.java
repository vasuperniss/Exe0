package model.geometry3d;

import model.geometry2d.I2DVertex;
import model.matrixLib.Matrix;

/**
 * The Interface I2DEdge.
 * @author Michael Vassernis 319582888
 */
public interface I3DVertex extends I2DVertex {

	/**
	 * Gets the z coordinate.
	 *
	 * @return the z
	 */
	public float getZ();
	
	/* (non-Javadoc)
	 * @see model.geometry2d.I2DVertex#applyMatrix(model.matrixLib.Matrix)
	 */
	public Vertex3D applyMatrix(Matrix matrix);
	
	/**
	 * Gets the distance from this to other.
	 *
	 * @param other the second vertex
	 * @return the distance
	 */
	public float getDistanceFrom(I3DVertex other);
}
