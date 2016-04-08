package model.geometry3d;

import model.geometry2d.Vertex2D;
import model.matrixLib.Vector;

public class Vertex3D extends Vertex2D implements I3DVertex {

	public Vertex3D(float x, float y, float z) {
		super(new Vector(new float[] { x, y, z, 1 }));
	}

	public Vertex3D() {
		super(new Vector(new float[] { 0, 0, 0, 1 }));
	}

	@Override
	public float getZ() {
		return this.vector.getValue(2);
	}
}