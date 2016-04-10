package model.camera;

import model.geometry3d.I3DVertex;
import model.geometry3d.Vector3D;
import model.matrixLib.Matrix;
import model.matrixLib.Matrix3DFactory;

public class OrthographicCamera implements Camera {
	
	private I3DVertex position;
	private I3DVertex lookAt;
	private Vector3D up;
	
	private Matrix3DFactory mFactory;
	private Matrix transformation;
	private Matrix projection;
	
	public OrthographicCamera(I3DVertex pos, I3DVertex lookAt, Vector3D up) {
		this.position = pos;
		this.lookAt = lookAt;
		this.up = up;
		
		this.mFactory = new Matrix3DFactory();
		this.projection = this.mFactory.getIdentityMatrix();
		this.projection.setValueAt(2, 2, 0);
		
		this.update();
	}
	
	@Override
	public void update() {
		Vector3D Zv = new Vector3D(this.lookAt, this.position);
		Zv.normalize();
		
		Vector3D Xv = this.up.crossProduct(Zv);
		Xv.normalize();
		
		Vector3D Yv = Zv.crossProduct(Xv);
		
		Matrix T = this.mFactory.getTransformationMatrix(
					-this.position.getX(), -this.position.getY(),
					-this.position.getZ());
		Matrix R = this.mFactory.getRotationMatrixVectors(Xv, Yv, Zv);
		
		this.transformation = R.multiply(T);
	}

	@Override
	public void setPosition(I3DVertex pos) {
		this.position = pos;
	}

	@Override
	public void setLookAt(I3DVertex lookAt) {
		this.lookAt = lookAt;
	}

	@Override
	public void setUp(Vector3D up) {
		this.up = up;
	}

	@Override
	public Matrix getTransformationMatrix() {
		return this.transformation;
	}

	@Override
	public Matrix getProjectionMatrix() {
		return this.projection;
	}
}
