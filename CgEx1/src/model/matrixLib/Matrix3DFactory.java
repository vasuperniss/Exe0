package model.matrixLib;

import model.geometry3d.Vector3D;

/**
 * The Class Matrix3DFactory.
 * 
 * @author Michael Vassernis 319582888
 */
public class Matrix3DFactory {
	
	/**
	 * Gets the identity matrix.
	 *
	 * @return the identity matrix
	 */
	public Matrix getIdentityMatrix() {
		return new Matrix(new float [][] { { 1, 0, 0, 0 },
											{ 0, 1, 0, 0 },
											{ 0, 0, 1, 0 },
											{ 0, 0, 0, 1 } });
	}
	
	/**
	 * Gets the transformation matrix.
	 *
	 * @param x the x transformation
	 * @param y the y transformation
	 * @param z the z transformation
	 * @return the transformation matrix
	 */
	public Matrix getTransformationMatrix(float x, float y, float z) {
		return new Matrix(new float [][] { { 1, 0, 0, x },
											{ 0, 1, 0, y },
											{ 0, 0, 1, z },
											{ 0, 0, 0, 1 } });
	}
	
	/**
	 * The Enum Axis.
	 */
	public enum Axis {
		
		/** The x. */
		X, 
		 /** The y. */
		 Y, 
		 /** The z. */
		 Z
	}
	
	/**
	 * Gets the rotation matrix in radiant.
	 *
	 * @param rotation the rotation
	 * @param axis the axis
	 * @return the rotation matrix
	 */
	public Matrix getRotationMatrixRad(float rotation, Axis axis) {
		float cos = (float) Math.cos(rotation);
		float sin = (float) Math.sin(rotation);
		if (axis == Axis.X) {
			// rotate around X axis
			return new Matrix(new float[][] { {1, 0, 0, 0 },
											{ 0, cos, sin, 0 },
											{ 0, -sin, cos, 0 },
											{ 0, 0, 0, 1 } });
		} else if (axis == Axis.Y) {
			// rotate around Y axis
			return new Matrix(new float[][] { {cos, 0, sin, 0 },
											{ 0, 1, 0, 0 },
											{ -sin, 0, cos, 0 },
											{ 0, 0, 0, 1 } });
		} else if (axis == Axis.Z) {
			// rotate around Z axis
			return new Matrix(new float[][] {{ cos, sin, 0, 0 },
											{ -sin, cos, 0, 0 },
											{ 0, 0, 1, 0 },
											{ 0, 0, 0, 1 } });
		}
		return null;
	}
	
	/**
	 * Gets the rotation matrix in degrees.
	 *
	 * @param rotation the rotation
	 * @param axis the axis
	 * @return the rotation matrix
	 */
	public Matrix getRotationMatrixDeg(float rotation, Axis axis) {
		return this.getRotationMatrixRad((float) Math.toRadians(rotation), axis);
	}
	
	/**
	 * Gets the scale matrix.
	 *
	 * @param scaleX the scale x
	 * @param scaleY the scale y
	 * @param scaleZ the scale z
	 * @return the scale matrix
	 */
	public Matrix getScaleMatrix(float scaleX, float scaleY, float scaleZ) {
		return new Matrix(new float [][] { { scaleX, 0, 0, 0 },
											{ 0, scaleY, 0, 0 },
											{ 0, 0, scaleZ, 0 },
											{0, 0, 0, 1 } });
	}
	
	/**
	 * Gets the scale matrix.
	 *
	 * @param scale the scale
	 * @return the scale matrix
	 */
	public Matrix getScaleMatrix(float scale) {
		return this.getScaleMatrix(scale, scale, scale);
	}
	
	/**
	 * Gets the x reflection matrix.
	 *
	 * @return the x reflection matrix
	 */
	public Matrix getXReflectionMatrix() {
		return this.getScaleMatrix(-1, 1, 1);
	}
	
	/**
	 * Gets the y reflection matrix.
	 *
	 * @return the y reflection matrix
	 */
	public Matrix getYReflectionMatrix() {
		return this.getScaleMatrix(1, -1, 1);
	}
	
	/**
	 * Gets the z reflection matrix.
	 *
	 * @return the z reflection matrix
	 */
	public Matrix getZReflectionMatrix() {
		return this.getScaleMatrix(1, 1, -1);
	}

	/**
	 * Gets the rotation matrix vectors.
	 *
	 * @param xv the X vector
	 * @param yv the Y vector
	 * @param zv the Z vector
	 * @return the rotation matrix vectors
	 */
	public Matrix getRotationMatrixVectors(Vector3D xv, Vector3D yv, Vector3D zv) {
		return new Matrix(new float [][] { 
					{ xv.getValue(0), xv.getValue(1), xv.getValue(2), 0 },
					{ yv.getValue(0), yv.getValue(1), yv.getValue(2), 0 },
					{ zv.getValue(0), zv.getValue(1), zv.getValue(2), 0 },
					{0, 0, 0, 1 } });
	}
}
