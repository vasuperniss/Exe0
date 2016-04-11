package model.geometry2d;

import model.matrixLib.Matrix;

public interface I2DVertex {
	public float getX();
	public float getY();
	Vertex2D applyMatrix(Matrix matrix);
}
