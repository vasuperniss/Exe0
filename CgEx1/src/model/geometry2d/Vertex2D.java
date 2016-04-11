package model.geometry2d;

import model.matrixLib.Matrix;
import model.matrixLib.Vector;

public class Vertex2D implements I2DVertex {

	protected Vector vector;
	
	public Vertex2D(float x, float y) {
		this.vector = new Vector(new float[]{x, y, 1});
	}
	
	protected Vertex2D(Vector vector) {
		this.vector = vector;
	}

	@Override
	public float getX() {
		return this.vector.getValue(0);
	}

	@Override
	public float getY() {
		return this.vector.getValue(1);
	}
	
	@Override
	public Vertex2D applyMatrix(Matrix matrix) {
		return new Vertex2D(matrix.multiply(this.vector));
	}
}
