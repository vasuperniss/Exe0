package model.geometry3d;

import model.matrixLib.Vector;

public class Vector3D extends Vector {

	public Vector3D(float[] vector) {
		super(4);
		this.matrix[0][0] = vector[0];
		this.matrix[1][0] = vector[1];
		this.matrix[2][0] = vector[2];
		this.matrix[3][0] = 1;
	}
	
	public Vector3D(float x, float y, float z) {
		super(4);
		this.matrix[0][0] = x;
		this.matrix[1][0] = y;
		this.matrix[2][0] = z;
		this.matrix[3][0] = 1;
	}
	
	public Vector3D crossProduct(Vector3D v) {
		if (this.matrix.length != v.matrix.length || this.matrix.length != 4)
			throw new RuntimeException("Vector dimentions do not match.");
		Vector3D u = this;		
		float[] crossP = new float[4];
		crossP[0] = u.matrix[1][0] * v.matrix[2][0] - u.matrix[2][0] * v.matrix[1][0];
		crossP[1] = u.matrix[2][0] * v.matrix[0][0] - u.matrix[0][0] * v.matrix[2][0];
		crossP[2] = u.matrix[0][0] * v.matrix[1][0] - u.matrix[1][0] * v.matrix[0][0];
		crossP[3] = u.matrix[3][0] * v.matrix[3][0];
	
		Vector3D crossV = new Vector3D(crossP);
		crossV.normalize();
		
		return crossV;
	}
	
	public Vector3D getNormalized() {
		float length = this.matrix[0][0] * this.matrix[0][0]
						+ this.matrix[1][0] * this.matrix[1][0]
						+ this.matrix[2][0] * this.matrix[2][0];
		length = (float) Math.sqrt(length);
		
		return new Vector3D(this.matrix[0][0] / length,
							this.matrix[0][0] / length,
							this.matrix[0][0] / length);
	}
	
	public void normalize() {
		float length = this.matrix[0][0] * this.matrix[0][0]
						+ this.matrix[1][0] * this.matrix[1][0]
						+ this.matrix[2][0] * this.matrix[2][0];
		length = (float) Math.sqrt(length);
		
		this.matrix[0][0] /= length;
		this.matrix[1][0] /= length;
		this.matrix[2][0] /= length;
	}
}
