package model.matrixLib;

/**
 * The Class Vector.
 * 
 * @author Michael Vassernis 319582888
 */
public class Vector extends Matrix {

	/**
	 * Instantiates a new vector.
	 *
	 * @param dim the dim
	 */
	public Vector(int dim) {
		super(dim, 1);
	}
	
	/**
	 * Instantiates a new vector.
	 *
	 * @param vector the vector
	 */
	public Vector(float[] vector) {
		super(new float[vector.length][1]);
		for (int i = 0; i < vector.length; i++) {
			this.matrix[i][0] = vector[i];
		}
	}
	
	/**
	 * Gets the value at.
	 *
	 * @param row the row
	 * @return the value
	 */
	public float getValue(int row) {
		return this.matrix[row][0];
	}
	
	/**
	 * Sets the value at.
	 *
	 * @param row the row
	 * @param value the value
	 */
	public void setValue(int row, float value) {
		this.matrix[row][0] = value;
	}

	/**
	 * Normalize by last dimension.
	 */
	public void normalizeByLastDim() {
		for (int i = 0; i < this.matrix.length - 1; i++) {
			this.matrix[i][0] /= this.matrix[this.matrix.length - 1][0];
		}
	}
}
