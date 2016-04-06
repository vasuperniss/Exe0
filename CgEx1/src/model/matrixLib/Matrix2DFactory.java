package model.matrixLib;

public class Matrix2DFactory {

	public Matrix getIdentityMatrix() {
		Matrix I = new Matrix(3, 3);
		for (int i = 0; i < 3; i++)
			I.setValueAt(i, i, 1);
		return I;
	}

	public Matrix getTransformationMatrix(float moveX, float moveY) {
		Matrix transform = this.getIdentityMatrix();
		transform.setValueAt(0, 2, moveX);
		transform.setValueAt(1, 2, moveY);
		return transform;
	}

	public Matrix getRotationMatrixRad(float rotation) {
		float cos = (float) Math.cos(rotation);
		float sin = (float) Math.sin(rotation);
		float[][] rotate = new float[][] { { cos, sin, 0 },
											{ -sin, cos, 0 },
											{ 0, 0, 1 } };
		return new Matrix(rotate);
	}

	public Matrix getRotationMatrixDeg(float rotation) {
		return this.getRotationMatrixRad((float) Math.toRadians(rotation));
	}

	public Matrix getShearMatrix(float sheerX, float sheerY) {
		float[][] shear = new float[][] { { 1, sheerX, 0 },
											{ sheerY, 1, 0 },
											{ 0, 0, 1 } };
		return new Matrix(shear);
	}

	public Matrix getScaleMatrix(float scaleX, float scaleY) {
		float[][] rotate = new float[][] { { scaleX, 0, 0 },
											{ 0, scaleY, 0 },
											{ 0, 0, 1 } };
		return new Matrix(rotate);
	}

	public Matrix getScaleMatrix(float scale) {
		return this.getScaleMatrix(scale, scale);
	}
	
	public Matrix getXReflectionMatrix() {
		return this.getScaleMatrix(-1, 1);
	}
	
	public Matrix getYReflectionMatrix() {
		return this.getScaleMatrix(1, -1);
	}
}
