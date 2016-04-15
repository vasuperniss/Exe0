package model.matrixLib;

/**
 * The Class Matrix2DFactory.
 * 
 * @author Michael Vassernis 319582888
 */
public class Matrix2DFactory {

	/**
	 * Gets the identity matrix.
	 *
	 * @return the identity matrix
	 */
	public Matrix getIdentityMatrix() {
		Matrix I = new Matrix(3, 3);
		for (int i = 0; i < 3; i++)
			I.setValueAt(i, i, 1);
		return I;
	}

	/**
	 * Gets the transformation matrix.
	 *
	 * @param moveX the move x steps
	 * @param moveY the move y steps
	 * @return the transformation matrix
	 */
	public Matrix getTransformationMatrix(float moveX, float moveY) {
		Matrix transform = this.getIdentityMatrix();
		transform.setValueAt(0, 2, moveX);
		transform.setValueAt(1, 2, moveY);
		return transform;
	}

	/**
	 * Gets the rotation matrix in radiant.
	 *
	 * @param rotation the rotation
	 * @return the rotation radiant
	 */
	public Matrix getRotationMatrixRad(float rotation) {
		float cos = (float) Math.cos(rotation);
		float sin = (float) Math.sin(rotation);
		float[][] rotate = new float[][] { { cos, sin, 0 },
											{ -sin, cos, 0 },
											{ 0, 0, 1 } };
		return new Matrix(rotate);
	}

	/**
	 * Gets the rotation matrix in degrees.
	 *
	 * @param rotation the rotation
	 * @return the rotation degree
	 */
	public Matrix getRotationMatrixDeg(float rotation) {
		return this.getRotationMatrixRad((float) Math.toRadians(rotation));
	}

	/**
	 * Gets the shear matrix.
	 *
	 * @param sheerX the sheer x
	 * @param sheerY the sheer y
	 * @return the shear matrix
	 */
	public Matrix getShearMatrix(float sheerX, float sheerY) {
		float[][] shear = new float[][] { { 1, sheerX, 0 },
											{ sheerY, 1, 0 },
											{ 0, 0, 1 } };
		return new Matrix(shear);
	}

	/**
	 * Gets the scale matrix.
	 *
	 * @param scaleX the scale x
	 * @param scaleY the scale y
	 * @return the scale matrix
	 */
	public Matrix getScaleMatrix(float scaleX, float scaleY) {
		float[][] rotate = new float[][] { { scaleX, 0, 0 },
											{ 0, scaleY, 0 },
											{ 0, 0, 1 } };
		return new Matrix(rotate);
	}

	/**
	 * Gets the scale matrix.
	 *
	 * @param scale the scale
	 * @return the scale matrix
	 */
	public Matrix getScaleMatrix(float scale) {
		return this.getScaleMatrix(scale, scale);
	}
	
	/**
	 * Gets the x reflection matrix.
	 *
	 * @return the x reflection matrix
	 */
	public Matrix getXReflectionMatrix() {
		return this.getScaleMatrix(-1, 1);
	}
	
	/**
	 * Gets the y reflection matrix.
	 *
	 * @return the y reflection matrix
	 */
	public Matrix getYReflectionMatrix() {
		return this.getScaleMatrix(1, -1);
	}
}
