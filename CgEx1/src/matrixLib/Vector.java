package matrixLib;

public class Vector extends Matrix {

	public Vector(int dim) {
		super(dim, 1);
	}
	
	public Vector(float[] vector) {
		super(new float[][] {vector});
	}
	
	public float getValue(int row) {
		return this.matrix[row][1];
	}
	
	public void setValue(int row, float value) {
		this.matrix[row][1] = value;
	}
}
