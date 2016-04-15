package model.camera;

import model.geometry3d.I3DVertex;
import model.geometry3d.Vector3D;
import model.matrixLib.Matrix;

/**
 * The Camera interface.
 *
 * @author Michael Vassernis 319582888
 */
public interface Camera {

	/**
	 * Sets the position.
	 *
	 * @param pos the new position
	 */
	void setPosition(I3DVertex pos);
	
	/**
	 * Sets the look at point.
	 *
	 * @param lookAt the new look at
	 */
	void setLookAt(I3DVertex lookAt);
	
	/**
	 * Sets the up vector.
	 *
	 * @param up the new up
	 */
	void setUp(Vector3D up);
	
	/**
	 * Update the camera.
	 */
	void update();
	
	/**
	 * Gets the transformation matrix.
	 *
	 * @return the transformation matrix
	 */
	Matrix getTransformationMatrix();
	
	/**
	 * Gets the projection matrix.
	 *
	 * @return the projection matrix
	 */
	Matrix getProjectionMatrix();
	
	/**
	 * Gets the look at matrix.
	 *
	 * @return the look at matrix
	 */
	Matrix getLookAtMatrix();
	
	/**
	 * Gets the look at matrix reversed.
	 *
	 * @return the look at matrix reversed
	 */
	Matrix getLookAtMatrixR();
}
