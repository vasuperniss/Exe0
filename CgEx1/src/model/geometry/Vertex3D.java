package model.geometry;

import model.matrixLib.Vector;

public class Vertex3D extends Vertex2D implements I3DVertex {

	public Vertex3D(float x, float y, float z) {
		super(new Vector(new float[] { x, y, z, 1 }));
	}

	@Override
	public float getZ() {
		return this.vector.getValue(2);
	}
}
