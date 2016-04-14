package model.camera;

import model.geometry3d.I3DVertex;
import model.geometry3d.Vector3D;
import model.matrixLib.Matrix;

public interface Camera {

	void setPosition(I3DVertex pos);
	
	void setLookAt(I3DVertex lookAt);
	
	void setUp(Vector3D up);
	
	void update();
	
	Matrix getTransformationMatrix();
	
	Matrix getProjectionMatrix();
	
	Matrix getLookAtMatrix();
	
	Matrix getLookAtMatrixR();
}
