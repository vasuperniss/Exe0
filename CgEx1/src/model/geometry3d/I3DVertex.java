package model.geometry3d;

import model.geometry2d.I2DVertex;
import model.matrixLib.Matrix;

public interface I3DVertex extends I2DVertex {

	public float getZ();
	public Vertex3D applyMatrix(Matrix matrix);
	public float getDistanceFrom(I3DVertex lookAt);
}
