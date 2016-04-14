package model.matrixLib;

public class Vector extends Matrix {

	public Vector(int dim) {
		super(dim, 1);
	}
	
	public Vector(float[] vector) {
		super(new float[vector.length][1]);
		for (int i = 0; i < vector.length; i++) {
			this.matrix[i][0] = vector[i];
		}
	}
	
	public float getValue(int row) {
		return this.matrix[row][0];
	}
	
	public void setValue(int row, float value) {
		this.matrix[row][0] = value;
	}

	public void normalizeByLastDim() {
		for (int i = 0; i < this.matrix.length - 1; i++) {
			this.matrix[i][0] /= this.matrix[this.matrix.length - 1][0];
		}
	}
}
