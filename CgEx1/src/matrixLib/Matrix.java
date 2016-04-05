package matrixLib;

public class Matrix {

	protected float[][] matrix;

	public Matrix(int rows, int cols) {
		this.matrix = new float[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				this.matrix[i][j] = 0f;
			}
		}
	}

	public Matrix(float[][] matrix) {
		this.matrix = matrix;
	}

	public Matrix add(Matrix other) {
		if (other.matrix.length != this.matrix.length
				|| other.matrix[0].length != this.matrix[0].length) {
			throw new RuntimeException("Matrix dimentions do not match.");
		}

		float[][] addResult = new float[this.matrix.length][this.matrix[0].length];
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < this.matrix[0].length; j++) {
				addResult[i][j] = this.matrix[i][j] + other.matrix[i][j];
			}
		}
		return new Matrix(addResult);
	}

	public Matrix multiply(Matrix other) {
		if (this.matrix[0].length != other.matrix.length) {
			throw new RuntimeException("Matrix dimentions do not match.");
		}

		float[][] multResult = new float[this.matrix.length][other.matrix[0].length];
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

	public Vector multiply(Vector vector) {
		if (this.matrix.length != vector.matrix.length) {
			throw new RuntimeException("Matrix dimentions do not match.");
		}
		
		float[] multResult = new float[this.matrix.length];
		for (int i = 0; i < this.matrix.length; i++) {
			float sum = 0;
			for (int k = 0; k < this.matrix[0].length; k++) {
				sum += this.matrix[i][k] * vector.matrix[k][0];
			}
			multResult[i] = sum;
		}
		return new Vector(multResult);
	}

	public float getValueAt(int row, int col) {
		return this.matrix[row][col];
	}

	public void setValueAt(int row, int col, float value) {
		this.matrix[row][col] = value;
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < this.matrix.length; i++) {
			if (i > 0)
				str += "\n";
			str += "[";
			for (int j = 0; j < this.matrix.length; j++) {
				if (j > 0)
					str += ", ";
				str += this.matrix[i][j];
			}
			str += "]";
		}
		return str;
	}
}
