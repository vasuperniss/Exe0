package controller;

import model.matrixLib.Matrix3DFactory;

public interface IViewController {
	
	void changeClippingState();

	void resetToOriginalPosition();

	void loadANewFile(String file);

	void changeRotationAxis(Matrix3DFactory.Axis axis);

	void changePolygonFillingState();

	void quit();

	void setTmpScale(float f);

	void endModifing();

	void setTmpTransform(int diffX, int diffY);

	void setTmpRotation(float deg);

	void sizeChanged(int width, int height);

}
