package model.matrixLib;

/**
 * The Class Matrix.
 * 
 * @author Michael Vassernis 319582888
 */
public class Matrix {

	/** The matrix. */
	protected float[][] matrix;

	/**
	 * Instantiates a new matrix.
	 *
	 * @param rows the rows
	 * @param cols the cols
	 */
	public Matrix(int rows, int cols) {
		this.matrix = new float[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				this.matrix[i][j] = 0f;
			}
		}
	}

	/**
	 * Instantiates a new matrix.
	 *
	 * @param matrix the matrix
	 */
	public Matrix(float[][] matrix) {
		this.matrix = matrix;
	}

	/**
	 * this matrix + other.
	 *
	 * @param other the other
	 * @return the result matrix
	 */
	public Matrix add(Matrix other) {
		if (other.matrix.length != this.matrix.length
				|| other.matrix[0].length != this.matrix[0].length) {
			throw new RuntimeException("Matrix dimentions do not match.");
		}

		float[][] addResult = new float[this.matrix.length][this.matrix[0].length];
		// O(n^2) matrixes addition
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < this.matrix[0].length; j++) {
				addResult[i][j] = this.matrix[i][j] + other.matrix[i][j];
			}
		}
		return new Matrix(addResult);
	}

	/**
	 * Multiply this with other.
	 *
	 * @param other the other
	 * @return the result matrix
	 */
	public Matrix multiply(Matrix other) {
		if (this.matrix[0].length != other.matrix.length) {
			throw new RuntimeException("Matrix dimentions do not match.");
		}

		float[][] multResult = new float[this.matrix.length][other.matrix[0].length];
		// O(n^3) matrix multiply a matrix
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < this.matrix[0].length; j++) {
				float sum = 0;
				for (int k = 0; k < this.matrix[0].length; k++) {
					sum += this.matrix[i][k] * other.matrix[k][j];
				}
				multResult[i][j] = sum;
			}
		}

		return new Matrix(multResult);
	}

	/**
	 * Multiply this with other (Vector).
	 *
	 * @param vector the vector
	 * @return the result vector
	 */
	public Vector multiply(Vector vector) {
		if (this.matrix.length != vector.matrix.length) {
			throw new RuntimeException("Matrix dimentions do not match.");
		}
		
		float[] multResult = new float[this.matrix.length];
		// O(n^2) matrix multiply a vector
		for (int i = 0; i < this.matrix.length; i++) {
			float sum = 0;
			for (int k = 0; k < this.matrix[0].length; k++) {
				sum += this.matrix[i][k] * vector.matrix[k][0];
			}
			multResult[i] = sum;
		}
		return new Vector(multResult);
	}

	/**
	 * Gets the value at.
	 *
	 * @param row the row
	 * @param col the col
	 * @return the value at
	 */
	public float getValueAt(int row, int col) {
		return this.matrix[row][col];
	}

	/**
	 * Sets the value at.
	 *
	 * @param row the row
	 * @param col the col
	 * @param value the value
	 */
	public void setValueAt(int row, int col, float value) {
		this.matrix[row][col] = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < this.matrix.length; i++) {
			if (i > 0)
				str += "\n";
			str += "[";
			for (int j = 0; j < this.matrix[0].length; j++) {
				if (j > 0)
					str += ", ";
				str += this.matrix[i][j];
			}
			str += "]";
		}
		return str;
	}
}
