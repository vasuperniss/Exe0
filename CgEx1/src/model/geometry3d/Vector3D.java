package model.geometry3d;

import model.matrixLib.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class Polygon2D.
 * 
 * @author Michael Vassernis 319582888
 */
public class Vector3D extends Vector {

	/**
	 * Instantiates a new vector3d with a vector array.
	 *
	 * @param vector the vector
	 */
	public Vector3D(float[] vector) {
		super(4);
		this.matrix[0][0] = vector[0];
		this.matrix[1][0] = vector[1];
		this.matrix[2][0] = vector[2];
		this.matrix[3][0] = 1;
	}
	
	/**
	 * Instantiates a new vector3d with 3 coordinates.
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param z the z coordinate
	 */
	public Vector3D(float x, float y, float z) {
		super(4);
		this.matrix[0][0] = x;
		this.matrix[1][0] = y;
		this.matrix[2][0] = z;
		this.matrix[3][0] = 1;
	}
	
	/**
	 * Instantiates a new vector3d with two Vertices.
	 *
	 * @param p1 the first vertex
	 * @param p2 the second vertex
	 */
	public Vector3D(I3DVertex p1, I3DVertex p2) {
		this(p2.getX() - p1.getX(),
				p2.getY() - p1.getY(),
				p2.getZ() - p1.getZ());
	}
	
	/**
	 * Cross product.
	 *
	 * @param v the second Vector
	 * @return this x V
	 */
	public Vector3D crossProduct(Vector3D v) {
		if (this.matrix.length != v.matrix.length || this.matrix.length != 4)
			throw new RuntimeException("Vector dimentions do not match.");
		Vector3D u = this;		
		float[] crossP = new float[4];
		// calculate the cross product of this x V
		crossP[0] = u.matrix[1][0] * v.matrix[2][0] - u.matrix[2][0] * v.matrix[1][0];
		crossP[1] = u.matrix[2][0] * v.matrix[0][0] - u.matrix[0][0] * v.matrix[2][0];
		crossP[2] = u.matrix[0][0] * v.matrix[1][0] - u.matrix[1][0] * v.matrix[0][0];
		crossP[3] = u.matrix[3][0] * v.matrix[3][0];
		Vector3D crossV = new Vector3D(crossP);
		// normalize the resulted vector
		crossV.normalize();
		return crossV;
	}
	
	/**
	 * Gets the normalized vector version of this vector.
	 *
	 * @return the normalized vector
	 */
	public Vector3D getNormalized() {
		// calculate the length of the vector
		float length = this.matrix[0][0] * this.matrix[0][0]
						+ this.matrix[1][0] * this.matrix[1][0]
						+ this.matrix[2][0] * this.matrix[2][0];
		length = (float) Math.sqrt(length);
		// divide all dimentions by the length
		return new Vector3D(this.matrix[0][0] / length,
							this.matrix[0][0] / length,
							this.matrix[0][0] / length);
	}
	
	/**
	 * Normalize this vector.
	 */
	public void normalize() {
		// calculate the length of the vector
		float length = this.matrix[0][0] * this.matrix[0][0]
						+ this.matrix[1][0] * this.matrix[1][0]
						+ this.matrix[2][0] * this.matrix[2][0];
		length = (float) Math.sqrt(length);
		// divide all dimentions by the length
		this.matrix[0][0] /= length;
		this.matrix[1][0] /= length;
		this.matrix[2][0] /= length;
	}
}
