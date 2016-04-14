package model.geometry3d;

import model.geometry2d.Vertex2D;
import model.matrixLib.Matrix;
import model.matrixLib.Vector;

public class Vertex3D extends Vertex2D implements I3DVertex {

	public Vertex3D(float x, float y, float z) {
		super(new Vector(new float[] { x, y, z, 1 }));
	}

	public Vertex3D() {
		super(new Vector(new float[] { 0, 0, 0, 1 }));
	}

	protected Vertex3D(Vector vector) {
		super(vector);
	}

	@Override
	public float getZ() {
		return this.vector.getValue(2);
	}

	@Override
	public Vertex3D applyMatrix(Matrix matrix) {
		Vertex3D result = new Vertex3D(matrix.multiply(this.vector));
		result.vector.normalizeByLastDim();
		return result;
	}

	@Override
	public float getDistanceFrom(I3DVertex lookAt) {
		return (float) Math.sqrt(Math.pow(this.getX() - lookAt.getX(), 2)
				+ Math.pow(this.getY() - lookAt.getY(), 2)
				+ Math.pow(this.getZ() - lookAt.getZ(), 2));
	}
}
