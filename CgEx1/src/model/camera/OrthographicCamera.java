package model.camera;

import model.geometry3d.I3DVertex;
import model.geometry3d.Vector3D;
import model.matrixLib.Matrix;
import model.matrixLib.Matrix3DFactory;

/**
 * The OrthographicCamera class.
 *
 * @author Michael Vassernis 319582888
 */
public class OrthographicCamera implements Camera {
	
	/** The position. */
	private I3DVertex position;
	
	/** The look at. */
	private I3DVertex lookAt;
	
	/** The up. */
	private Vector3D up;
	
	/** The distance. */
	private float distance;
	
	/** The m factory. */
	private Matrix3DFactory mFactory;
	
	/** The transformation. */
	private Matrix transformation;
	
	/** The projection. */
	protected Matrix projection;
	
	/**
	 * Instantiates a new orthographic camera.
	 *
	 * @param pos the pos
	 * @param lookAt the look at
	 * @param up the up
	 */
	public OrthographicCamera(I3DVertex pos, I3DVertex lookAt, Vector3D up) {
		this.position = pos;
		this.lookAt = lookAt;
		this.up = up;
		
		this.mFactory = new Matrix3DFactory();
		this.projection = this.mFactory.getIdentityMatrix();
		this.projection.setValueAt(2, 2, 0);
		
		this.update();
	}
	
	/* (non-Javadoc)
	 * @see model.camera.Camera#update()
	 */
	@Override
	public void update() {
		// Z vector
		Vector3D Zv = new Vector3D(this.lookAt, this.position);
		Zv.normalize();
		// X = Z x Up
		Vector3D Xv = Zv.crossProduct(this.up);
		Xv.normalize();
		// Y = X x Z
		Vector3D Yv = Xv.crossProduct(Zv);
		// camera to 0,0 matrix
		Matrix T = this.mFactory.getTransformationMatrix(
					-this.position.getX(), -this.position.getY(),
					-this.position.getZ());
		// to camera's world matix
		Matrix R = this.mFactory.getRotationMatrixVectors(Xv, Yv, Zv);
		// to camera's cords matrix
		this.transformation = R.multiply(T);
		this.distance = this.position.getDistanceFrom(this.lookAt);
	}

	/* (non-Javadoc)
	 * @see model.camera.Camera#setPosition(model.geometry3d.I3DVertex)
	 */
	@Override
	public void setPosition(I3DVertex pos) {
		this.position = pos;
	}

	/* (non-Javadoc)
	 * @see model.camera.Camera#setLookAt(model.geometry3d.I3DVertex)
	 */
	@Override
	public void setLookAt(I3DVertex lookAt) {
		this.lookAt = lookAt;
	}

	/* (non-Javadoc)
	 * @see model.camera.Camera#setUp(model.geometry3d.Vector3D)
	 */
	@Override
	public void setUp(Vector3D up) {
		this.up = up;
	}

	/* (non-Javadoc)
	 * @see model.camera.Camera#getTransformationMatrix()
	 */
	@Override
	public Matrix getTransformationMatrix() {
		return this.transformation;
	}

	/* (non-Javadoc)
	 * @see model.camera.Camera#getProjectionMatrix()
	 */
	@Override
	public Matrix getProjectionMatrix() {
		return this.projection;
	}
	
	/* (non-Javadoc)
	 * @see model.camera.Camera#getLookAtMatrix()
	 */
	@Override
	public Matrix getLookAtMatrix() {
		Matrix T = this.mFactory.getTransformationMatrix(
				0,
				0,
				this.distance);
		return T;
	}
	
	/* (non-Javadoc)
	 * @see model.camera.Camera#getLookAtMatrixR()
	 */
	@Override
	public Matrix getLookAtMatrixR() {
		Matrix T = this.mFactory.getTransformationMatrix(
				0,
				0,
				- this.distance);
		return T;
	}
}
