package controller;

public interface IViewController {
	
	enum Axis { X, Y, Z }
	
	void changeClippingState();

	void resetToOriginalPosition();

	void loadANewFile(String file);

	void changeRotation(Axis x);

	void changePolygonFillingState();

	void quit();

}
