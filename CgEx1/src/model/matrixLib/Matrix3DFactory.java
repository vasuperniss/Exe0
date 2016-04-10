package model.matrixLib;

import model.geometry3d.Vector3D;

public class Matrix3DFactory {
	
	public Matrix getIdentityMatrix() {
		return new Matrix(new float [][] { { 1, 0, 0, 0 },
											{ 0, 1, 0, 0 },
											{ 0, 0, 1, 0 },
											{ 0, 0, 0, 1 } });
	}
	
	public Matrix getTransformationMatrix(float x, float y, float z) {
		return new Matrix(new float [][] { { 1, 0, 0, x },
											{ 0, 1, 0, y },
											{ 0, 0, 1, z },
											{ 0, 0, 0, 1 } });
	}
	
	enum Axis {
		X, Y, Z
	}
	
	public Matrix getRotationMatrixRad(float rotation, Axis axis) {
		float cos = (float) Math.cos(rotation);
		float sin = (float) Math.sin(rotation);
		if (axis == Axis.X) {
			return new Matrix(new float[][] { {1, 0, 0, 0 },
											{ 0, cos, sin, 0 },
											{ 0, -sin, cos, 0 },
											{ 0, 0, 0, 1 } });
		} else if (axis == Axis.Y) {
			return new Matrix(new float[][] { {cos, 0, sin, 0 },
											{ 0, 1, 0, 0 },
											{ -sin, 0, cos, 0 },
											{ 0, 0, 0, 1 } });
		} else if (axis == Axis.Z) {
			return new Matrix(new float[][] {{ cos, sin, 0, 0 },
											{ -sin, cos, 0, 0 },
											{ 0, 0, 1, 0 },
											{ 0, 0, 0, 1 } });
		}
		return null;
	}
	
	public Matrix getRotationMatrixDeg(float rotation, Axis axis) {
		return this.getRotationMatrixRad((float) Math.toRadians(rotation), axis);
	}
	
	public Matrix getScaleMatrix(float scaleX, float scaleY, float scaleZ) {
		return new Matrix(new float [][] { { scaleX, 0, 0, 0 },
											{ 0, scaleY, 0, 0 },
											{ 0, 0, scaleZ, 0 },
											{0, 0, 0, 1 } });
	}
	
	public Matrix getScaleMatrix(float scale) {
		return this.getScaleMatrix(scale, scale, scale);
	}
	
	public Matrix getXReflectionMatrix() {
		return this.getScaleMatrix(-1, 1, 1);
	}
	
	public Matrix getYReflectionMatrix() {
		return this.getScaleMatrix(1, -1, 1);
	}
	
	public Matrix getZReflectionMatrix() {
		return this.getScaleMatrix(1, 1, -1);
	}

	public Matrix getRotationMatrixVectors(Vector3D xv, Vector3D yv, Vector3D zv) {
		return new Matrix(new float [][] { { xv.getValue(0), xv.getValue(1), xv.getValue(2), 0 },
										{ yv.getValue(0), yv.getValue(1), yv.getValue(2), 0 },
										{ zv.getValue(0), zv.getValue(1), zv.getValue(2), 0 },
										{0, 0, 0, 1 } });
	}
}
