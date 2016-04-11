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
		return new Vertex3D(matrix.multiply(this.vector));
	}
}
